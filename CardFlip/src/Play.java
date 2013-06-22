import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Play extends BasicGameState 
{
	private Input input;
	private Image background;
	private SpriteSheet cards;
	private SpriteSheet message;
	private Animation messageA;
	private int posx;
	private int posy;
	private int score;
	private int winStreak;
	private int totalBombs;
	private int pointCards;
	private int oneCards;
	private int[] rowTotal, rowBombs, colTotal, colBombs;
	private int[] cardPos = {155, 255, 355, 455, 555};
	private Card[][] board;
	private int cardRow;
	private int cardCol;
	private float delay;
	private boolean win;
	private boolean lose;
	
	//Constructor
	public Play(int state)
	{
		
	}
	
	//Initializes stuff
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		background = new Image("res/Background.png");
		delay = 200;
		score = 0;
		winStreak = 0;
		cards = new SpriteSheet("res/CardValues.png", 90, 90);
		message = new SpriteSheet("res/Message.png", 280, 170);
		messageA = new Animation(message, 200);
		messageA.stop(); //Stops animation loop
		messageA.setCurrentFrame(0); //Sets animation to the first frame
		
		restart();
	}
	
	//Renders everything when you first enter this state
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		background.draw(0,0);
		
		for (int a = 0; a < 5; a++)
		{
			g.drawString("" + colBombs[a],180 + a * 100,105);
			g.drawString("" + rowBombs[a], 80, 210 + a * 100);
			g.drawString("" + colTotal[a], 195 + a * 100, 80);
			g.drawString("" + rowTotal[a], 95, 180 + 100 * a);
		}
		
		g.drawString("Score: " + score, 50, 90);
		g.drawString("Wins: "+ winStreak, 57, 110);
		
		//Drawing all of the cards
		for (int i : cardPos)
		{
			for (int j : cardPos)
			{
					board[(j-155)/100][(i-155)/100].getAnimation().draw(i,j); 
			}
		}
		
		messageA.draw(210,263); //Animation that contains the Win/Lose messages. The first frame is invisible
		
	}
	
	//Main game loop
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		posx = Mouse.getX();
		posy = Mouse.getY();
		cardCol = (int)(posx - 150) / 100;
		cardRow = (int)(posy - 550) / -100;
		input = gc.getInput();
		
		delay -= 0.5f * delta; //Delta is the number of miliseconds since the last call to Update()
		
		//Checks if you can flip a card & if you clicked in the card area & that the card isn't already flipped
		if (!lose && delay < 0 && input.isMouseButtonDown(0) && ((posx > 150 && posy < 550) && (posx < 650  && posy > 50)) && !board[cardRow][cardCol].isCardFlipped())
		{
			board[cardRow][cardCol].flipCard();
			score += board[cardRow][cardCol].getValue() * 10;
			
			if (board[cardRow][cardCol].getValue() != 0 && board[cardRow][cardCol].getValue() != 1)
			{
				score += board[cardRow][cardCol].getValue() + (int)(board[cardRow][cardCol].getValue() * (winStreak * .5));
				pointCards--;
			}
			
			delay = 200;
		}
		
		
		//Checks if you've flipped a bomb card. Probably should've gone up there ^^ but its a bit late now, lol
		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 5; c++)
			{
				if (board[r][c].isCardFlipped() && board[r][c].getValue() == 0)
				{ lose = true; }
			}
		}
		
		if (pointCards == 0 && !lose)
			win = true;
		
		if (win)
		{
			messageA.setCurrentFrame(2); //Displays "You've Won!" message
		
			if (delay < 0 && input.isMouseButtonDown(0))
			{
				for (int i = 0; i < 1; i++)
				{winStreak++;}
		
				restart();
				delay = 200;
			}
		}
			
		if (lose)
		{ 
			messageA.setCurrentFrame(1); //Displays "You've Lost!" message
		
			if (input.isMouseButtonDown(0) && delay < 0)
			{
				sbg.enterState(0); //Go back to main menu
				score = 0;
				winStreak = 0;
				restart();
				delay = 200;
			}
		}
		
		
	}
	
	public int getID()
	{ return 1; }
	
	//Resets the board, win/lose booleans and row/col values
	public void restart()
	{
		rowTotal = new int[5];
		rowBombs = new int[5];
		colTotal = new int[5];
		colBombs = new int[5];
		totalBombs = 0;
		oneCards = 0;
		pointCards = 0;
		board = new Card[5][5];
		win = false;
		lose = false;
		messageA.setCurrentFrame(0);
		delay = 200;
		
		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 5; c++)
			{
				board[r][c] = new Card((int)(Math.random() * 4), cards);
			}
		}
			
		//Calculates row/col point values and bombs
		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 5; c++)
			{
				rowTotal[r] += board[r][c].getValue();
					
				if (board[r][c].getValue() == 0)
				{
					rowBombs[r]++;
					totalBombs++;
				}
					
				colTotal[r] += board[c][r].getValue();
					
				if (board[c][r].getValue() == 0)
					colBombs[r]++;
				
				if (board[r][c].getValue() != 0 && board[r][c].getValue() != 1)
					pointCards++;
				
				if (board[r][c].getValue() == 1)
					oneCards++;
				
			}		
		}
		
		if (totalBombs < 3 || totalBombs > 10)
			restart();
		
		if (oneCards < 10)
			restart();
		
	}
}

