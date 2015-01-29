package common;

public interface GameConstants {
	int CANVAS_WIDTH = 400;
	int CANVAS_HEIGHT = 300;
	int DELAY = 25;
	int STARTX = 50;
	int STARTY = CANVAS_HEIGHT/2;
	int STARTHP = 100;
	int GUN_DAMAGE = 5;
	int GUN_SPEED = 9;
	int MISSLE_DAMAGE = 10;
	int MISSLE_SPEED = 6;
	int LASER_DAMAGE = 40;
	
	// image file names
	String CREEP_IMAGENAME = "creep";
	String MOVING_CREEP_IMAGENAME = "m_creep";
	
	// path file names
	String CREEP_PATH_1 = "res/test2D_res/info/creep_paths/creep_path_1.txt";
	String ERIC_PATH = "res/test2D_res/info/creep_paths/eric_path.txt";
	String FONT_PATH = "res/test2D_res/font/emulogic.ttf";
}
