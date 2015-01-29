package enitity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import weapon.Missle;

public class Craft extends Entity implements KeyListener{
	
	private ArrayList<Missle> missles;
	private boolean[] keyPressedList;
	private final int FIRE_INTERVAL_MILLIS = 250;
	private int dx, dy;
	private long prevFireTime;
	
	private int hp;
	
	public Craft(int x, int y, int hp){
		super(x, y, hp);
		ImageIcon temp = new ImageIcon("res/img/craft.png");
		setEntityImage(temp.getImage());
		missles = new ArrayList<Missle>();
		keyPressedList = new boolean[5];
		prevFireTime = System.currentTimeMillis();
	}
	
	public ArrayList<Missle> getMissles(){
		return missles;
	}
	
	public void drawCraft(Graphics g){
		g.drawImage(entityImage, x, y, null);
		for(int i = 0; i < missles.size(); i++){
			missles.get(i).drawWeapon(g);
		}
	}
	public void move(int xBoundary, int yBoundary){
		if((dx < 0 && x > 0) || (dx > 0 && x < (xBoundary - entityImage.getWidth(null)))){
			x += dx;
		}
		if((dy < 0 && y > 0) || (dy > 0 && y < (yBoundary - entityImage.getHeight(null)))){
			y += dy;
		}
		for(int i = 0; i < missles.size(); i++){
			missles.get(i).moveMissle();
			if(missles.get(i).getX() > xBoundary || !missles.get(i).isVisible()){
				missles.remove(i);
			}
		}
		dx = dy = 0;
	}
	
	private void fireMissle(){
		long currTime = System.currentTimeMillis();
		if(currTime - prevFireTime > FIRE_INTERVAL_MILLIS){
			prevFireTime = currTime;
			missles.add(new Missle(x + entityImage.getWidth(null), y + (entityImage.getHeight(null)/2)));
		}
	}
	public void deployActions(){
		if(keyPressedList[0]){
			dy = -3;
		}
		if(keyPressedList[1]){
			dy = 3;
		}
		if(keyPressedList[2]){
			dx = -3;
		}
		if(keyPressedList[3]){
			dx = 3;
		}
		if(keyPressedList[4]){
			fireMissle();
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		/*
		 * 0 = up
		 * 1 = down
		 * 2 = left
		 * 3 = right
		 * 4 = z key
		 */
		if(key == KeyEvent.VK_UP){
			keyPressedList[0] = true;
			keyPressedList[1] = false;
		}
		if(key == KeyEvent.VK_DOWN){
			keyPressedList[1] = true;
			keyPressedList[0] = false;
		}
		if(key == KeyEvent.VK_LEFT){
			keyPressedList[2] = true;
			keyPressedList[3] = false;
		}
		if(key == KeyEvent.VK_RIGHT){
			keyPressedList[3] = true;
			keyPressedList[2] = false;
		}
		if(key == KeyEvent.VK_Z){
			keyPressedList[4] = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP){
			keyPressedList[0] = false;
		}
		if(key == KeyEvent.VK_DOWN){
			keyPressedList[1] = false;
		}
		if(key == KeyEvent.VK_LEFT){
			keyPressedList[2] = false;
		}
		if(key == KeyEvent.VK_RIGHT){
			keyPressedList[3] = false;
		}
		if(key == KeyEvent.VK_Z){
			keyPressedList[4] = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_SPACE: fireMissle(); break;
		}
	}
}
