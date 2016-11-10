package view.panels;

import java.awt.Color;

import javax.swing.JPanel;

import controller.ShopController;
import controller.event.MainWindowChangedFiringSource;

public class ProductsOverviewPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2434848969986287009L;
	
	public ProductsOverviewPanel(ShopController shopController,MainWindowChangedFiringSource listener){
		super();
		setBackground(new Color(0, 255, 0));
	}

}
