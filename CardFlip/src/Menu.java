import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState
{
	Image background;
	Image title;
	Image playButton;
	Image creditsButton;
	Image exitButton;
	private static final int BUTTON_LENGTH = 400;
	private static final int BUTTON_HEIGHT = 75;
	int posx = 0;
	int posy = 0;
	float delay;
	
	
	public Menu(int state)
	{
		
	}
	
	//Initializing stuff
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		gc.setShowFPS(false); //FPS doesn't show in the top corner anymore
		background = new Image("res/Backdrop Green.png");
		title = new Image("res/Title3.png");
		playButton = new Image("res/Play_Button.png");
		creditsButton = new Image("res/HowTo_Button.png");
		exitButton = new Image("res/Exit_Button.png");
		delay = 200;
	}
	
	//Drawing stuff
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		background.draw(0,0);
		title.draw(0,0);
		playButton.draw(150,350);
		creditsButton.draw(150,450);
		exitButton.draw(150,550);
	}
	
	//Game Loop
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		posx = Mouse.getX();
		posy = Mouse.getY();
		
		delay -= 0.5f * delta;
		
		//Play button
		if (delay < 0 && (posx > 150 && posx < 150 + BUTTON_LENGTH) && (posy < 350 && posy > 350 - BUTTON_HEIGHT) && Mouse.isButtonDown(0))
			{ 
				sbg.enterState(1); 
				delay = 200;
			}
		
		//How To button, named credits because I changed it to How To at the last minute
		if (delay < 0 && (posx > 150 && posx < 150 + BUTTON_LENGTH) && (posy > 250 - BUTTON_HEIGHT && posy < 250) && Mouse.isButtonDown(0))
			{ 
				sbg.enterState(2); 
				delay = 200;
			}
		
		//Exit button
		if (delay < 0 && (posx > 150 && posx < 150 + BUTTON_LENGTH) && (posy > 150 - BUTTON_HEIGHT && posy < 150) && Mouse.isButtonDown(0))
			{ System.exit(0); }
		
	}
	
	public int getID()
	{ return 0; }
	
}
