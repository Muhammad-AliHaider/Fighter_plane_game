package Objects;

public class Bomber extends Vehicle {
	
	int dy = 1;// speed of the bomber at which it moves 
	private ProjectileFactory projectfact;

	public Bomber(String path, int x, int y)//constructor 
	{
		super(path, x, y);
	}
	public Bomber(String path, MyPoint p) //constructor
	{
		super(path, p);
	}

	@Override
	public void move()// is responsible for moving the the bomber  
	{
		y = y + dy;
	}

	public void Fire() {
		// TODO Auto-generated method stub
		projectfact = super.getFactory().returnProjectileFactory(x, y);
		bullet = projectfact.returnBullet(false);
	}

}
