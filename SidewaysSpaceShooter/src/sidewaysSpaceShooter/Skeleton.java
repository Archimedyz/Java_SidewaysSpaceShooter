package sidewaysSpaceShooter;

import javax.swing.JFrame;

public class Skeleton extends JFrame{
	Main_Canvas canvas;
	public Skeleton(){
		canvas = new Main_Canvas();
		add(canvas);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Craft vs Creeps");
	}

	public static void main(String[] args) {
		Skeleton s = new Skeleton();
		s.setVisible(true);
		s.setFocusable(true);
	}

}
