package de.blogspot.hollowspecter.frogger.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspecter.frogger.other.Constants;

public class GameStatePaused extends BasicGameState {
	
	private Image background;
	private Sound sound_unpause;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = new Image("img/background.png");
		sound_unpause = new Sound(Constants.SOUND_UNPAUSE);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_ENTER))
		{
			sound_unpause.play();
			state.enterState(GameStates.PlayingState);
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{
		background.draw(0,0);
		g.drawString("- PAUSED -", 215, 200);
		g.drawString("\n press ESCAPE or ENTER to Enter it again", 85, 220);
	}

	public int getID() {
		return GameStates.Paused;
	}


}
