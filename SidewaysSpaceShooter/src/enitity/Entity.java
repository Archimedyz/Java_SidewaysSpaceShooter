package enitity;

import java.awt.Image;
import java.awt.Rectangle;

import weapon.Weapon;

public class Entity {
	protected Image entityImage;
	protected int x, y, hp;
	protected boolean visible;
	
	public Entity(int x, int y, int hp){
		this.x = x;
		this.y = y;
		this.hp = hp;
		visible = true;
	}
	
	public void setEntityImage(Image i){
		entityImage = i;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return y;
	}
	public void setHP(int hp){
		this.hp = hp;
	}
	public int getHP(){
		return hp;
	}
	public Rectangle getHitBox(){
		return new Rectangle(x, y, entityImage.getWidth(null), entityImage.getHeight(null));
	}
	public boolean isVisible(){
		return visible;
	}
	public void doDamage(Weapon w){
		hp -= w.getDamage();
		w.hit();
	}
}
