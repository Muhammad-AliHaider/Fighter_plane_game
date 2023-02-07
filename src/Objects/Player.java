package Objects;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import Objects.MyPoint;

public class Player extends Vehicle
{
	public int width;
	public int height;
	public static Player player = new Player("src/Sprites/BF-109E/Type-1/Animation/", 0, 0);
	
	private ProjectileFactory projectfact;
	
	public Player()
	{
		super();
	}
	
	private Player(String path, int x, int y)// constructor
	{
		super(path, x, y);
	}
	
	private Player(String path, MyPoint p)// constructor
	{
		super(path, p);
	}
	
	public static Player getInstance()
	{
		return player;
	}
	
	
	public void move()// causes the player to move
	{
		if((dx>0))//it controls the right movement of the player and doesn't let it pass the bounds of the screen
		{
			if((x<=width)&& (x>=0))
			this.x += dx;
		}
		
		if((dx<0))//it controls the left movement of the player and doesn't let it pass the bounds of the screen
		{
			if((x<=width)&& (x>=0))
			this.x += dx;
		}
		
		if((dy>0))//it controls the downward movement of the player and doesn't let it pass the bounds of the screen
		{
			if((y<=1024)&&(y>=0))
			this.y += dy;
		}
		
		if((dy<0))//it controls the upward movement of the player and doesn't let it pass the bounds of the screen
		{
			if((y<=1024)&&(y>=0))
			this.y += dy;
		}

	}
	
	public void keyPressed(KeyEvent e) 
	{
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        if (key == KeyEvent.VK_SPACE) {
            Fire();
        }
    }
	public void Fire()
	{
		projectfact = super.getFactory().returnProjectileFactory(x, y);
		bullet = projectfact.returnBullet(true);
	}

    public void keyReleased(KeyEvent e) 
    {        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

	
}
