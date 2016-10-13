package domain;

import java.util.List;

import db.DbException;

public class EMailService implements Observer {

	Shop shop;

	public EMailService(Shop shop) {
		this.shop = shop;
	}

	public void update() {
		List<Customer> customers=null;
		try {
			customers = shop.getAllSubscribedCustomers();
		} catch (DbException e) {}

		Product p=null;

		try {
			p = shop.getLastAddedProduct();
		} catch (DbException e) {}
		
		for(Customer c :customers){
			System.out.println("Send mail to "+c.getEmailAddress()+":\n"
							+ "A new product was added to the shop:\n"
							+ "------------------------------------\n"
								+ "\tId: "+p.getId()+"\n"
								+ "\tTitle: "+p.getTitle());
		}
	}

}
