package view.custom;

import javax.swing.JButton;

import controller.event.EventEnum;

public class Button extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4914731228304780362L;

	public Button(String arg){
		super(arg);
	}
	
	public void setActionCommand(EventEnum event){
		super.setActionCommand(event.getActionCommand());
	}
	
}
