package package2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements GameController {

	private static final long serialVersionUID = 1L;

	private BufferStrategy strategy;

	private static final int WIDTH = 800, HEIGHT = 600;

	// key definitions
	private final byte UP_KEY = 0x08;
	private final byte LEFT_KEY = 0x04;
	private final byte DOWN_KEY = 0x02;
	private final byte RIGHT_KEY = 0x01;
	private byte keys = 0x0;
	private String message="";
	// timing
	private Timer timer;
	private long lastLoopTime;
	private int fps = 60;
	private long refreshInterval = 0;

	// flags
	private boolean notRunning = true;
	private boolean dispose;
	private boolean logicRequiredThisLoop;

	// Shipe parameters
	private Entity ship;
	private double shipMinSpeed = 50.0;
	private double shipMaxSpeed = 500.0;
	private double shipSpeed = shipMinSpeed;

	// actor lists
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Entity> removeList = new ArrayList<>();

	private Canvas canvas;

	public Game() {
		super("Chapter 2 - The Ship");
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(canvas);

		setSize(WIDTH, HEIGHT);

		canvas.setIgnoreRepaint(true);
		canvas.setFocusable(false);

		setResizable(false);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		addKeyListener(new KeyBoardHandler());

		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();

		initEntities();

		timer = new Timer((int) (1000.0 / fps), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				render();
			}

		});
		lastLoopTime = System.currentTimeMillis();
		
	}

	private void render() {
		if(dispose)
		dispose();
		
		Graphics2D gc = (Graphics2D) strategy.getDrawGraphics();
		gc.setColor(Color.black);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
	
		if(notRunning){
			gc.setColor(Color.white);
			gc.drawString(message,
					(WIDTH-gc.getFontMetrics().stringWidth(message))/2, 
					HEIGHT/2);
			
			String str = "Press any key";
			
			gc.drawString(str,
					(WIDTH-gc.getFontMetrics().stringWidth(str))/2, 
					HEIGHT/2 + 50);
		}
		else{
			processKeysAndRelocateActors();
		}
		for(Entity entity : entities)
			entity.draw(gc);
			
		entities.removeAll(removeList);
		removeList.clear();
		
		if(logicRequiredThisLoop){
			for(Entity entity : entities)
				entity.doLogic();
				logicRequiredThisLoop=false;				
		}
		
		gc.dispose();
		strategy.show();
		
		lastLoopTime = System.currentTimeMillis();
		
	}
	private void processKeysAndRelocateActors() {
		refreshInterval = System.currentTimeMillis()- lastLoopTime;
		
		
		//actor processing
		setShipDirection();
		
		// move other actors
		for(Entity entity : entities)
			entity.updateLocation(refreshInterval);
	}



	private void setShipDirection() {
		ship.setHorizontalSpeed(0);
		
		//A
		if((keys & LEFT_KEY) != 0x0)
			ship.setHorizontalSpeed(-shipSpeed);
		
		//D
		if((keys & RIGHT_KEY) != 0x0)
			ship.setHorizontalSpeed(shipSpeed);
		
		if((keys & (LEFT_KEY | RIGHT_KEY)) !=0){
			if(shipSpeed < shipMaxSpeed)
				shipSpeed += 10;
		} else{
			shipSpeed = shipMinSpeed;
		}
			
	}



	private class KeyBoardHandler extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				keys &= ~UP_KEY;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				keys &= ~LEFT_KEY;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				keys &= ~DOWN_KEY;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				keys &= ~RIGHT_KEY;
				break;
			}

		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					keys |= UP_KEY;
					break;
			case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					keys |= LEFT_KEY;
					break;
			case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					keys |= DOWN_KEY;
					break;
			case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					keys |= RIGHT_KEY;
					break;
			case KeyEvent.VK_ESCAPE:
				dispose=true;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if(notRunning){
				notRunning = false;
				startGame();
				for(Entity entity:entities)
					entity.activate(true);
			}
			if(e.getKeyChar()==KeyEvent.VK_ESCAPE)
				dispose=true;
		}

	}

	private void startGame() {
		entities.clear();		
		initEntities();
	}
	private void initEntities() {
		ship=  new SpaceShip("sprites/shipSpriteSheet.png", 3, WIDTH/2 - 30, HEIGHT - 70, this);
		shipSpeed = 0;
		entities.add(ship);
		for(Entity entity:entities)
			entity.activate(false);
	}
	
	private void removeEntities(Entity e){
		removeList.add(e);
	}
	
	public void start(){
		timer.start();
	}
	
	public void dispose(){
		super.setVisible(false);
		super.dispose();
		timer.start();
		System.exit(0);
	}
	
	@Override
	public void onGameEvent(GameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		return super.getBounds();
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.start();
	}

}
