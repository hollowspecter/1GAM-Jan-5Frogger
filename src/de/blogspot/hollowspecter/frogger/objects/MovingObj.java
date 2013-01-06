package de.blogspot.hollowspecter.frogger.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import de.blogspot.hollowspecter.frogger.other.Constants;

public class MovingObj extends GameObj{
	
	protected int lane;
	protected float lanePosY;
	protected boolean rotate;
	
	//constructor
	public MovingObj(float posX, float spd, String type, int lane)
	{
		super(posX, 0, spd, 0, type);
		this.lane = lane;
	}
	
	//constructor with fixed speeds setup for every lane
	public MovingObj(float posX, String type, int lane, boolean rotate)
	{
		super(posX, 0, 0, 0, type);
		this.lane = lane;
		this.rotate = rotate;
		
		if (lane == 0)
			spdX = Constants.LANESPD0;
		if (lane == 1)
			spdX = Constants.LANESPD1;
		if (lane == 2)
			spdX = Constants.LANESPD2;
		if (lane == 3)
			spdX = Constants.LANESPD3;
		if (lane == 4)
			spdX = Constants.LANESPD4;
		if (lane == 6)
			spdX = Constants.LANESPD6;
		if (lane == 7)
			spdX = Constants.LANESPD7;
		if (lane == 8)
			spdX = Constants.LANESPD8;
		if (lane == 9)
			spdX = Constants.LANESPD9;
		if (lane == 10)
			spdX = Constants.LANESPD10;
	}
	
	public MovingObj(float posX, String type, int lane)
	{
		this(posX,type,lane,false);
	}
	
	public void init(GameContainer container) throws SlickException
	{
		super.init(container);
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		super.update(container, delta);
		
		if (rotate == true)
			img.setRotation(180);
		
		setLanePosY(563.0f - 45.0f *getLane());
		
		//das Auto in die angegebene Lane platzieren
		if (getPosY() == 0)
			setPosY(getLanePosY());
		
		//prüfen ob das Auto bereits aus dem Bildschirm gefahren ist
		{
			if (checkOutOfBounds(this) == true)
			{
				if (spdX > 0)
					posX -= 1000;
				else
					posX += 1000;
			}
		}
		
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		super.render(container, g);
	}
	
	//getter und setter
	public int getLane()
	{
		return lane;
	}
	public void setLane(int lane)
	{
		this.lane = lane;
	}
	public float getLanePosY()
	{
		return lanePosY;
	}
	public void setLanePosY(float lanePosY)
	{
		this.lanePosY = lanePosY;
	}
	
	//weitere Methoden
	public boolean checkOutOfBounds(MovingObj obj)
	{
		if (this.spdX > 0)
			if (posX >= 650)
				return true;
			else
				return false;
		else
			if (posX <= -50)
				return true;
			else
				return false;
			
	}
	
}
