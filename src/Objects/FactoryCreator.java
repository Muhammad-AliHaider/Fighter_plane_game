package Objects;

public class FactoryCreator {
	
	public PlaneFactory returnPlaneFactory(int w)
	{
		PlaneFactory planefactory = new PlaneFactory(w);
		return planefactory;
	}
	
	public ProjectileFactory returnProjectileFactory(int x , int y)
	{
		ProjectileFactory projectilefactory = new ProjectileFactory(x,y);
		return projectilefactory;
	}

}
