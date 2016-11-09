package controller.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ShopController;
import domain.DomainException;
import view.panels.CustomersOverviewPanel;
import view.panels.ProductsOverviewPanel;

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
			fireChanged(new ProductsOverviewPanel(this.shopController));
			break;
		
		case CUSTOMERSBUTTONLISTENER:
			try {
				fireChanged(new CustomersOverviewPanel(this.shopController));
			} catch (DomainException e) {
				JOptionPane.showMessageDialog(null, "Couldn't open the customers window.","",JOptionPane.WARNING_MESSAGE);
			}
			break;
		case QUITBUTTONLISTENER:
			System.exit(1);
			break;
		default:
			break;
		}
	}
}
