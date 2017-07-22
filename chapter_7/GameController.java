/**
 * 
 */
package chapter_7;

import java.awt.Rectangle;

/**
 * @author lnr7605
 *
 */
public interface GameController {
	public void onGameEvent(GameEvent e);
	public Rectangle getBounds();	
}
