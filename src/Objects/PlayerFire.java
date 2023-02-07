package Objects;

public class PlayerFire extends Vehicle {
	
	int dy = -4;// speed at which the bullet moves
	
	public PlayerFire(String path,int x,int y)// constructor
	{
		super(path,x,y);
	}
	
	public PlayerFire(String path,MyPoint p)// constructor
	{
		super(path,p);
	}

	@Override
	public void move()// causes the bullet to move 
	{
		
	 this.y = y + dy;	
	}

	public void Fire() {
		
	}
	
	
	
}
