package de.blogspot.hollowspectersgames.frogger.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspectersgames.frogger.other.Constants;

public class GameStateCredits extends BasicGameState {
	
	private Sound select;
	private boolean flash = true;
	private int flash_timer = 25;
	private Image pressStartToReturn;
	private Image background;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		pressStartToReturn = new Image("img/menu/pressentertoreturn.png");
		select = new Sound(Constants.SOUND_SELECT);
		background = new Image("img/menu/credits_bg.png");
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
		//damit das "press enter to return" flasht
		flash_timer--;
		
		if (flash_timer == 0)
		{
			if (flash == true)
				flash = false;
			else
				flash = true;
			
			flash_timer = 25;
		}
			
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_ENTER))
		{
			select.play();
			state.enterState(GameStates.MenuState);
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{		
		background.draw(0,0);
		
		if (flash == true)
			pressStartToReturn.drawCentered(270,580);
	}

	public int getID() {
		return GameStates.Credits;
	}


}
