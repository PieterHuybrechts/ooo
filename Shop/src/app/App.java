package app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import db.Database;
import db.DatabaseFactory;
import db.DbException;
import db.DerbyDb;
import domain.DomainException;
import domain.EMailService;
import domain.Observer;
import domain.Shop;
import ui.MenuPanel;
import ui.ShopFrame;

public class App {

	public static void main(String[] args) throws DomainException, DbException, IOException{
		File configFile = new File("cfg\\config.cfg");
		
		if(!configFile.exists()){
			Properties props = new Properties();
			File dir = new File(configFile.getParent());
			if(!dir.exists()){
				dir.mkdir();
			}
			configFile.createNewFile();
			props.put("dbUrl", "resources/shopDb");
			props.put("dbType", DerbyDb.class.getName());
			
			OutputStream out = new FileOutputStream(configFile);
			String message=
					  "#################################################################################################################\n"
					+ "#\n"
					+ "#    _____    __                             ____                                          __     _\n"
					+ "#   / ___/   / /_   ____     ____           / __ \\   _____  ____     ____   ___    _____  / /_   (_)  ___    _____\n"
					+ "#   \\__ \\   / __ \\ / __ \\   / __ \\         / /_/ /  / ___/ / __ \\   / __ \\ / _ \\  / ___/ / __/  / /  / _ \\  / ___/\n"
					+ "#  ___/ /  / / / // /_/ /  / /_/ /        / ____/  / /    / /_/ /  / /_/ //  __/ / /    / /_   / /  /  __/ (__  ) \n"
					+ "# /____/  /_/ /_/ \\____/  / .___/        /_/      /_/     \\____/  / .___/ \\___/ /_/     \\__/  /_/   \\___/ /____/  \n"
					+ "#                        /_/                                     /_/\n"
					+ "#\n"
					+ "#################################################################################################################\n";
			
			props.store(out, message);
			out.close();
		}
		
		
		
		Database db = DatabaseFactory.createDb(PropertiesEnum.DBTYPE.getProperty(),PropertiesEnum.DBURL.getProperty());
		
		Shop shop = new Shop(db);
		Observer eMailService = new EMailService(shop);
		shop.addObserver(eMailService);
		
		ShopFrame frame = new ShopFrame();
		frame.setContentPane(new MenuPanel(shop,frame));
		frame.start();
	}
	
}
