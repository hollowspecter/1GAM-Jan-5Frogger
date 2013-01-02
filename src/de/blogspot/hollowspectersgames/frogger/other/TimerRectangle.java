package de.blogspot.hollowspectersgames.frogger.other;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class TimerRectangle {

	private float height;
	private float width;
	private float posX;
	private float posY;
	private long timer;
	private long resetTimer;
	private float startWidth;
	private boolean timerOn;
	private boolean flash = false;
	private int flashtimer = 10;
	private boolean event = false;

		
	//constructor
	public TimerRectangle(float height, float width, long timer) {
		this.posX = 430;
		this.posY = 645;
		this.height = height;
		this.width = width;
		this.timer = 0;
		
		resetTimer = timer;
		startWidth = width;
		timerOn = false;
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		if (getTimerOn() == true)
		{
			
			if (timer != 0)
				timer--;
			
			if (timer == 0)
			{
				width--;
				restartTimer();
			}
			
			if (width <= 0)
			{
				width = startWidth;
				setEvent(true);
				restartTimer();
			}
		}
		
		//zum flashen wenn die Zeit zu kurz wird
		if (width <= 30)
		{
			flashtimer--;
			
			if (flashtimer <= 0)
			{
				if (flash == false)
					flash = true;
				else
					flash = false;
				
				flashtimer = 10;
			}
			
		}
		else flash = false;
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		g.drawRect(posX-1, posY-1, startWidth+1, height+1);
		
		//Wenn der Balken nicht mehr viel Zeit hat flasht es rot zur Warnung

		if (flash == false)
			g.setColor(Color.green);
		if (flash == true)
				g.setColor(Color.red);
		g.fillRect(posX, posY, width, height);
		
		g.setColor(Color.black);
		g.drawString("Timer:", posX-60, posY);
	}

	//getter und setter
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	
	public boolean getTimerOn() {
		return timerOn;
	}
	public void setTimerOn(boolean timerOn) {
		this.timerOn = timerOn;
	}
 
	public long getTimer() {
		return timer;
	}
	public void setTimer(long timer) {
		this.timer = timer;
	}
	public void restartTimer() {
		this.timer = resetTimer;
	}
	public void restoreWidth() {
		this.width = startWidth;
	}

	public boolean getEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}
}
