package controller.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import controller.ShopController;
import view.panels.CustomersPanel;
import view.panels.ProductsPanel;

/**
 * 
 * @author Huybrechts
 *
 */


public class MainWindowChangedFiringSource implements WindowChangedFiringService,ActionListener{

	
	
	private List<WindowChangedService> listeners;
	private ShopController shopController;
	
	public MainWindowChangedFiringSource(ShopController shopController) {
		listeners = new ArrayList<WindowChangedService>();
		this.shopController = shopController;
	}
	
	@Override
	public void addListener(WindowChangedService listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(WindowChangedService listener) {
		listeners.remove(listener);
	}

	@Override
	public void fireChanged(JPanel panel) {
		for(WindowChangedService listener : listeners){
			listener.fireChanged(panel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		EventEnum eventEnum = null;
		
		for(EventEnum event : EventEnum.values()){
			if(event.getActionCommand().equals(arg0.getActionCommand())){
				eventEnum = event;
				break;
			}
		}
		
		switch (eventEnum) {
		case PRODUCTSBUTTONLISTENER:
			fireChanged(new ProductsPanel(this.shopController));
			break;
		
		case CUSTOMERSBUTTONLISTENER:
			fireChanged(new CustomersPanel(this.shopController));
			break;
		case QUITBUTTONLISTENER:
			System.exit(1);
			break;
		default:
			break;
		}
	}
}
