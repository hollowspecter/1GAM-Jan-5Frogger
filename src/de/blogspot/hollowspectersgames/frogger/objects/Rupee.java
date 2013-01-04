package de.blogspot.hollowspectersgames.frogger.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.blogspot.hollowspectersgames.frogger.other.Constants;

public class Rupee extends GameObj{
			
	protected int wert;
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;
	private Image img5;
	private Sound sound;
	
	//modifyer
	protected boolean collected = false;
	
	public Rupee(float posX, float posY) {
		super(posX, posY, 0, 0, "rupee1");
		randomize();
	}

	public void init(GameContainer container) throws SlickException
	{
		super.init(container);
		
		setSound(new Sound(Constants.SOUND_PICKUP));
		
		img1 = new Image("img/rupee1.png");
		img2 = new Image("img/rupee3.png");
		img3 = new Image("img/rupee-1.png");
		img4 = new Image("img/rupee5.png");
		img5 = new Image("img/rupee10.png");
		
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		super.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		if (collected == false)
		{
			if (wert == 3)
				img2.drawCentered(posX,getPosY());
			else if (wert == -1)
				img3.drawCentered(posX,getPosY());
			else if (wert == 5)
				img4.drawCentered(posX,getPosY());
			else if (wert == 10)
				img5.drawCentered(posX, posY);
			else
				img1.drawCentered(posX, getPosY());
		}
	}
	
	public void randomize()
	{
		randomizeWert(3, 5, 0.66f, 0.93f);
	}
	
	/*
	 * Ursprüngliche Methode, die zwischen 1, -1 und 3 würfelt
	 * Verhältnisse stehen in der Constants.java
	 */
	
	public void randomizeWert()
	{
		final double n = Math.random();
		
		//die Wahrscheinlichkeiten sind in Constants als RUPEECHANCES abgespeichert
		if (n >= 0 && n < Constants.RUPEECHANCES[0])
			wert = 1;
		
		if (n >= Constants.RUPEECHANCES[0] && n < Constants.RUPEECHANCES[1])
			wert = -1;
		
		if (n >= Constants.RUPEECHANCES[1] && n <= 1)
			wert = 3;
		
		this.setCollected(false);
 	}
	
	/*
	 * Bei dieser Methode kann zwischen 2 Werten gewürfelt werden,
	 * und die Wahrscheinlichkeit muss mit angegeben werden
	 */
	
	public void randomizeWert(int wert1, int wert2, float chance)
	{
		final double n = Math.random();
		
		if (n >= 0 && n < chance)
			wert = wert1;
		if (n >= chance && n <= 1)
			wert = wert2;
		
		this.setCollected(false);
 	}
	
	/*
	 * Wieder für 2 verschiedene Werte, hinzukommt aber die zweite Chance
	 * für einen goldenen Rubin (10) der extrem viel wert ist
	 * und deswegen nur sehr selten ist
	 */
	
	public void randomizeWert(int wert1, int wert2, float chance1, float chance2)
	{
		final double n = Math.random();
		
		if (n >= 0 && n < chance1)
			wert = wert1;
		if (n >= chance1 && n < chance2)
			wert = wert2;
		if (n >= chance2 && n < 1)
			wert = 10;
		
		this.setCollected(false);
 	}
	
	public int getWert()
	{
		return wert;
	}
	
	public void setWert(int wert)
	{
		this.wert = wert;
	}
	
	public boolean getCollected()
	{
		return collected;
	}
	
	public void setCollected(boolean collected)
	{
		this.collected = collected;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}
}