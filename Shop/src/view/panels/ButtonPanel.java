package view.panels;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import controller.event.EventEnum;
import controller.event.MainWindowChangedFiringSource;
import view.custom.Button;

public class ButtonPanel extends JPanel{
	private static final long serialVersionUID = -6358979366202454017L;
	
	Button blueButton;
	Button redButton;
	Button greenButton;
	
	public ButtonPanel(){
		super();
		
		MainWindowChangedFiringSource listener = MainWindowChangedFiringSource.getInstance();
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 50,100,50 };
		int numberOfRows=14;
		int[] heights=new int[numberOfRows];
		int windowHeight=580;
		for(int i=0;i<numberOfRows;i++){
			heights[i]=windowHeight/numberOfRows;
		}
		gbl.rowHeights = heights;
		setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		blueButton = new Button("Products");
		blueButton.addActionListener(listener);
		blueButton.setActionCommand(EventEnum.PRODUCTSBUTTONLISTENER);
		add(blueButton,gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		redButton = new Button("Customers");
		redButton.addActionListener(listener);
		redButton.setActionCommand(EventEnum.CUSTOMERSBUTTONLISTENER);
		add(redButton,gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		greenButton = new Button("Quit");
		greenButton.addActionListener(listener);
		greenButton.setActionCommand(EventEnum.QUITBUTTONLISTENER);
		add(greenButton,gbc);
	}
}
