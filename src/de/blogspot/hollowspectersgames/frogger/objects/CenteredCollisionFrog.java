package de.blogspot.hollowspectersgames.frogger.objects;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;


public class CenteredCollisionFrog extends GameObj{

	protected float halfWidth;
	protected float halfHeight;
	
	public CenteredCollisionFrog(float posX, float posY, float spdX, float spdY) {
		
		super(posX, posY, spdX, spdY, "frog");
	}
	
	public void init(GameContainer container) throws SlickException {
		
		super.init(container);
		halfWidth = img.getWidth() / 2.0f;
        halfHeight= img.getHeight() / 2.0f;
        
        collisionShape = new Ellipse(0,0,10,12);
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		
		super.update(container, delta);

		if (collisionOn == true)
		{
	    	// Kollision mit den Rändern prüfen
	    	float randLinks = halfWidth;
	    	float randRechts = container.getWidth() - halfWidth;
	    	float randOben = halfHeight + 30;
	    	float randUnten = container.getHeight() - halfHeight - 50;
	    	
	    	// Kollision mit linkem Rand -> spdX positiv setzen -> Bewegung nach rechts
	    	if (posX < randLinks) {
	    		spdX = Math.abs(spdX);
	    		posX = randLinks;
	    	}
	
	    	// Kollision mit rechtem Rand -> spdX negativ setzen -> Bewegung nach links
	    	if (posX > randRechts) {
	    		spdX = -Math.abs(spdX);
	    		posX = randRechts;
	    	}
	    	
	    	// Kollision mit oberem Rand -> spdY positiv setzen -> Bewegung nach unten
	    	if (getPosY()  < randOben) {
	    		setSpdY(Math.abs(getSpdY()));
	    		setPosY(randOben);
	    	}
	
	    	// Kollision mit unterem Rand -> spdY negativ setzen -> Bewegung nach oben
	    	if (getPosY()  > randUnten) {
	    		setSpdY(-Math.abs(getSpdY()));
	    		setPosY(randUnten);
	    	}
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
//		g.draw(collisionShape);
		img.drawCentered(posX, getPosY());
	}
	
}
