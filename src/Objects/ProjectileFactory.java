package Objects;

public class ProjectileFactory {
	
	Vehicle bullet;
	public int positionx;
	public int positiony;
	
	public ProjectileFactory(int x, int y)
	{
		positionx = x;
		positiony = y;
	}
	// turn for player and false for enemy
	public Vehicle returnBullet(boolean check)
	{
		if(check == true)
		{
			bullet = new PlayerFire("src/Sprites/PlayerFire/",positionx,positiony-70);
		}
		else
		{
			bullet = new EnemyFire("src/Sprites/EnemyFire/",positionx,positiony+70);
		}
		return bullet;
	}
	

}
