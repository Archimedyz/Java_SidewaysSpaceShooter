package sidewaysSpaceShooter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import common.CollisionHandler;
import common.GameConstants;
import enitity.Craft;
import enitity.Creep;
import enitity.Moving_Creep;

public class Main_Canvas extends JPanel implements Runnable, KeyListener, GameConstants{
	
	private Craft craft;
	private ArrayList<Creep> creeps;
	private Thread mainThread;
	private int level;
	private CollisionHandler collisionHandler;
	private File fontFile;
	private Font mainFont;
	private boolean running;
	public Main_Canvas(){
		init();
		loadScreen();
		addKeyListener(this);
		collisionHandler = new CollisionHandler();
		initCollisions();
	}
	
	private void init(){
		running = true;
		fontFile = new File(FONT_PATH);
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		}catch (Exception e) {
			System.out.println("Main_Canvas: mainFont > init()");
		}
		mainFont = mainFont.deriveFont(12.0f);
	}
	
	private void loadScreen(){
		craft = new Craft(STARTX, STARTY, STARTHP);
		creeps = new ArrayList<Creep>();
		level = 0;
		initCreeps();
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		setDoubleBuffered(true);
		paint(this.getGraphics());
		
	}
	
	private void initCollisions(){
		collisionHandler.addCollision(creeps, craft.getMissles());
		//collisionHandler.addCollision(, craft.getMissles());
		// add more collisions here
	}
	
	private void initCreeps(){
		if(creeps.isEmpty()){
			switch(level){
			case 0:
				System.out.println("here1");
				creeps.add(new Creep(225, 75, 10, CREEP_IMAGENAME));
				creeps.add(new Creep(225, 150, 20, CREEP_IMAGENAME));
				creeps.add(new Creep(225, 225, 30, CREEP_IMAGENAME));
				creeps.add(new Moving_Creep(50, 2,MOVING_CREEP_IMAGENAME, CREEP_PATH_1));
				//creeps.add(new Moving_Creep(50, 3,MOVING_CREEP_IMAGENAME, CREEP_PATH_1));
				break;
			case 1:
				creeps.add(new Moving_Creep(50, 2,MOVING_CREEP_IMAGENAME, ERIC_PATH));
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case 8:
				
				break;
			case 9:
				
				break;
			default:
				running = false;
			}
			level++;
		}
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
        mainThread = new Thread(this);
        mainThread.start();
    }
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(mainFont);
		g.drawString("LEVEL " + level, 10, 20);
		craft.drawCraft(g);
		for(int i = 0; i < creeps.size(); i++){
			creeps.get(i).drawCreep(g);
		}
		g.dispose();
	}
	private void cycle(){
		craft.deployActions();
		craft.move(this.getWidth(), this.getHeight());
		for(int i = 0; i < creeps.size(); i++){
			if(!creeps.get(i).isVisible()){
				creeps.remove(i);	
			}else{
				creeps.get(i).move();
			}
		}
		collisionHandler.collisionCheck();
		initCreeps();
	}

	public void run() {
		//delta = timeDiff
		long beforeTime, delta, sleep;
		beforeTime = System.currentTimeMillis();
		
		while(running){
			cycle();
			repaint();
			requestFocus();
			
			delta = System.currentTimeMillis() - beforeTime;
			sleep = DELAY-delta;
			
			if(sleep < 2){
				sleep = 2;
			}
			
			try {
	            Thread.sleep(sleep);
	        } catch (InterruptedException e) {
	        	System.out.println("Interrupted: " + e.getMessage());
	        }			
			beforeTime = System.currentTimeMillis();
		}
		
	}

	public void keyPressed(KeyEvent e) {
		craft.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		craft.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		craft.keyTyped(e);
	}

}
