package de.blogspot.hollowspectersgames.frogger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspectersgames.frogger.states.GameOver;
import de.blogspot.hollowspectersgames.frogger.states.GameStateCredits;
import de.blogspot.hollowspectersgames.frogger.states.GameStateHowToPlay;
import de.blogspot.hollowspectersgames.frogger.states.GameStateMenu;
import de.blogspot.hollowspectersgames.frogger.states.GameStatePaused;
import de.blogspot.hollowspectersgames.frogger.states.GameStatePlaying;

public class GameStateFrogger extends StateBasedGame{

	public GameStateFrogger() {
		super("5F: Find Food For Frogger Family 0.9.4");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		//Einlesen der einzelnen States
		addState(new GameStateMenu());
		addState(new GameStatePlaying());
		addState(new GameOver());
		addState(new GameStatePaused());
		addState(new GameStateHowToPlay());
		addState(new GameStateCredits());
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new GameStateFrogger(), 540, 680, false);
			app.setVSync(true); //stops tearing
			app.setAlwaysRender(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
