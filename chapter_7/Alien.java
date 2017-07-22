package chapter_7;

import java.awt.Rectangle;


import chapter_7.GameEvent.Event;

public class Alien extends Entity {
	private final double DEFAULT_SPEED = -80;
	
	public Alien(String ref, int x, int y, GameController controller) {
		super(ref, x, y, controller);
		horizontalSpeed = DEFAULT_SPEED;
	}
	
	public Alien(String ref, int imageCount, int x,int y,GameController controller){
		super(ref, imageCount, x, y,controller);
		horizontalSpeed = DEFAULT_SPEED;
	}
	public void setSpeed(int speed){
		horizontalSpeed = speed;
	}
	@Override
	public void updateLocation(long elapsedTime){//left side of the screen
		if((horizontalSpeed <0) && (locX <10)){
			controller.onGameEvent(new GameEvent(this, Event.LOGIC_REQUIRED));
		}
		Rectangle rect = controller.getBounds();//what is the size of the screen?
		if ((horizontalSpeed >0)&&(locX > rect.width - 50)){//right side of screen
			controller.onGameEvent(new GameEvent(this, Event.LOGIC_REQUIRED));
		}
		super.updateLocation(elapsedTime);
		
	}
	
	
	
	@Override
	public void doLogic() {
		horizontalSpeed = - horizontalSpeed;
		locY += 10;
		Rectangle rect = controller.getBounds();
		if (locY > rect.height - 50)
			controller.onGameEvent(new GameEvent(this, Event.NOTIFY_LOST));
	}
	@Override
	public String toString(){
		return "Alien";
	}

}
