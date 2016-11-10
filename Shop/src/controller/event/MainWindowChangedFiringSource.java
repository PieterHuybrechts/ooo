package controller.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ShopController;
import domain.DomainException;
import view.panels.CustomerAddPanel;
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
		if(eventEnum!=null){
			switch (eventEnum) {
			case PRODUCTSBUTTONEVENT:
				fireChanged(new ProductsOverviewPanel(this.shopController,this));
				break;
			case CANCELADDCUSTOMERBUTTONEVENT:
			case OKADDCUSTOMERBUTTONEVENT:
			case CUSTOMERSBUTTONEVENT:
				try {
					fireChanged(new CustomersOverviewPanel(this.shopController,this));
				} catch (DomainException e) {
					JOptionPane.showMessageDialog(null, "Couldn't open the customers window.","",JOptionPane.WARNING_MESSAGE);
				}
				break;
			case QUITBUTTONEVENT:
				System.exit(1);
				break;
			case ADDCUSTOMERBUTTONEVENT:
				fireChanged(new CustomerAddPanel(this.shopController,this));
				break;
			default:
				break;
			}
		}
		
	}
}
