package de.blogspot.hollowspecter.frogger.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.blogspot.hollowspecter.frogger.other.Constants;
import de.blogspot.hollowspecter.frogger.other.RupeeStringHover;



public class Frog extends CenteredCollisionFrog{

	protected int lifeCount;
	protected int rupeesInBag;
	protected boolean onBoat;
	private boolean eingabe;
	private Image frogWithBag;
	private Image life;
	protected Sound sound_die;
	private Sound sound_gameover;
	
	private float lifePosX = 400;
	private final float LIFEPOSYIN = 5;
	
	private RupeeStringHover rupeeStringHover;
	
	public Frog(float posX, float posY, int lifeCount)
	{
		super(posX, posY, 0, 0);
		
		this.lifeCount = lifeCount;
		onBoat = false;
		eingabe = false;
	}
	
	public void init(GameContainer container) throws SlickException
	{
		super.init(container);
		
		frogWithBag = new Image("img/frogWithBag.png");
		life = new Image("img/frog.png");
		setRupeeStringHover(new RupeeStringHover());
		sound_die = new Sound(Constants.SOUND_DIE);
		setSound_gameover(new Sound(Constants.SOUND_GAMEOVER));
	}

	public void update(GameContainer container, int delta) throws SlickException {
		
		// Input handling
		Input input = container.getInput();
		
		if (eingabe == true)
		{
			if (input.isKeyDown(Input.KEY_LEFT)) {
				spdX = -Constants.FROGSPEED;
				img.setRotation(-90);
			} else if (input.isKeyDown(Input.KEY_RIGHT)) {
				spdX = Constants.FROGSPEED;
				img.setRotation(90);
			} else {
				spdX = 0;
			}
	
			if (input.isKeyDown(Input.KEY_UP)) {
				setSpdY(-Constants.FROGSPEED);
				img.setRotation(0);
			} else if (input.isKeyDown(Input.KEY_DOWN)) {
				setSpdY(Constants.FROGSPEED);
				img.setRotation(180);
			} else {
				setSpdY(0);
			}
		
		//beim schr�g laufen bild auch schr�g rotieren lassen
		if (input.isKeyDown(Input.KEY_LEFT)&&input.isKeyDown(Input.KEY_UP))
			img.setRotation(-45);
		if (input.isKeyDown(Input.KEY_LEFT)&&input.isKeyDown(Input.KEY_DOWN))
			img.setRotation(-120);
		if (input.isKeyDown(Input.KEY_RIGHT)&&input.isKeyDown(Input.KEY_UP))
			img.setRotation(45);
		if (input.isKeyDown(Input.KEY_RIGHT)&&input.isKeyDown(Input.KEY_DOWN))
			img.setRotation(120);

		}
		
		frogWithBag.setRotation(img.getRotation());
		
		getRupeeStringHover().update(container, delta);
		super.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		super.render(container, g);
		
		switch(getLifeCount())
		{
		case 3: life.draw(lifePosX+3*35, LIFEPOSYIN, 0.7f); 
		case 2: life.draw(lifePosX+2*35, LIFEPOSYIN, 0.7f);
		case 1: life.draw(lifePosX+1*35, LIFEPOSYIN, 0.7f); break;
		}
		
		if (rupeesInBag != 0)
			frogWithBag.drawCentered(posX,getPosY());
		
		getRupeeStringHover().render(container, g);
	}
	
	public int getLifeCount()
	{
		return lifeCount;
	}
	
	public void reduceLifeCount()
	{
		lifeCount--;
		sound_die.play();
	}
	
	public void incrLifeCount()
	{
		lifeCount++;
	}
	
	public void setLifeCount(int numberOfLives)
	{
		lifeCount = numberOfLives+1;
	}
	
	public int getRupeeaInBag()
	{
		return rupeesInBag;
	}
	
	public void setRupeesInBag(int rupees)
	{
		this.rupeesInBag = rupees;
	}
	
	public void incrRupeesInBag(int rupees)
	{
		this.rupeesInBag += rupees;
	}
	
	public boolean getOnBoat()
	{
		return onBoat;
	}
	
	public void setOnBoat(boolean onBoat)
	{
		this.onBoat = onBoat;
	}
	
	public boolean getEingabe()
	{
		return eingabe;
	}
	
	public void setEingabe(boolean eingabe)
	{
		this.eingabe = eingabe;
	}
	
	public void reset()
	{
		/*
		 * 1. collision is turned off so the frog can slide in off screen
		 * 2. input is toggled off so while sliding you cant move around
		 * 3. reduced lifecount
		 * 4. frog is rotatet to its original
		 * 5. posX and posY resettet, speed set, so he slides in
		 * 6. bag is emptied
		 */
		
		if (lifeCount > 1)
		{
			this.setCollisionOn(false);
			this.setEingabe(false);
			this.reduceLifeCount();
			img.setRotation(0);
			this.posX = startPosX;
			this.setPosY(startPosY + 105);
			this.spdX = startSpdX;
			this.setSpdY(-60);
			collisionShape.setCenterX(startPosX);
			collisionShape.setCenterY(startPosY);
			setRupeesInBag(0);
		}
		else
		{
			/*
			 * When Gameover
			 */
			
			this.posX = startPosX;
			this.setPosY(startPosY);
			collisionShape.setCenterX(startPosX);
			collisionShape.setCenterY(startPosY);
			this.reduceLifeCount();
		}
	}

	public RupeeStringHover getRupeeStringHover() {
		return rupeeStringHover;
	}

	public void setRupeeStringHover(RupeeStringHover rupeeStringHover) {
		this.rupeeStringHover = rupeeStringHover;
	}

	public int getRupeesInBag() {
		return rupeesInBag;
	}

	public Sound getSound_gameover() {
		return sound_gameover;
	}

	public void setSound_gameover(Sound sound_gameover) {
		this.sound_gameover = sound_gameover;
	}
	
	public void setSpdX(float spdX) {
		this.spdX = spdX;
	}
}
