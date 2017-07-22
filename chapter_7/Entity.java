package chapter_7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public abstract class Entity {
	
	protected final GameController controller;
	protected double locX, locY;
	protected Sprite sprite;
	protected final int imageCount;
	
	protected double horizontalSpeed, vertiacalSpeed;//instance variable automatically initialized(already known)
	private int index;
	private boolean active;
	private boolean debug=false;
	
	private int killPoints=1;
	
	public Entity(String ref, int imageCount, int x,int y, GameController controller){
		this.sprite = SpriteFactory.getInstance().getSprite(ref, imageCount);
		locX = x;
		locY=y;
		this.controller=controller;
		this.imageCount= imageCount;
	}
	
	public Entity(String ref, int x, int y, GameController controller){
		this(ref, 1, x, y, controller);
		
	}

	public double getLocx() {
		return locX;
	}

	public void setLocx(double locx) {
		this.locX = locx;
	}

	public double getLocy() {
		return locY;
	}

	public void setLocy(double locy) {
		this.locY = locy;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public double getHorizontalSpeed() {
		return horizontalSpeed;
	}

	public void setHorizontalSpeed(double horizontalSpeed) {
		this.horizontalSpeed = horizontalSpeed;
	}

	public double getVertiacalSpeed() {
		return vertiacalSpeed;
	}

	public void setVertiacalSpeed(double vertiacalSpeed) {
		this.vertiacalSpeed = vertiacalSpeed;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isActive() {
		return active;
	}

	public void activate(boolean active) {
		this.active = active;
	}

	public GameController getController() {
		return controller;
	}

	public int getImageCount() {
		return imageCount;
	}
	/**
	 * request that this entity updates its location based on specified time interval
	 * @param elapsedTime in  milliseconds
	 */
	public void updateLocation(long elapsedTime){
		locX += (elapsedTime * horizontalSpeed)/1000.0;
		locY += (elapsedTime * vertiacalSpeed)/1000.0;
	}
	
	public void draw(Graphics gc){
		if (active)//if the sprite is standing still it will not move because of this.
			index = (++index) % imageCount;
		if(debug){
			Color c = gc.getColor();
			gc.setColor(Color.CYAN);
			gc.drawRect((int)locX, (int)locY, getWidth(), getHeight());
			gc.setColor(c);
		}
		sprite.draw(gc, (int)locX, (int)locY, index);
		
	}
	
	public int getWidth(){
		return sprite.getWidth();
	}
	public int getHeight(){
		return sprite.getHeigh();
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)locX, (int)locY, getWidth(), getHeight());
	}
	
	public boolean detectCollision(Entity other){
		return getBounds().intersects(other.getBounds());
		
		
	}

	/**
	 * @return the killPoints
	 */
	public int getKillPoints() {
		return killPoints;
	}

	/**
	 * @param killPoints the killPoints to set
	 */
	public void setKillPoints(int killPoints) {
		this.killPoints = killPoints;
	}
	
	public abstract void doLogic();
}






























