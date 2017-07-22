package chapter_7;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {

	protected final Image image;
	private final int imageCount;

	public Sprite(Image image) {
		this(image, 1);
	}

	public Sprite(Image image, int imageCount) {
		this.imageCount = imageCount;
		this.image = image;

	}

	public int getWidth() {
		return image.getWidth(null) / imageCount;
	}

	public int getHeigh() {
		return image.getHeight(null);// the null can be specified as an image
										// observer;
	}

	public void draw(Graphics gc, int x, int y, int index) {
		if (imageCount == 1) {
			return;
		}

		int sx1 = getWidth() * index;// where do i start the draw?
		int sx2 = sx1 + getWidth();// how fast will it go?

		gc.drawImage(image, x, y, x + getWidth(), y + getHeigh(), sx1, 0, sx2,
				getHeigh(), null);
		// takes image, the destination (x,y), source top left and right, then
		// bottom left and right! and the observer is blank

	}

	public void draw(Graphics gc, int x, int y) {
		draw(gc, x, y, 0);
	}

	public void draw(Graphics gc, int dx, int dy, int dWidth, int dHeight, int index) {
		int sx1 = getWidth() * index;
		int sx2 = sx1 + getWidth();
		
		gc.drawImage(image, dx, dy, dx+dWidth, dy+dHeight, sx1, 0, sx2, getHeigh(), null);
	}

	public void draw(Graphics gc, int dx, int dy, int dWidth, int dHeight) {
		gc.drawImage(image, dx, dy, dx + dWidth, dy + dHeight, 0, 0,
				getWidth(), getHeigh(), null);
	}

}
