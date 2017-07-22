/**
 * 
 */
package chapter_7;

import java.awt.Rectangle;

/**
 * @author lnr7605
 *
 */
public class SpaceShip extends Entity {

	
	public SpaceShip(String ref, int x, int y, GameController controller) {
		super(ref, x, y, controller);
		// TODO Auto-generated constructor stub
	}
	public SpaceShip(String ref, int imageCount,int x , int y, GameController controller) {
		super(ref,imageCount, x, y, controller);
	}
	@Override

	public void updateLocation( long elapsedTime){
		//Hit left edge
		if(horizontalSpeed<0 && locX<10)
			return;
		
		
		Rectangle rect = controller.getBounds();
		
		
		//Hit right edge
		if( (horizontalSpeed >0) && (locX>(rect.width - sprite.getWidth()-5)))
			return;
		
		super.updateLocation(elapsedTime);
	}
	
	
	/* (non-Javadoc)
	 * @see package2.Entity#doLogic()
	 */
	@Override
	public void doLogic() {
		// TODO Auto-generated method stub

	}

	
	
}



























