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
	
	private ShopController shopController;
	
	public ProductsOverviewPanel(ShopController shopController,MainWindowChangedFiringSource listener){
		super();
		this.shopController = shopController;
		setBackground(new Color(0, 255, 0));
	}

}
