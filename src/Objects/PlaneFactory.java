package Objects;

import java.util.Random;

public class PlaneFactory {
	
	private Vehicle enemy;
	public int width;
	
	
	public PlaneFactory(int w)
	{
		width = w;
	}
	
	// true for fighter , false for bomber
	public Vehicle returnEnemy(Boolean x)
	{
		Random rand = new Random();
		int rand_int =(int) rand.nextInt(width);
		if(x == true)
		{	
			enemy = new Fighter("src/Sprites/FighterSprite/",rand_int,0);
		}
		else
		{
			enemy = new Bomber("src/Sprites/B-17/Type-1/Animation/",rand_int,0);
		}
		
		return enemy;
	}
	

}
