package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import db.Database;
import db.DatabaseFactory;
import db.DbException;
import db.SqlDb;
import domain.DomainException;
import domain.EMailService;
import domain.Observer;
import domain.Shop;
import ui.MenuPanel;
import ui.ShopFrame;

public class App {

	public static void main(String[] args) throws DomainException, DbException, IOException{
		
		File configFile = new File("cfg\\config.cfg");
		Properties props = new Properties();
		
		if(!configFile.exists()){
			File dir = new File(configFile.getParent());
			if(!dir.exists()){
				dir.mkdir();
			}
			configFile.createNewFile();
			props.put("db", SqlDb.class.getName());
			
			OutputStream out = new FileOutputStream(configFile);
			props.store(out, "#####################\n#\n# Properties\n#\n#####################");
			out.close();
			
		}else{
			InputStream in = new FileInputStream(configFile);
			props.load(in);
		}
		
		Database db = DatabaseFactory.createDb((String)props.get("db"));
		
		Shop shop = new Shop(db);
		Observer eMailService = new EMailService(shop);
		shop.addObserver(eMailService);
		
		ShopFrame frame = new ShopFrame();
		frame.setContentPane(new MenuPanel(shop,frame));
		frame.start();
	}
	
}
