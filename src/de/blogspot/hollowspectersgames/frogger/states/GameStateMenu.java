package de.blogspot.hollowspectersgames.frogger.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspectersgames.frogger.objects.GameObj;
import de.blogspot.hollowspectersgames.frogger.other.Constants;

public class GameStateMenu extends BasicGameState {
	
	private Sound music;
	private Sound select;
	private Image background;
	private ArrayList<Image> menu;
	private GameObj selecter;
	private int position = 0;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		menu = new ArrayList<Image>();
		Image menu1 = new Image("img/menu/start.png");
		Image menu2 = new Image("img/menu/howtoplay.png");
		Image menu3 = new Image("img/menu/credits.png");
		menu.add(menu1);		
		menu.add(menu2);		
		menu.add(menu3);		

		music = new Sound(Constants.SOUND_MENUBG);
		
		select = new Sound(Constants.SOUND_SELECT);
		background = new Image("img/menu/bg.png");
		
		selecter = new GameObj(40,0,0,0,"/menu/select");
		selecter.init(container);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{		
		if (music.playing() == false)
			music.loop();

		selecter.update(container, delta);
		
		Input input = container.getInput();
		
		if (position == 0)
			selecter.setPosY(383);
		if (position == 1)
			selecter.setPosY(383+60);
		if (position == 2)
			selecter.setPosY(383+2*60);
		
		if (input.isKeyPressed(Input.KEY_DOWN))
		{
			select.play();
			position++;
			if (position > 2)
				position = 2;
		}
		
		if (input.isKeyPressed(Input.KEY_UP))
		{
			select.play();
			position--;
			if (position < 0)
				position = 0;
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER) )
		{
			if (position == 0)
			{
				select.play();
				music.stop();
				state.enterState(GameStates.PlayingState);
			}
			if (position == 1)
			{
				select.play();
				state.enterState(GameStates.Howtoplay);
			}
			if (position == 2)
			{
				select.play();
				state.enterState(GameStates.Credits);
			}
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{	
		background.draw(0,0);
		selecter.render(container, g);

		for (int i=0; i <= menu.size()-1; i++)
		{
			menu.get(i).draw(50,300+(i*60+60));
		}
	}

	public int getID() {
		return GameStates.MenuState;
	}
}
