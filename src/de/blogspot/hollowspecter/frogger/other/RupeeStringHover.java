package de.blogspot.hollowspecter.frogger.other;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/*
 * Kleine zahl die über dem Frosch wegfadet/hovert, wenn
 * man einen Rupee eingesammelt hat.
 */

public class RupeeStringHover {
	
	private float posX;
	private float posY;
	private String wert = null;
	private boolean activate;
	
	//timer
	private final long TIMER = 70;
	private long timer = 0;
	
	public RupeeStringHover ()
	{
	
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		if (activate == true)
		{
			if (timer != 0)
			{
				timer--;
				posY -= 0.75f;
			} else
			{
				activate = false;
			}
		}

	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		if (activate == true)
		{
			int i = Integer.parseInt(wert);
			if (i > 0)
				g.setColor(Color.white);
			if (i < 0)
				g.setColor(Color.red);
			
			g.drawString(this.wert,posX,posY);
			g.setColor(Color.black);
		}
	}
	
	public void activate(float posX, float posY, int wert)
	{
		//die Position auf den Frosch updaten
		this.posX = posX;
		this.posY = posY;
		
		//dann zeigt es +wert oder -wert an
		if (wert > 0)
		{
			this.wert = "+";
			this.wert += String.valueOf(wert);
		} else
			this.wert = String.valueOf(wert);
			
		
		activate = true;
		timer = TIMER;
	}
}