package ui;

import javax.swing.JOptionPane;

import domain.Shop;

public class Ui {
	
	private Shop shop;
	
	public Ui(Shop shop){
		setShop(shop);
		showMenu();
	}
	
	private void setShop(Shop shop){
		this.shop=shop;
	}
	
	private void showMenu(){
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
	
	private void showAddproduct(){
		
	}
	
	private void showProduct(){
		
	}
	
	private void showPrice(){
		
	}
	
}
