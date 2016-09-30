package app;

import domain.DomainException;
import domain.Shop;
import ui.Ui;

public class App {

	public static void main(String[] args) throws DomainException{
		new Ui(new Shop());
	}
	
}
