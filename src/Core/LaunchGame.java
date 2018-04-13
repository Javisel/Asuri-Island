package Core;

import javax.swing.JFrame;

public class LaunchGame {

	public static void main(String args[]) {
		  Testrun T = new Testrun(); //create new object
		    T.init(); //invoke the applet's init() method
		    T.start(); //starts the applet
		    // Create a window (JFrame) and make applet the content pane.
		    JFrame window = new JFrame("Asuri Island the Game"); 
		    window.setSize(1074, 626); //size in pixels
		    window.setContentPane(T); //
		    window.setVisible(true);
		    window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}


	
}
