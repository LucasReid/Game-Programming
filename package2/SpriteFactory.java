package package2;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteFactory {
	
	private static SpriteFactory instance=null;
	
	private HashMap<String, Sprite> spriteMap = new HashMap<>();//holds a key in a value used for indexing
	
	protected SpriteFactory(){// Exists to supress the no arg-custructor
		}
	
	public static SpriteFactory getInstance(){//singleton pattern VERY IMPORTANT!!!!!!!!
		if (instance==null)
			instance = new SpriteFactory();
		return instance;
	}
	/*
	 * steps for singleton pattern
	 * 1: suppress constructors
	 * 2:PROVIDE A static factory mehtod
	 */

	private void abend(String message){
		System.err.println(message);
		System.exit(0);
	}
	
	public Sprite getSprite(String resid, int imageCount){
		if(spriteMap.get(resid)!= null){
			return spriteMap.get(resid);
		}
		BufferedImage srcImage=null;
		
		try
		{
			URL url= this.getClass().getClassLoader().getResource(resid);
			if(url==null)
			{
				abend("cannot find"+ resid);
			}
			srcImage = ImageIO.read(url);
		
		}catch(IOException e){
			abend("Failed to load resouce -"+e.getMessage());
		}
		
		GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDefaultConfiguration();
		
		Image image = graphicsConfiguration.createCompatibleImage(srcImage.getWidth(), srcImage.getHeight(),Transparency.BITMASK);
		
		image.getGraphics().drawImage(srcImage, 0, 0, null);
		Sprite sprite = new Sprite(image, imageCount);
		spriteMap.put(resid, sprite);
		return sprite;//all this shit turns files into memory loadable images.......
		
		
		
	}
	
}


















