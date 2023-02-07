package Objects;

public class Fighter extends Vehicle {
	
	int dy =  3;// moving speed of the fighter
	int dx = 8;// dodging speed of the fighter
	
	private ProjectileFactory projectfact;

	

	public Fighter(String path, int x, int y)// constructor 
	{
		super(path, x, y);
	}
	
	public Fighter(String path, MyPoint p)// constructor 
	{
		super(path, p);
	}

	@Override
	public void move()// causes the fighter to move 
	{
		// TODO Auto-generated method stub
		
		this.y = y + dy;
	
	}
	public void dodge(boolean x)// cause the fighter to move
	{
			if(x == true)
			{
				this.x = this.x+dx;
				//System.out.println("123 " + this.x);
			}
			if(x == false)
			{
				this.x = this.x-dx;
				//System.out.println("123132 " + this.x);
			}
	}

	public void Fire() {
		// TODO Auto-generated method stub
		projectfact = super.getFactory().returnProjectileFactory(x, y);
		bullet = projectfact.returnBullet(false);
	}
	
	
	
	

}
