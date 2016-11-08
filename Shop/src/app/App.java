package app;
import common.Configuration;
import common.Observer;
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
		
		MainWindow w = new MainWindow();
		MainWindowChangedFiringSource.getInstance().addListener(w);
		
		/*ShopFrame frame = new ShopFrame();
		frame.setContentPane(new MenuPanel(shop,frame));
		frame.start();*/
	}
	
}