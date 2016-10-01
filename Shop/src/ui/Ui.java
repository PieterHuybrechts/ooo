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
		String menu = "1. Add product\n2. Show product\n3. Show rental price\n\n0. Quit";
		int choice = -1;
		while (choice != 0) {
			String choiceString = JOptionPane.showInputDialog(menu);
			choice = Integer.parseInt(choiceString);
			if (choice == 1) {
				showAddproduct();
			} else if (choice == 2) {
				showProduct();;
			} else if (choice == 3){
				showPrice();
			}
		}
	}
	
	private void showAddproduct() throws DomainException{
		String title = JOptionPane.showInputDialog("Enter the title:");
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id:"));
		String type = JOptionPane.showInputDialog("Enter the type (M for movie/G for game):");
		Product p=null;
		
		if(type.toLowerCase().equals("m")){
			p = new Movie(id,title,ProductStateEnum.RENTABLE.getState());
		}else if(type.toLowerCase().equals("g")){
			p= new Game(id,title,ProductStateEnum.RENTABLE.getState());
		}
		
		this.shop.addProduct(p);
	}
	
	private void showProduct(){
		String id = JOptionPane.showInputDialog("Enter the id:");
		
		Product p = null;
		p=shop.getProduct(Integer.parseInt(id));
		
		if(p!=null)
		{
			JOptionPane.showMessageDialog(null, p.getTitle());
		}
	}
	
	private void showPrice(){
		String id = JOptionPane.showInputDialog("Enter the id:");
	
		Product p = null;
		p=shop.getProduct(Integer.parseInt(id));
		
		if(p!=null){
			String daysString = JOptionPane.showInputDialog("Enter the number of days:");
			int days = Integer.parseInt(daysString);
			JOptionPane.showMessageDialog(null, p.getPrice(days));
		}
	}
	
}
