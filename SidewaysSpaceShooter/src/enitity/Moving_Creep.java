package enitity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Moving_Creep extends Creep{
	private int movementSpeed, dx, dy, x_speed, y_speed;
	private File movementFile;
	private Scanner pathReader;
	private boolean entering_screen, toright, tobottom;
	public Moving_Creep(int hp, int speed, String imagename, String filename){
		super(-100, -100, hp, imagename);
		entering_screen = true;
		movementSpeed = speed;
		dx = dy = 0;
		movementFile = new File(filename);
		try{
			pathReader = new Scanner(movementFile);
		} catch(FileNotFoundException e){
			System.out.println("Moving_Creep: pathReader > Constructor");
		}
		updatePath();
		updateSpeeds();
	}
	
	@Override
	public void move(){
		x += x_speed;
		y += y_speed;
		if((x_speed != 0) && ((toright && x >= dx) || (!toright && x <= dx))){// || (tobottom && y >= dy) || (!tobottom && y <= dy)){
			updatePath();
			updateSpeeds();
		} else if((y_speed != 0) && ((tobottom && y >= dy) || (!tobottom && y <= dy))){
			updatePath();
			updateSpeeds();
		}
	}
	
	private void updateSpeeds(){
		int x_dist = Math.abs(x-dx);
		int y_dist = Math.abs(y-dy);
		int hyp = (int) Math.sqrt((x_dist*x_dist + y_dist*y_dist));
		
		x_speed = (movementSpeed*x_dist)/ hyp;
		y_speed = (movementSpeed*y_dist)/ hyp;
		
		if(x > dx){
			x_speed = -x_speed;
		}
		if(y > dy){
			y_speed = -y_speed;			
		}
	}
	
	private void updatePath(){
		if(pathReader.hasNextLine()){
			String l = pathReader.nextLine();
			if(l.contains("+")){
				if(entering_screen){
					l = pathReader.nextLine();
					String[] temp = l.split(",");
					x = Integer.parseInt(temp[0]);
					y = Integer.parseInt(temp[1]);
					updatePath();
					entering_screen =  false;
				} else {
					while(!l.contains("-")){
						l = pathReader.nextLine();
					}
				}
			} else if(l.contains("-")){
				l = pathReader.nextLine();
				String[] temp = l.split(",");
				dx = Integer.parseInt(temp[0]);
				dy = Integer.parseInt(temp[1]);
				toright = (x < dx);
				tobottom = (y < dy);
			} else if(l.contains(",")){
				String[] temp = l.split(",");
				dx = Integer.parseInt(temp[0]);
				dy = Integer.parseInt(temp[1]);
				toright = (x < dx);
				tobottom = (y < dy);
			} else {
				updatePath();
			}
		}else{
			pathReader.close();
			try{
				pathReader = new Scanner(movementFile);
			} catch(FileNotFoundException e){
				System.out.println("Moving_Creep: pathReader > Constructor");
			}
			updatePath();
		}
	}
}
