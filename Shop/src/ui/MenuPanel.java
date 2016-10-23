package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import db.DbException;
import domain.Customer;
import domain.DomainException;
import domain.Product;
import domain.Shop;

public class MenuPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1078767362753449796L;
	
	private Shop shop;
	private ShopFrame frame;
	
	private JButton addProductButton;
	private JButton showProductButton;
	private JButton showRentalPriceButton;
	private JButton rentProductButton;
	private JButton returnProductButton;
	private JButton repairProductButton;
	private JButton deleteProductButton;
	private JButton addCustomerButton;
	private JButton quitButton;
	
	public MenuPanel(Shop shop,ShopFrame frame){
		this.shop=shop;
		this.frame = frame;
		
		this.addProductButton = new JButton("Add product");
		addProductButton.addActionListener(new AddProductListener());
		this.add(addProductButton);
		
		this.showProductButton = new JButton("Show Product");
		showProductButton.addActionListener(new ShowProductListener());
		this.add(showProductButton);
		
		this.showRentalPriceButton = new JButton("Show rental Price");
		showRentalPriceButton.addActionListener(new ShowRentalPriceListener());
		this.add(showRentalPriceButton);
		
		this.rentProductButton = new JButton("Rent product");
		rentProductButton.addActionListener(new RentProductButtonListener());
		this.add(rentProductButton);
		
		this.returnProductButton = new JButton("Return product");
		returnProductButton.addActionListener(new ReturnProductButtonListener());
		this.add(returnProductButton);
		
		this.repairProductButton = new JButton("Repair product");
		repairProductButton.addActionListener(new RepairProductButtonListener());
		this.add(repairProductButton);
		
		this.deleteProductButton = new JButton("Delete product");
		deleteProductButton.addActionListener(new DeleteProductButtonListener());
		this.add(deleteProductButton);
		
		this.addCustomerButton = new JButton("Add customer");
		addCustomerButton.addActionListener(new AddCustomerButtonListener());
		this.add(addCustomerButton);
		
		this.quitButton = new JButton("quit");
		quitButton.addActionListener(new QuitButtonListener());
		this.add(quitButton);
	}

	private class AddProductListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*String title = JOptionPane.showInputDialog("Enter the title:");
			int id;
			try{
				id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id:"));
				String type = JOptionPane.showInputDialog("Enter the type (M for movie/G for game):");
				Product p=null;
				
				if(type.toLowerCase().equals("m")){
					//p = new Movie(id,title,ProductStateEnum.RENTABLE);
				}else if(type.toLowerCase().equals("g")){
					//p= new Game(id,title,ProductStateEnum.RENTABLE);
				}
				
				
				
				
				shop.addProduct(p);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE);
			}*/
			
			frame.setContentPane(new AddProductPanel(shop,frame));
			frame.start();
		}
		
	}
	
	private class ShowProductListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("Enter the id:");
			
			Product p = null;
			try {
				p=shop.getProduct(Integer.parseInt(id));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (DbException ex) {
				JOptionPane.showMessageDialog(null,ex.getMessage(), "",JOptionPane.WARNING_MESSAGE);
			}
			
			if(p!=null)
			{
				JOptionPane.showMessageDialog(null, p.getTitle());
			}
		}
		
	}
	
	private class ShowRentalPriceListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("Enter the id:");
			
			Product p = null;
			try {
				p=shop.getProduct(Integer.parseInt(id));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (DbException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage() , "",JOptionPane.WARNING_MESSAGE);
			}
			
			
			if(p!=null){
				String daysString = JOptionPane.showInputDialog("Enter the number of days:");
				int days = Integer.parseInt(daysString);
				JOptionPane.showMessageDialog(null, p.getPrice(days));
			}
		}
		
	}
	private class RentProductButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("Enter the id:");
			
			try {
				shop.rentProduct(Integer.parseInt(id));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (DomainException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.WARNING_MESSAGE);
			} catch (DbException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage() , "",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	private class ReturnProductButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("Enter the id:");
			boolean damaged=JOptionPane.showConfirmDialog(null, "Is the item damaged?", "", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
			
			try {
				shop.returnProduct(Integer.parseInt(id),damaged);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (DomainException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.WARNING_MESSAGE);
			} catch (DbException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage() , "",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	private class RepairProductButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("Enter the id:");
			
			try {
				shop.repairProduct(Integer.parseInt(id));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (DomainException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.WARNING_MESSAGE);
			} catch (DbException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage() , "",JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
	}
	private class DeleteProductButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog("Enter the id:");
			
			try {
				shop.deleteProduct(Integer.parseInt(id));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (DomainException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.WARNING_MESSAGE);
			} catch (DbException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage() , "",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	private class AddCustomerButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			
			try {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Id:"));
				String firstName = JOptionPane.showInputDialog("First name:");
				String lastName = JOptionPane.showInputDialog("Last name:");
				String address = JOptionPane.showInputDialog("Address:");
				String zipCode = JOptionPane.showInputDialog("ZIP code:");
				String city = JOptionPane.showInputDialog("City:");
				String eMailAddress = JOptionPane.showInputDialog("EMail address:");
				boolean subscribed = JOptionPane.showConfirmDialog(null, "Subscribe to newsletter?", "", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
				Customer c = new Customer(id, firstName, lastName, address, zipCode, city, eMailAddress, subscribed);
				shop.addCustomer(c);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	private class QuitButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
		
	}
	
}
	
	
	
	
	

