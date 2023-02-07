
/* uncomment this to draw stuff
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Objects.Bomber;
import Objects.EnemyFire;
import Objects.FactoryCreator;
import Objects.Fighter;
import Objects.PlaneFactory;
import Objects.Player;
import Objects.PlayerFire;
import Objects.ProjectileFactory;
import Objects.Vehicle;


public class Board extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int DELAY = 10;
    
	private Player player;
	private int w = 1024;
	private int h = 768;
	private ArrayList <Vehicle> linkedList = new ArrayList<>();
	private FactoryCreator factory = new FactoryCreator();
	private PlaneFactory planefact;
	private int count = 0;
	private int count1 = 0;
	private Vehicle enemy;
	private int enemyleftbound = 0;
	private int enemyrightbound = 0;
	private int bottom = 0;
	private int count2 = 0;
	private boolean checker;
	int x = 0;
	int y = 0;
	
		
	private Timer timer;

    public Board() 
    {    	
        initBoard();
    }
    
    private void initBoard() //Initializes all the game objects
    {	
    	addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        
        player = Player.getInstance();
        player.SetFactory(factory);

        planefact = factory.returnPlaneFactory(w);
        
        setPreferredSize(new Dimension((int)w, (int)h));   //Set the size of Window     
        player.moveTo((int)w/2, (int)h *3/4);
         
        
        timer = new Timer(DELAY, this); //Timer with 10 ms delay 
        timer.start();
    }
    
    
    @Override
    public void paintComponent(Graphics g) //Draws all the components on screen
    {
    	g.setColor(getBackground());		//get the background color
        g.clearRect(0 , 0, (int)w, (int)h);	//clear the entire window
    	Dimension size = getSize();  //get the current window size  	
        w = (int)size.getWidth(); // gets the width of the board
        h = (int)size.getHeight(); // gets the height of the board
        
        planefact.width = w;


        Graphics2D g2d = (Graphics2D) g;
        
        
        for(int i = 0 ; i < linkedList.size(); i++) // is used to draw all the objects in the linked list
        {
        	if(linkedList.get(i)!= null)
        	{
        	Vehicle v1 = linkedList.get(i);
        	v1.paintComponent(g2d);
        	}
        }
        if(checker == true) // is used to iterate the explosion images 
        {
        	Image[] image = new Image[4];
        	int w1 = 0;
        	int h1 = 0;
        	String path = "src/Sprites/Explosion/";
        	for(int i = 0 ; i < 4 ; i++)
    		{
    		ImageIcon imageIcon = new ImageIcon(path +Integer.toString(i+1)+".png");
    		image[i] = imageIcon.getImage();
    				
    		w1 = image[i].getWidth(null);
            h1 = image[i].getHeight(null);
    		}
        	for(int i = 0 ; i < 4 ; i++)
        	{
        	g2d.drawImage(image[i], x-w1/2, y-h1/2, this);
        	
        	}
        	
        	checker = false;
        }
        
        if(player != null)
        player.paintComponent(g2d);//paint the player 
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    
    public void actionPerformed(ActionEvent e) {
    	if(player != null)
    	{
    		if(player.bullet!=null)
    		linkedList.add(player.bullet); // adds the bullet in the linked list
    		player.bullet = null;
    	}
        step();
        count++;
        count1++;
        count2++;
    }
    
    private void step() 
    { 	if(player != null)
    	{
    	player.width = w;
        player.height = h;
    	}
    	for(int i = 0 ; i < linkedList.size(); i++) // is responsible to move the objects in the linked list
        {
    		if(linkedList.get(i)!= null){
        	linkedList.get(i).move();
    		}
        }
    	for(int i = 0 ; i < linkedList.size();i++) // is setting bounds for the objects other than player that if they go out of bounds they will get removed from the linked list
    	{
    		if(linkedList.get(i)!= null)
    		{
	    		if((linkedList.get(i).getY() <= 0) || (linkedList.get(i).getY() >= h) || (linkedList.get(i).getX() <= 0) || (linkedList.get(i).getX() >= w) )
	    		{
	    			linkedList.get(i).isActive = false; 
	    			
	    		}
    		}
    	}
    	
    	for(int i = 0 ; i < linkedList.size();i++) // is used to make the fighter dodge the bullet
    	{
    		if(linkedList.get(i) instanceof Fighter) // checks if the object at position i of the linked list is a Fighter
    		{
    			enemyleftbound = ( linkedList.get(i).getX()-(int)(linkedList.get(i).getWidth()/2) ); // finds the enemy rectangle's left bottom coordinate
    			enemyrightbound = ((int)(linkedList.get(i).getWidth()/2) + linkedList.get(i).getX()); // finds the enemy rectangle's right bottom coordinate
    			for(int k = 0; k<linkedList.size() ; k++)
    			{
	    			if(linkedList.get(k) instanceof PlayerFire)// checks if the object at position i of the linked list is a bullet or not
	    			{
	    				if((linkedList.get(k).getX() >= enemyleftbound)&&(linkedList.get(k).getX() <= enemyrightbound)) // checks if the bullet lies within the rectangle of the enemy
	    				{
	    					if(linkedList.get(k).getY() <= (linkedList.get(i).getY() + 200))
	    					{
		    					int L = linkedList.get(k).getX() - enemyleftbound; // the distance between the bullet and left bound of the enemy
		    					int R = enemyrightbound - linkedList.get(k).getX(); // the distance between the bullet and right bound of the enemy
		    					if(L<R)
		    					{
		    					
		    					linkedList.get(i).dodge(true);// causes the fighter to dodge the bullet
		    					}
		    					else if(R<L)
		    					{
		    					
		    					linkedList.get(i).dodge(false);// causes the fighter to dodge the bullet
		    					}
	    					}
	    				}
	    			}
    			}
    		}
    	}
    	    	
    	
    	for(int i=0; i<linkedList.size();i++)// removes the enemy from the linked list on collision with the bullet 
    	{
    		if(linkedList.get(i) instanceof PlayerFire)// checks if the object at position i of the linked list is a bullet or not
    		{
    			for(int j=0; j<linkedList.size();j++)
    			{
    				if((linkedList.get(j) instanceof Bomber)|| (linkedList.get(j) instanceof Fighter) )// checks if the object at position i of the linked list is a Fighter or a bomber
    						{
    							Rectangle planBounds = linkedList.get(j).getBounds();// gets the rectangular bounds of the enemy
    							Rectangle bulletBounds = linkedList.get(i).getBounds();// gets the rectangular bounds of the bullet
    							if(bulletBounds.intersects(planBounds))
    							{
    								linkedList.get(i).isActive = false;// on collision sets the bullet's isActive boolean as false 
    								linkedList.get(j).isActive = false;// on collision sets the enemy's isActive boolean as false 
    								
    								x=linkedList.get(j).getX(); // gets and stores the x coordinate of the enemy
    								y=linkedList.get(j).getY();// gets and stores the y coordinate of the enemy 
    							
    								
    								checker = true; //  sets checker to true to cause the explosion
    								
    							}
    						}
    			}
    		}
    	}
    	
    	
    	
    	if(player != null)
    	{
	    	for(int i = 0; i < linkedList.size();i++)
	    	{
	    		if((linkedList.get(i) instanceof Fighter) || (linkedList.get(i) instanceof Bomber))// checks if the object at position i of the linked list is a Fighter or a bomber
	    		{
	    			enemyleftbound = ( linkedList.get(i).getX()-(int)(linkedList.get(i).getWidth()/2) );// finds the enemy rectangle's left bottom x-coordinate
	    			enemyrightbound = ((int)(linkedList.get(i).getWidth()/2) + linkedList.get(i).getX());// finds the enemy rectangle's right bottom x-coordinate
	    			bottom = (linkedList.get(i).getY()+(int)(linkedList.get(i).getHeight()/2)); // finds the enemy rectangle's bottom y coordinate 
	    			int playerleftbound = (player.getX()-(int)player.getWidth()/2); // finds the player rectangle's left upper x-coordinate
	    			int playerrightbound = ((int)(player.getWidth()/2) + player.getX());// finds the player rectangle's right upper x-coordinate
	    			if((player.getY()-70) <= bottom)
	    			{
		    			if(((playerleftbound >= enemyleftbound)&&(playerleftbound <= enemyrightbound)) || ((playerrightbound >=enemyleftbound)&&(playerrightbound <=enemyrightbound))) // checks if enemy plane collides with the player
		    			{
		    				linkedList.get(i).isActive = false;// on collision sets the enemy's isActive boolean as fals
		    				checker = true;//  sets checker to true to cause the explosion
		    				x = linkedList.get(i).getX();// gets and stores the x coordinate of the enemy
		    				y = linkedList.get(i).getY();// gets and stores the y coordinate of the enemy
		    			}
	    			}
	    		}
	    	}
    	}
    	if(player != null)
    	{
	    	for(int i = 0; i < linkedList.size();i++)
	    	{
	    		if(linkedList.get(i) instanceof EnemyFire)
	    		{
	    			Rectangle playerrect = player.getBounds();
	    			Rectangle enemyfirerect = linkedList.get(i).getBounds();
	    			if(playerrect.intersects(enemyfirerect))
	    			{
	    				player = null;
	    				linkedList.get(i).isActive = false;
	    				checker = true;
	    			}
	    		}
	    	}
    	}
    	
    	for(int i = 0 ; i< linkedList.size();i++)
    	{
    		if(linkedList.get(i) instanceof EnemyFire)
    		{
    			for(int j = 0 ; j< linkedList.size() ; j++)
    			{
    				if(linkedList.get(j) instanceof PlayerFire)
    				{
    					Rectangle enemyfirerect = linkedList.get(i).getBounds();
    					Rectangle playerfirerect = linkedList.get(j).getBounds();
    					if(enemyfirerect.intersects(playerfirerect))
    					{
    						linkedList.get(i).isActive = false;
    						linkedList.get(j).isActive = false;
    					}
    				}
    			}
    		}
    	}
    	if(player != null)
    	{
	    	if(count2 >100 )
	    	{
	    		for(int i = 0 ; i < linkedList.size(); i++)
	    		{
	    			if((linkedList.get(i) instanceof Fighter) || (linkedList.get(i) instanceof Bomber))
	    			{
	    				enemyleftbound = ( linkedList.get(i).getX()-(int)(linkedList.get(i).getWidth()/2) );// finds the enemy rectangle's left bottom x-coordinate
		    			enemyrightbound = ((int)(linkedList.get(i).getWidth()/2) + linkedList.get(i).getX());// finds the enemy rectangle's right bottom x-coordinate
		    			int playerleftbound = (player.getX()-(int)player.getWidth()/2); // finds the player rectangle's left upper x-coordinate
		    			int playerrightbound = ((int)(player.getWidth()/2) + player.getX());// finds the player rectangle's right upper x-coordinate
		    			if(((playerleftbound >= enemyleftbound)&&(playerleftbound <= enemyrightbound)) || ((playerrightbound >=enemyleftbound)&&(playerrightbound <=enemyrightbound))) // checks if enemy plane collides with the player
		    			{
		    				linkedList.get(i).Fire();
		    				if(linkedList.get(i).bullet != null);
		    				{
		    					linkedList.add(linkedList.get(i).bullet);
		    				}
		    			}
	    					linkedList.get(i).bullet = null;
	    				count2 = 0;
	        			break;
	
	    			}
	    		}
	    	}
    	}
    	
    	
    	if(count1 > 300) // is responsible for creating enemy planes
    	{ 
    		enemy = planefact.returnEnemy(false);
    		enemy.SetFactory(factory);
    		linkedList.add(enemy);
    		count1 = 0;
    	}
    	if(count > 200)// is responsible for creating fighter plane
    	{ 
    		enemy = planefact.returnEnemy(true);
    		enemy.SetFactory(factory);
    		linkedList.add(enemy);
    		count = 0;
    	}
    	if(player != null)
        player.move(); // cause the player to move
        Cleanup();//clean the list from unwanted objects
        repaint();
        
       	
    }    
    
    private void Cleanup()//clean the list from unwanted objects
    {
    	for(int i = 0 ; i < linkedList.size();i++)
    	{
    		if(linkedList.get(i) != null)
    		{
	    		if(linkedList.get(i).isActive != true)
	    		{
	    			linkedList.remove(i);
	    		}
    		}
    	}
    }

    private class TAdapter extends KeyAdapter 
    {

        @Override
        public void keyReleased(KeyEvent e) 
        {
        	if(player != null)
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
        	if(player != null)
            player.keyPressed(e);
        }
    }

}