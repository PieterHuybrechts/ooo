package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import db.DbException;
import domain.Game;
import domain.Movie;
import domain.Product;
import domain.ProductStateEnum;
import domain.Shop;

public class MenuPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1078767362753449796L;
	
	private Shop shop;
	
	private JButton addProductButton;
	private JButton showProductButton;
	private JButton showRentalPriceButton;
	private JButton rentProductButton;
	private JButton returnProductButton;
	private JButton repairProductButton;
	private JButton deleteProductButton;
	private JButton addCustomerButton;
	private JButton quitButton;
	
	public MenuPanel(Shop shop){
		this.shop=shop;
		
		this.addProductButton = new JButton("Add product");
		this.showProductButton = new JButton("Show Product");
		this.showRentalPriceButton = new JButton("Show rental Price");
		this.rentProductButton = new JButton("Rent product");
		this.returnProductButton = new JButton("Return product");
		this.repairProductButton = new JButton("Repair product");
		this.deleteProductButton = new JButton("Delete product");
		this.addCustomerButton = new JButton("Add customer");
		this.quitButton = new JButton("quit");
		
		//addProductButton.addActionListener(l);
		
		this.add(addProductButton);
		this.add(showProductButton);
		this.add(showRentalPriceButton);
		this.add(rentProductButton);
		this.add(returnProductButton);
		this.add(repairProductButton);
		this.add(deleteProductButton);
		this.add(addCustomerButton);
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
					p = new Movie(id,title,ProductStateEnum.RENTABLE);
				}else if(type.toLowerCase().equals("g")){
					p= new Game(id,title,ProductStateEnum.RENTABLE);
				}
				
				this.shop.addProduct(p);
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
			} catch (DbException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"",JOptionPane.WARNING_MESSAGE);
			}
			
		}*/
		
	}
	
	private class ShowProductListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ShowRentalPriceListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class RentProductButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class addProductListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class addProductListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class addProductListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class addProductListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class addProductListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
