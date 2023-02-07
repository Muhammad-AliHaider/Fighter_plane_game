package Objects;

public class EnemyFire extends Vehicle {
	int dy = 4;// speed at which the Enemy fire moves
	
	public EnemyFire(String path,int x,int y)// constructor
	{
		super(path,x,y);
	}
	
	public EnemyFire(String path,MyPoint p)// constructor
	{
		super(path,p);
	}

	@Override
	public void move()// causes the Enemy fire to move 
	{
		
	 this.y = y + dy;	
	}

	@Override
	public void Fire() 
	{
			
	}

	

}
