package de.blogspot.hollowspecter.frogger.other;

public class Constants {
	
	//Alle Autos
	public static final float[] LANEPOS0 = {300f, 600f, 750f};
	public static final float[] LANEPOS1 = {-100f, -230f};
	public static final float[] LANEPOS2 = {-100f, -300f};
	public static final float[] LANEPOS3 = {500f, 750f, 1000f};
	public static final float[] LANEPOS4 = {400f, 140f, -100f};

	//Geschwindigkeit der Autos
	public static final float LANESPD0 = -150f;
	public static final float LANESPD1 = 80f;
	public static final float LANESPD2 = 300f;
	public static final float LANESPD3 = -150f;
	public static final float LANESPD4 = 60f;
	
	//Alle Baumstämme
	public static final float[] LANEPOS6 = {-50f, -200f, -600f};
	public static final float[] LANEPOS7 = {650f, 800f};
	public static final float[] LANEPOS8 = {-50f, -300f, -620f};
	public static final float[] LANEPOS9 = {734f, 1080f};
	public static final float[] LANEPOS10 = {-300f, -555f, -1000f};
	
	//Geschwindigkeiten der Baumstämme
	public static final float LANESPD6 = 100f;
	public static final float LANESPD7 = -200f;
	public static final float LANESPD8 = 150f;
	public static final float LANESPD9 = -90f;
	public static final float LANESPD10 = 100f;
	
	//X positionen von Rupees ganz hinten
	public static final int[] RUPEEPOS = {63, 166, 268, 370, 475};
	public static final int RUPEEPOSLASTY = 53;
	
	//0%-x% = Wert1; x%-y% = Wert-1, y%-100% = Wert3
	public static final double[] RUPEECHANCES =  {0.5,0.75};
	
	public static final int FROGSPEED = 200;
	
	//Sounds
	public static final String SOUND_PAUSE = "sfx/sfx_pause.wav";
	public static final String SOUND_UNPAUSE = "sfx/sfx_pause.wav";
	public static final String SOUND_DIE = "sfx/sfx_die.wav";
	public static final String SOUND_GAMEOVER = "sfx/sfx_gameover.wav";
	public static final String SOUND_GIVERUPEES = "sfx/sfx_giverupees.wav";
	public static final String SOUND_SELECT = "sfx/sfx_select.wav";
	public static final String SOUND_PICKUP = "sfx/sfx_pickup.wav";

	public static final String SOUND_MENUBG ="sfx/Spazzmatica Polka.wav";
	public static final String SOUND_PLAYINGBG = "sfx/Theme for Harold var 1.wav";
	public static final String SOUND_GAMEOVERBG = "sfx/8bit Dungeon Level.wav";
	
	//Strings
	//String at top to guide a little in the beginning
	public static final String GUIDE1 = "Try to fetch some of those Rupees!";
	public static final String GUIDE2 = "Bring them back to your wife now!";
	public static final String GUIDE3 = "And now get some more!";
	public static final String GUIDE4 = "If the time runs out, you die!";
	public static final String GUIDE5 = "Try to get as many as you can!";

	public static float getPosYWithLane(int lane)
	{
		return 563.0f - 45.0f*lane;
	}
	
	/*
	 * Score idee:
	 * Rupee * Zeit die übrig geblieben ist!
	 */
	
}
