package app;
import common.Configuration;
import common.Observer;
import controller.ShopController;
import controller.event.MainWindowChangedFiringSource;
import db.DbException;
import domain.EMailService;
import domain.Shop;
import view.MainWindow;

public class App {

	public static void main(String[] args) throws DbException{
		new Configuration();
		Shop shop = new Shop();
		
		Observer eMailService = new EMailService(shop);
		shop.addObserver(eMailService);
		
		ShopController shopController = new ShopController(shop);
		
		MainWindowChangedFiringSource listener = new MainWindowChangedFiringSource(shopController);
		
		MainWindow w = new MainWindow(listener);
		listener.addListener(w);
	}
	
}