package app;

import db.DbException;
import domain.DomainException;
import domain.EMailService;
import domain.Observer;
import domain.Shop;
import ui.Ui;

public class App {

	public static void main(String[] args) throws DomainException, DbException{
		Shop shop = new Shop();
		Observer eMailService = new EMailService(shop);
		shop.addObserver(eMailService);
		
		new Ui(shop);
	}
	
}
