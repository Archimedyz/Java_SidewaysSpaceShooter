package enitity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import weapon.Weapon;
import common.GameConstants;

public class Creep extends Entity implements GameConstants{
	int creepState;
	Image hitImage;
	
	public Creep(int x, int y, int hp, String imagename){
		super(x, y, hp);
		creepState = 0;
		ImageIcon temp = new ImageIcon("res/test2D_res/img/"+ imagename +".png");
		setEntityImage(temp.getImage());
		temp = new ImageIcon("res/test2D_res/img/" + imagename + "_hit.png");
		hitImage = temp.getImage();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public void doDamage(Weapon w){
		hp -= w.getDamage();
		w.hit();
		creepState = 2;
	}
	public void drawCreep(Graphics g){
		if(hp <= 0 && creepState == 0){
			visible = false;
			return;
		}
		if(creepState <= 0){
			g.drawImage(entityImage, x, y, null);
		} else {
			g.drawImage(hitImage, x, y, null);
			creepState--;
		}
		
	}
	public void move(){}
}
