package weapon;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import common.GameConstants;

public class Weapon implements GameConstants{
	private int damage;
	protected int x, y;
	private boolean visible;
	private Image weaponImage;
	public Weapon(int x, int y, int damage, Image weaponImage){
		this.damage = damage;
		this.weaponImage = weaponImage;
		visible = true;
		this.x = x;
		this.y = y;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void hit(){
		visible = false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setWeaponImage(Image weaponImage){
		this.weaponImage = weaponImage;
	}
	
	public Image getWeaponImage(){
		return weaponImage;
	}
	
	public Rectangle getHitBox(){
		return new Rectangle(x, y, weaponImage.getWidth(null), weaponImage.getHeight(null));
	}
	public void drawWeapon(Graphics g){
		g.drawImage(getWeaponImage(), x, y, null);
	}
}
