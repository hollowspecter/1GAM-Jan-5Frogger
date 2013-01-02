package de.blogspot.hollowspectersgames.frogger.objects;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import de.blogspot.hollowspectersgames.frogger.other.Constants;

public class AllObjects {
	
	private ArrayList<GameObj> cars;
	private ArrayList<GameObj> logs;
	private ArrayList<GameObj> river;
	private ArrayList<GameObj> wall;
	private ArrayList<Rupee> rupees;
	
	public AllObjects()
	{
		setCars(new ArrayList<GameObj>());
		setLogs(new ArrayList<GameObj>());
		setRiver(new ArrayList<GameObj>());
		setWall(new ArrayList<GameObj>());
		setRupees(new ArrayList<Rupee>());
	}
	
	public void init(GameContainer container) throws SlickException
	{
		/* 
		 * Autos werden hinzugefügt, indem in der Constants Klasse bei den
		 * jeweiligen Lanes weitere Positionen hinzugefügt werden.
		 * Diese werden in den Schleifen ausgelesen und daraus Objekte dann in die
		 * Listen eingefügt.
		 */
		
		//Lane 0 Stinger
		for(int i=0; i <= Constants.LANEPOS0.length-1; i++)
		{
			MovingObj stinger = new MovingObj (Constants.LANEPOS0[i], "stinger", 0);
			getCars().add(stinger);
		}
		
		//Lane 1 Regal
		for(int i=0; i <= Constants.LANEPOS1.length-1; i++)
		{
			MovingObj regal = new MovingObj (Constants.LANEPOS1[i], "regal", 1, true);
			getCars().add(regal);
		}
		
		//Lane 2 Police
		for(int i=0; i <= Constants.LANEPOS2.length-1; i++)
		{
			MovingObj police = new MovingObj (Constants.LANEPOS2[i], "police", 2, true);
			getCars().add(police);
		}
		
		//Lane 3 Hotdog
		for(int i=0; i <= Constants.LANEPOS3.length-1; i++)
		{
			Hotdog hotdog = new Hotdog(Constants.LANEPOS3[i], 3);
			getCars().add(hotdog);
		}
		
		//Lane 4 Donuts
		for(int i=0; i <= Constants.LANEPOS4.length-1; i++)
		{
			MovingObj tanker = new MovingObj(Constants.LANEPOS4[i], "tanker", 4, true);
			getCars().add(tanker);
		}
		
		//Alle Autos werden zusammen initialisiert
		for(GameObj obj : getCars()) {
			obj.init(container);
		}
		
		//alle schwimmenden Sachen
		
		for (int i = 0; i <= Constants.LANEPOS6.length - 1; i++) {
			MovingObj log6 = new MovingObj(Constants.LANEPOS6[i], "poop", 6);
			getLogs().add(log6);
		}

		for (int i = 0; i <= Constants.LANEPOS7.length - 1; i++) {
			MovingObj log7 = new MovingObj(Constants.LANEPOS7[i], "poop", 7);
			getLogs().add(log7);
		}

		for (int i = 0; i <= Constants.LANEPOS8.length - 1; i++) {
			MovingObj log8 = new MovingObj(Constants.LANEPOS8[i], "poop", 8);
			getLogs().add(log8);
		}

		for (int i = 0; i <= Constants.LANEPOS9.length - 1; i++) {
			MovingObj log9 = new MovingObj(Constants.LANEPOS9[i], "poop", 9);
			getLogs().add(log9);
		}

		for (int i = 0; i <= Constants.LANEPOS10.length - 1; i++) {
			MovingObj log10 = new MovingObj(Constants.LANEPOS10[i], "poop", 10);
			getLogs().add(log10);
		}
		
		//alle logs initialisieren
		for(GameObj obj : getLogs()) {
			obj.init(container);
		}
		
		//river
		GameObj stream1 = new GameObj(270,(563.0f - 45.0f * 6),0,0,"stream");
		GameObj stream2 = new GameObj(270,(563.0f - 45.0f * 7),0,0,"stream");
		GameObj stream3 = new GameObj(270,(563.0f - 45.0f * 8),0,0,"stream");
		GameObj stream4 = new GameObj(270,(563.0f - 45.0f * 9),0,0,"stream");
		GameObj stream5 = new GameObj(270,(563.0f - 45.0f * 10),0,0,"stream");
		getRiver().add(stream1);
		getRiver().add(stream2);
		getRiver().add(stream3);
		getRiver().add(stream4);
		getRiver().add(stream5);
		
		for(GameObj obj : getRiver()) {
			obj.init(container);
		}

		GameObj wall1 = new GameObj(115,54,0,0,"wall");
		GameObj wall2 = new GameObj(216,54,0,0,"wall");
		GameObj wall3 = new GameObj(320,54,0,0,"wall");
		GameObj wall4 = new GameObj(420,54,0,0,"wall");
		getWall().add(wall1);
		getWall().add(wall2);
		getWall().add(wall3);
		getWall().add(wall4);
		
		for(GameObj obj : getWall()) {
			obj.init(container);
		}
		
		for (int i = 0; i <= Constants.RUPEEPOSLAST.length - 1; i++) {
			Rupee rupee = new Rupee(Constants.RUPEEPOSLAST[i], Constants.RUPEEPOSLASTY);
			getRupees().add(rupee);
		}
		
		for(GameObj obj : getRupees()) {
			obj.init(container);
		}
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		for(GameObj obj : getCars()) {
			obj.update(container, delta);
			}
		for(GameObj obj : getLogs()) {
			obj.update(container, delta);
			}
		for(GameObj obj : getRiver()) {
			obj.update(container, delta);
			}
		for(GameObj obj : getWall()) {
			obj.update(container, delta);
			}
		for(GameObj obj : getRupees()) {
			obj.update(container, delta);
			}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		for(GameObj obj : getCars()) {
			obj.render(container, g);
			}
		for(GameObj obj : getLogs()) {
			obj.render(container, g);
			}
		for(GameObj obj : getWall()) {
			obj.render(container, g);
			}
		for(GameObj obj : getRupees()) {
			obj.render(container, g);
			}
//		for(GameObj obj : river) {
//		obj.render(container, g);
//		}
	}
	
	public ArrayList<GameObj> getList(String s)
	{
		if (s.equals("cars") == true)
			return getCars();
		else if (s.equals("river") == true)
			return getLogs();
		else if (s.equals("river") == true)
			return getCars();
		else if (s.equals("wall") == true)
			return getWall();
		else return null;
	}

	public ArrayList<Rupee> getRupees() {
		return rupees;
	}

	public void setRupees(ArrayList<Rupee> rupees) {
		this.rupees = rupees;
	}

	public ArrayList<GameObj> getCars() {
		return cars;
	}

	public void setCars(ArrayList<GameObj> cars) {
		this.cars = cars;
	}

	public ArrayList<GameObj> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<GameObj> logs) {
		this.logs = logs;
	}

	public ArrayList<GameObj> getRiver() {
		return river;
	}

	public void setRiver(ArrayList<GameObj> river) {
		this.river = river;
	}

	public ArrayList<GameObj> getWall() {
		return wall;
	}

	public void setWall(ArrayList<GameObj> wall) {
		this.wall = wall;
	}
}