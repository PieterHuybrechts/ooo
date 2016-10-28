package app;
import common.Configuration;
import db.DbException;
import domain.EMailService;
import domain.Observer;
import domain.Shop;
import ui.MenuPanel;
import ui.MainWindow;

public class App {

	public static void main(String[] args) throws DbException{
		new Configuration();
		Shop shop = new Shop();
		
		Observer eMailService = new EMailService(shop);
		shop.addObserver(eMailService);
		
		MainWindow frame = new MainWindow();
		frame.setContentPane(new MenuPanel(shop,frame));
		frame.start();
	}
	
}
