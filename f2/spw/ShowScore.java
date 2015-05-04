package f2.spw;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ShowScore extends JFrame
{
	public ShowScore(GameEngine gm)
	{
		super("Game Over");	
    	// create the GUI
		Container c = getContentPane();
		JLabel label = new JLabel("Your level is "+gm.getLv() +", score is "+gm.getScore());
		c.add(label);
    	// set close behaviour for JFrame as exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(200, 100);
    	// pack();   // reduce size to fit GUI components
		setVisible(true);
	} 
}