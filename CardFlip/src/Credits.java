import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Credits extends BasicGameState //Changed credits button to How To at the last minute
{
	private Image background;
	private Image backButton;
	private int posx;
	private int posy;
	
	public Credits(int state)
	{
		
	}
	
	//Initializes stuff
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		background = new Image("res/HowTo.png");
		backButton = new Image("res/Back_Button.png");
	}
	
	//Draws Stuff
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		background.draw(0,0);
		backButton.draw(5,620);
	}
	
	//Game loop
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		posx = Mouse.getX();
		posy = Mouse.getY();
		
		//Back Button
		if ((posx > 5 && posx < 205) && (posy < 80 && posy > 5) && Mouse.isButtonDown(0))
		{ sbg.enterState(0); }
	}
	
	public int getID()
	{ return 2; }
	
}
