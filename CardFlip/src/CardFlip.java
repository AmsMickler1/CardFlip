import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class CardFlip extends StateBasedGame 
{
	public static final String GAME_NAME = "Card Flip!";
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int CREDITS = 2;
	public static final int WIN = 3;
	public static final int LOSE = 4;
	
	//Creating the other game states
	public CardFlip(String GAME_NAME)
	{
		super(GAME_NAME);
		this.addState(new Menu(MENU));
		this.addState(new Play(PLAY));
		this.addState(new Credits(CREDITS));
	}
	
	//Initializes the states
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.getState(MENU).init(gc, this);
		this.getState(PLAY).init(gc, this);
		this.getState(CREDITS).init(gc, this);
		this.enterState(MENU);
	}
	
	//Main Method; I have no idea how the try/catch statement works, but you need it to launch the game
	public static void main(String[] args) 
	{
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new CardFlip(GAME_NAME));
			appgc.setDisplayMode(700, 700, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

}
