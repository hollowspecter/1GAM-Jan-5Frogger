package de.blogspot.hollowspecter.frogger.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;

import de.blogspot.hollowspecter.frogger.objects.AllObjects;
import de.blogspot.hollowspecter.frogger.objects.Frog;
import de.blogspot.hollowspecter.frogger.objects.GameObj;
import de.blogspot.hollowspecter.frogger.objects.Rupee;
import de.blogspot.hollowspecter.frogger.objects.Woman;
import de.blogspot.hollowspecter.frogger.other.Constants;
import de.blogspot.hollowspecter.frogger.other.TimerRectangle;


public class GameStatePlaying extends BasicGameState {
	
	private Image background;
	private Image rupeeCounterImg;
	private String guideString = "";
	protected Frog frog;
	protected TimerRectangle timerBar;
    private Woman woman;
	
	//Sounds
	protected Sound sound_pause;
	protected Music sound_music;
	
	//Highscores
	public static int rupeeCounter;
	public static int highscore;
	
	//Timer
	private long resetTimer;
    private final long RESETTIMER = 100;
	
    //boolean variablen
    private boolean isMusicPaused = false;
    
	//Listen
	protected AllObjects allOtherObjects;
	
	
	public GameStatePlaying() {		
		allOtherObjects = new AllObjects();
		
		resetTimer = RESETTIMER;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame state) throws SlickException
	{	
		background = new Image("img/background.png");
		rupeeCounterImg = new Image("img/rupee1.png");
		frog = new Frog(260, 605, 3);
		allOtherObjects.init(container);
		woman = new Woman();
		woman.init(container);
		timerBar = new TimerRectangle(20, 100, 20);
		sound_pause = new Sound(Constants.SOUND_PAUSE);
		sound_music = new Music(Constants.SOUND_PLAYINGBG);
		
		frog.init(container);
	}

	@Override
    public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
    	frog.update(container, delta);
    	allOtherObjects.update(container, delta);
    	
    	//Musik loopen
    	if (sound_music.playing() == false)
    	{
    		if (isMusicPaused == true)
    			sound_music.resume();
    		else
    			sound_music.loop();
    	}
    		
    	
    	//Testobjekte
    	woman.update(container, delta);
    	timerBar.update(container, delta);
    	
    	//Rupees einsammeln
    	for (Rupee obj : allOtherObjects.getRupees()) {
    		if (frog.checkCollisionWith(obj) == true)
    		{
    			if (obj.getCollected() == false)
    			{
    				obj.setCollected(true);
    				obj.getSound().play();
    				frog.incrRupeesInBag(obj.getWert());
    				frog.getRupeeStringHover().activate(frog.getPosX(), frog.getPosY(), obj.getWert());
    			}

    		}
    	}
		
    	//und bei der Frau abgeben!
    	/*
    	 * Nur wenn die Rupees bei der Frau abgegegeben werden
    	 * dann wird die Zeit vollends aufgefüllt
    	 */
    	/*
    	 * Berechnung des Highscores:
    	 * Rupees * verbliebende Zeit = Highscore
    	 */
    	if (frog.checkCollisionWith(woman) == true && frog.getRupeesInBag() != 0) {
    		
    		//Highscore Berechnung!
    		highscore += (long) ((timerBar.getWidth()/1) * frog.getRupeeaInBag());
    		
    		rupeeCounter += frog.getRupeesInBag();
    		frog.setRupeesInBag(0);
    		woman.setWithBag(true);
    		woman.setCollisionOn(false);
    		woman.setAnimation(true);
    		woman.getSound().play();
    		timerBar.restoreWidth();
    		for (Rupee obj : allOtherObjects.getRupees()) {
				obj.randomize();
			}
    	}
    	
		//beim Tod/Gameover
		if (frog.getLifeCount()<=0)
		{
			frog.getSound_gameover().play();
			sound_music.stop();
			
			frog.reset();
			frog.setRupeesInBag(0);
			frog.setLifeCount(2);
			timerBar.setTimerOn(false);
			
			state.enterState(GameStates.GameOver);
		}
		
		//wenn die Zeit abgelaufen wird: ein Leben nehmen!
		//außerdem die Rupees wieder neu druchwürfeln
		if (timerBar.getEvent() == true) {
			frog.reduceLifeCount();
			frog.incrLifeCount();
			timerBar.setEvent(false);
			frog.reset();
			timerBar.setTimerOn(false);

			for (Rupee obj : allOtherObjects.getRupees()) {
				obj.randomize();
			}
		}
		
		// Kollisionsabfrage
		// Frosch mit Autos
		for (GameObj obj : allOtherObjects.getCars()) {
			if (frog.checkCollisionWith(obj) == true) {
				frog.reset();
				timerBar.setTimerOn(false);
				
				for (Rupee obj2 : allOtherObjects.getRupees()) {
					obj2.randomize();
				}
			}
		}

		// mit den LOGS oder dem WASSER
		frog.setOnBoat(false);

		for (GameObj obj : allOtherObjects.getLogs()) {
			if (frog.checkCollisionWith(obj) == true) {
				frog.setOnBoat(true);
				frog.setPosX(frog.getPosX() + (obj.getSpdX() * (delta / 1000.0f)));
			}
		}

		for (GameObj obj2 : allOtherObjects.getRiver()) {
			if (frog.checkCollisionWith(obj2) == true
					&& frog.getOnBoat() == false) {
				frog.reset();
				timerBar.setTimerOn(false);
				
				for (Rupee obj : allOtherObjects.getRupees()) {
					obj.randomize();
				}
			}
		}

		//Falls man den Frosch wegen eines resettes nicht mehr steuern kann
		//sollen so und so viele Sekunden passieren bis man ihn wieder
		//kontrollieren kann!
		if (frog.getEingabe() == false)
		{
			resetTimer--;
		}
		if (resetTimer <= 0)
		{
			frog.setEingabe(true);
			frog.setCollisionOn(true);
			timerBar.setTimerOn(true);
			resetTimer = RESETTIMER;
			timerBar.restoreWidth();
		}
		
		//Der Frosch soll nicht durch die Wände können!
		for (GameObj obj : allOtherObjects.getWall())
		{
			if (frog.checkCollisionWith(obj) == true)
			{				
				//von rechts
                if (frog.getSpdX() > 0)
                	frog.setPosX(frog.getPrevPosX());
                //von links
                else if (frog.getSpdX() < 0)
                	frog.setPosX(frog.getPrevPosX());
                //von unten
                if (frog.getSpdY() < 0 && frog.getPosY() >= 82)
                	frog.setPosY(frog.getPrevPosY());
			}
			
		}
		
		//Pause game
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sound_pause.play();
			sound_music.pause();
			isMusicPaused = true;
			state.enterState(GameStates.Paused);
		}
		
		/*
		 * Der Guide wird nur ganz am Anfang oben als kleiner Text eingeblendet.
		 * Sobald einmal die Rupees bei der Frau abgegeben worden sind,
		 * verschwindet der Text bzw ein motivierender Text steht dort.
		 */
		
		if (guideString.equals(Constants.GUIDE5) == false)
		{
			//erster Tipp
			guideString = Constants.GUIDE1;
			
			//zweiter Tipp
			if (frog.getRupeesInBag() >= 2)
				guideString = Constants.GUIDE2;
			
			//dritter Tipp
			if (rupeeCounter >= 1)
				guideString = Constants.GUIDE3;
			
			//vierter Tipp
			if (guideString.equals(Constants.GUIDE3) && frog.getRupeesInBag() >= 1)
				guideString = Constants.GUIDE4;

			//Ende
			if (guideString.equals(Constants.GUIDE4) && frog.getRupeesInBag() == 0)
				guideString = Constants.GUIDE5;
			
		}
		
	}
	
    @Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{
    	g.setColor(Color.black);
    	
		background.draw(0,0);
		rupeeCounterImg.draw(320,643);

		allOtherObjects.render(container, g);
		frog.render(container, g);
		
		woman.render(container, g);
		timerBar.render(container, g);
		
		g.drawString(guideString,10,6);
		g.drawString(String.valueOf(rupeeCounter),300,644);
		g.drawString("Highscore: " + String.valueOf(highscore),20,644);

	}
	

	@Override
	public int getID() {
		return GameStates.PlayingState;
	}
	
	public static int getCounter() {
		return rupeeCounter;
	}
}
