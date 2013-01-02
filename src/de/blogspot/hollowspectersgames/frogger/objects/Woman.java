package de.blogspot.hollowspectersgames.frogger.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.blogspot.hollowspectersgames.frogger.other.Constants;

public class Woman extends GameObj{
	
	private boolean withBag = false;
	private boolean animation = false;
	private Sound sound;
	private Image womanWithBag;
	
	//Timer
	private final long TIMER = 130;
	private long timer = TIMER;
	private long timer2 = TIMER;
	
	public Woman()
	{
		super(20, 609, 0, 0, "woman");
	}

	public void init(GameContainer container) throws SlickException
	{
		super.init(container);
		img.setRotation(90);
		womanWithBag = new Image("img/womanwithbag.png");
		setSound(new Sound(Constants.SOUND_GIVERUPEES));
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		//wenn die Frau Rupees erhalten hat, soll sie vom Bildschirm mit
		//der Tasche verschwinden, und nach einem kurzen Moment wieder
		//ohne Rucksack sich hinstellen
		if (isAnimation() == true && timer != 0) {
			timer--;

			if (timer != 0) {
				img.setRotation(-90);
				spdX = -30;
			}
		} else if (isAnimation() == true && timer2 != 0) {
			timer2--;
			img.setRotation(90);
			spdX = 30;
			setWithBag(false);
		} else if (isAnimation() == true) {
			setAnimation(false);
			collisionOn = true;

			spdX = 0;

			timer = TIMER;
			timer2 = TIMER;
		}
		
		womanWithBag.setRotation(img.getRotation());
		super.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		super.render(container, g);
		if (isWithBag() == true)
			womanWithBag.drawCentered(posX,getPosY());
	}

	public boolean isWithBag() {
		return withBag;
	}

	public void setWithBag(boolean withBag) {
		this.withBag = withBag;
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}
}