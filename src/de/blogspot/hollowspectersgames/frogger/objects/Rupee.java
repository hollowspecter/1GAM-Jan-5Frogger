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
	private Sound sound;
	
	//modifyer
	private boolean collected = false;
	
	public Rupee(float posX, float posY) {
		super(posX, posY, 0, 0, "rupee1");
		this.wert = randomizeWert();
	}

	public void init(GameContainer container) throws SlickException
	{
		super.init(container);
		
		setSound(new Sound(Constants.SOUND_PICKUP));
		
		img1 = new Image("img/rupee1.png");
		img2 = new Image("img/rupee3.png");
		img3 = new Image("img/rupee-1.png");
		img4 = new Image("img/rupee5.png");
		
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
				img2.draw(posX,getPosY());
			else if (wert == -1)
				img3.draw(posX,getPosY());
			else if (wert == 5)
				img4.draw(posX,getPosY());
			else
				img1.draw(posX, getPosY());
		}
	}
	
	public int randomizeWert()
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
		
		return wert;
 	}
	
	//randomized zwischen den zwei werten zu der angegebenen wahrscheinlichkeit
	public void randomizeWert(int wert1, int wert2, float chance)
	{
		final double n = Math.random();
		
		if (n >= 0 && n < chance)
			wert = wert1;
		if (n >= chance && n < 1)
			wert = wert2;
		
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