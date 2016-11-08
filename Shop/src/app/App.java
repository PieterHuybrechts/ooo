package app;
import common.Configuration;
import common.Observer;
import db.DbException;
import domain.EMailService;
import domain.Shop;
import ui.MenuPanel;
import ui.ShopFrame;

public class App {

	public static void main(String[] args) throws DbException{
		new Configuration();
		Shop shop = new Shop();
		
		Observer eMailService = new EMailService(shop);
		shop.addObserver(eMailService);
		
		ShopFrame frame = new ShopFrame();
		frame.setContentPane(new MenuPanel(shop,frame));
		frame.start();
	}
	
}