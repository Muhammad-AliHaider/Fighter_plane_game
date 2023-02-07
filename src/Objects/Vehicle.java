package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Vehicle 
{
	protected Image[] image = new Image[3];// array of images
	protected int x;
	protected int y;
	protected int w;
    protected int h;
	protected int dx, dy;
	public Vehicle bullet;
	public boolean isActive = true;// used for removing objects in the linked list
	private FactoryCreator factory = new FactoryCreator();
		
	protected long count = 0;
	
	public Vehicle()
	{
		
	}
	public void SetFactory(FactoryCreator fact)
	{
		factory = fact;
	}
	public FactoryCreator getFactory()
	{
		return factory;
	}
	
	public Vehicle(String path, int x, int y)// constructor
	{
		this.x = x;
		this.y = y;
		for(int i = 0 ; i < 3 ; i++)// stores the images in the image array
		{
		ImageIcon imageIcon = new ImageIcon(path +(i+1)+".png");
		image[i] = imageIcon.getImage();
				
		w = image[i].getWidth(null);
        h = image[i].getHeight(null);
		}
	}
	
	public Vehicle(String path, MyPoint p)// constructor
	{
		x = p.x;
		y = p.y;
		for(int i = 0 ; i < 3 ; i++)// stores the images in the image array
		{
		ImageIcon imageIcon = new ImageIcon(path +Integer.toString(i+1)+".png");
		image[i] = imageIcon.getImage();
				
		w = image[i].getWidth(null);
        h = image[i].getHeight(null);
		}
	}
	
	public Rectangle getBounds() // gets the bounds of the triangle 
	{
	    return new Rectangle(x-w/2, y-w/2, w, h);
	}
	
	
	
	public void paintComponent(Graphics2D g)// draws the objects 
	{
		int num = (int)(count%3);
		g.drawImage(image[num], x - image[num].getWidth(null)/2, y - image[num].getHeight(null)/2, null);
		g.setColor(new Color(255,0,0));
		//g.drawRect(x-w/2, y-w/2, w, h);//Only to show image bounds, can be removed
		//System.out.println(x-w/2);
		count++;
	}
	
	public void moveTo(int x, int y)// moves the object to a certain position
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void move();
	public abstract void Fire();
	
	public void dodge(boolean x)// is done because wanted to access dodge through the vehicle's reference
	{
		
	}
	
	public int getX() // returns the x coordinate 
	{
        
        return x;
    }

    public int getY() // returns the y coordinate 
	{
        
        return y;
    }
    
    public int getWidth() // returns the width of the image 
	{
        return w;
    }
    
    public int getHeight() // returns the height of the image
	{
        return h;
    }    

    public Image[] getImage()// returns the array of images  
    {
        
        return image;
    }
	
	
}
