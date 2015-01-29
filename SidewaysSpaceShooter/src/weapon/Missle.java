package weapon;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Missle extends Weapon{
	int speed = 6;
	boolean visible;
	public Missle(int x, int y){
		super(x, y, MISSLE_DAMAGE, null);
		ImageIcon temp = new ImageIcon("res/test2D_res/img/missle.png");
		setWeaponImage(temp.getImage());
	}
	
	public void moveMissle(){
		x += speed;
	}
}
