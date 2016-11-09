package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ShopController;
import domain.Customer;
import domain.DomainException;

public class CustomersPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810257151911564333L;

	private ShopController shopController;
	
	private JTextField searchTF;
	private JTable customersTable;
	
	public CustomersPanel(ShopController shopController){
		super();
		
		this.setBackground(new Color(255, 0, 0));
		
		this.shopController = shopController;
		List<Customer> customers;
		try {
			customers = shopController.getAllCustomers();
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"",JOptionPane.WARNING_MESSAGE);
		}
		
		Dimension dimension = new Dimension(600,600);
		this.setSize(dimension);
		setLayout(null);
		
		
	}
	
}
