import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Card
{
	private int myValue;
	private boolean isFlipped = false;
	private Animation myAnimation;
	
	public Card(int value, SpriteSheet sprite)
	{
		myValue = value;
		myAnimation = new Animation(sprite, 200);
		myAnimation.stop();
		myAnimation.setCurrentFrame(4);
	}
	
	public int getValue()
	{ return myValue; }
	
	public Animation getAnimation()
	{ return myAnimation; }
	
	public boolean isCardFlipped()
	{ return isFlipped; }
	
	public void flipCard()
	{ 
		myAnimation.setCurrentFrame(myValue); 
		isFlipped = true;
	}
	
}
