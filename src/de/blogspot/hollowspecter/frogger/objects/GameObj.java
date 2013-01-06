package de.blogspot.hollowspecter.frogger.objects;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class GameObj {

	protected Image img;
	
	protected float posX;
	protected float posY;
	protected float spdX;
	private float spdY;
	protected float logspd = 0;
	protected String type;
	
	protected float startPosX;
	protected float startPosY;
	protected float startSpdX;
	protected float startSpdY;
	
	private float prevPosX;
	private float prevPosY;
	
	protected boolean collisionOn;
	
	protected Shape collisionShape;
	
	
	//constructor
	public GameObj(float posX, float posY, float spdX, float spdY, String type) {
		this.posX = posX;
		this.setPosY(posY);
		this.spdX = spdX;
		this.setSpdY(spdY);
		this.type = type;
		
		startPosX = posX;
		startPosY = posY;
		startSpdX = spdX;
		startSpdY = spdY;
		
		collisionOn = true;
	}
	
	
	public void init(GameContainer container) throws SlickException
	{
		img = new Image("img/" + type + ".png");
		collisionShape = new Rectangle(posX, getPosY(), img.getWidth(), img.getHeight());
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		setPrevPosX(posX);
		setPrevPosY(getPosY());
		
		float _delta = delta / 1000.0f;

		posX += spdX * _delta;
		setPosY(getPosY() + getSpdY() * _delta);
		
		collisionShape.setCenterX(posX);
		collisionShape.setCenterY(getPosY());
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		img.drawCentered(posX, getPosY());
//		g.draw(collisionShape);
	}

	//Methode zur Kollisionsabfrage
	public boolean checkCollisionWith(GameObj other)
	{
		if (collisionOn == true)
		{
			if (this == other)
				return false;
			
			return (collisionShape.contains(other.collisionShape) ||
					collisionShape.intersects(other.collisionShape) ||
					other.collisionShape.contains(collisionShape));
		}
		else return false;
	}
	
	public void reset()
	{
		this.posX = startPosX;
		this.setPosY(startPosY);
		this.spdX = startSpdX;
		this.setSpdY(startSpdY);
		collisionShape.setCenterX(startPosX);
		collisionShape.setCenterY(startPosY);
	}
	
	
	//getter und setter für XPOS
	public float getPosX()
	{
		return posX;
	}
	
	public void setPosX(float posX)
	{
		this.posX = posX;
	}
	
	public boolean getCollisionOn()
	{
		return collisionOn;
	}
	
	public void setCollisionOn(boolean collisionOn)
	{
		this.collisionOn = collisionOn;
	}
	
	public float getSpdX()
	{
		return this.spdX;
	}


	public float getPosY() {
		return posY;
	}


	public void setPosY(float posY) {
		this.posY = posY;
	}


	public float getPrevPosX() {
		return prevPosX;
	}


	public void setPrevPosX(float prevPosX) {
		this.prevPosX = prevPosX;
	}


	public float getSpdY() {
		return spdY;
	}


	public void setSpdY(float spdY) {
		this.spdY = spdY;
	}


	public float getPrevPosY() {
		return prevPosY;
	}


	public void setPrevPosY(float prevPosY) {
		this.prevPosY = prevPosY;
	}
}
