package chapter_7;

import java.awt.Rectangle;

import javax.security.auth.Refreshable;

import chapter_7.GameEvent.Event;

public class AlienShip extends Entity{


	private final double DEFAULT_SPEED = 150;
	
	public AlienShip(String ref, int x, int y, GameController controller) {
		super(ref, x, y, controller);
		horizontalSpeed = DEFAULT_SPEED;
		// TODO Auto-generated constructor stub
	}
	public AlienShip(String ref, int imageCount, int x, int y, GameController controller){
		super(ref, imageCount, x, y, controller);
		horizontalSpeed = DEFAULT_SPEED;
	}
	
	
	public void setSpeed(int speed){
		horizontalSpeed=  speed;
	}
	@Override
	public void updateLocation( long elapsedTime){
		
		Rectangle rect = controller.getBounds();
		
		if(horizontalSpeed > 0 && locX > (rect.width - sprite.getWidth()+50))
			locX = -100;
		
		super.updateLocation(elapsedTime);	
	
	}
	
	public void doLogic() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return"alienShip";
	}
	

}
