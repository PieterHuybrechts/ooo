package app;

import db.DbException;
import domain.DomainException;
import domain.Shop;
import ui.Ui;

public class App {

	public static void main(String[] args) throws DomainException, DbException{
		new Ui(new Shop());
	}
	
}
