package app;

import domain.Shop;
import ui.Ui;

public class App {

	public static void main(String[] args){
		new Ui(new Shop());
	}
	
}
