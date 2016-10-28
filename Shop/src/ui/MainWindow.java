package ui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6807861903824838582L;
	
	public MainWindow(){
		this.setSize(new Dimension(200, 300));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start(){
		this.setVisible(true);
	}
}
