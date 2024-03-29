package de.blogspot.hollowspecter.frogger.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import de.blogspot.hollowspecter.frogger.other.Constants;

public class RupeeLane extends Rupee{
	
	private int lane;
	private int position;
			
	public RupeeLane(int position, int lane) {
		super(0, Constants.getPosYWithLane(lane));
		this.position = position;
		this.lane = lane;
		randomize();
	}

	public void init(GameContainer container) throws SlickException
	{
		super.init(container);
		
		posX = Constants.RUPEEPOS[position];
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		super.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		super.render(container, g);
	}
	
	/*
	 * �berschreibt urspr�ngliche randomize, je nachdem ob auf
	 * der Stra�e, auf dem Mittelstreifen oder auf dem Fluss,
	 * sollen die Werte anders gew�rfelt werden
	 */
	
	public void randomize()
	{
		//Auf der Stra�e
		if (lane <= 4)
		{
			/*
			 * Auf der Stra�e nur gr�ne und vereinzelt schwarze Rubine sein
			 * Sie sollen nur zu einer bestimmten Wahrscheinlichkeit random
			 * gespawnt werden! Toggle with "collected"
			 */
			super.randomizeWert(1, -3, 0.75f);
			randomizeCollected(0.5f);
		}
		//Mittelstreifen
		if (lane == 5)
		{
			//nur gr�ne, und zwar immer
			super.randomizeWert(1, -3, 1);
			this.setCollected(false);
		}
		//Auf dem Wasser
		else if (lane >= 6)
		{
			super.randomizeWert(1, 10, 0.3f);
			randomizeCollected(0.8f);
		}
	}
	
	/*
	 * Schaltet collected zu einer angegebenen Wahrscheinlichkeit an. 
	 * (l�sst es verschwinden)
	 * Ansonsten bleibt es aus.
	 */
	public void randomizeCollected(float chance)
	{
		final double n = Math.random();

		if (n <= chance)
			this.collected = true;
	}
	
}