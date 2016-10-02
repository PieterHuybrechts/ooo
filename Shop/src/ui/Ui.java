package ui;

import javax.swing.JOptionPane;

import domain.DomainException;
import domain.Game;
import domain.Movie;
import domain.Product;
import domain.ProductStateEnum;
import domain.Shop;

public class Ui {
	
	private Shop shop;
	
	public Ui(Shop shop) throws DomainException{
		setShop(shop);
		showMenu();
	}
	
	private void setShop(Shop shop){
		this.shop=shop;
	}
	
	private void showMenu() throws DomainException{
		String menu = "1. Add product\n2. Show product\n3. Show rental price\n4. Rent Product\n5. Return Product\n6. Repair Product\n7. Delete Product\n\n0. Quit";
		int choice = -1;
		while (choice != 0) {
			String choiceString = JOptionPane.showInputDialog(menu);
			choice = Integer.parseInt(choiceString);
			if (choice == 1) {
				showAddproduct();
			} else if (choice == 2) {
				showProduct();
			} else if (choice == 3){
				showPrice();
			} else if (choice == 4){
				showRent();
			} else if (choice == 5){
				showReturn();
			} else if (choice == 6){
				showRepair();
			} else if (choice == 7){
				showDelete();
			}
		}
	}
	
	private void showAddproduct() throws DomainException{
		String title = JOptionPane.showInputDialog("Enter the title:");
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
		}
		
		
	}
	
	private void showProduct(){
		String id = JOptionPane.showInputDialog("Enter the id:");
		
		Product p = null;
		try {
			p=shop.getProduct(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "",JOptionPane.WARNING_MESSAGE);
		}
		
		if(p!=null)
		{
			JOptionPane.showMessageDialog(null, p.getTitle());
		}
	}
	
	private void showPrice(){
		String id = JOptionPane.showInputDialog("Enter the id:");
	
		Product p = null;
		try {
			p=shop.getProduct(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "",JOptionPane.WARNING_MESSAGE);
		}
		
		
		if(p!=null){
			String daysString = JOptionPane.showInputDialog("Enter the number of days:");
			int days = Integer.parseInt(daysString);
			JOptionPane.showMessageDialog(null, p.getPrice(days));
		}
	}
	
	private void showDelete() {
		String id = JOptionPane.showInputDialog("Enter the id:");
		
		try {
			shop.deleteProduct(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "",JOptionPane.WARNING_MESSAGE);
		}
	}

	private void showRepair() {
		String id = JOptionPane.showInputDialog("Enter the id:");
		
		try {
			shop.repairProduct(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "",JOptionPane.WARNING_MESSAGE);
		}
	}

	private void showReturn() {
		String id = JOptionPane.showInputDialog("Enter the id:");
		int reply = JOptionPane.showConfirmDialog(null, "Is the item damaged?", "", JOptionPane.YES_NO_OPTION);
		boolean damaged=false;
		
		if(reply == JOptionPane.YES_OPTION){
			damaged = true;
		}
		
		try {
			shop.returnProduct(Integer.parseInt(id),damaged);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "",JOptionPane.WARNING_MESSAGE);
		}
	}

	private void showRent() {
		String id = JOptionPane.showInputDialog("Enter the id:");
		
		try {
			shop.rentProduct(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ID input is van foute waarde.", "",JOptionPane.WARNING_MESSAGE);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "",JOptionPane.WARNING_MESSAGE);
		}
	}
}
