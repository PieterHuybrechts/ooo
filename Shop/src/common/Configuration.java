package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import db.concrete.DerbyDb;

public class Configuration {
	
	public Configuration(){
		File configFile = new File("cfg\\config.cfg");
		
		if(!configFile.exists()){
			Properties props = new Properties();
			props.put("dbUrl", "resources/shopDb");
			props.put("dbType", DerbyDb.class.getName());
			
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
			
			
			File dir = new File(configFile.getParent());
			if(!dir.exists()){
				dir.mkdir();
			}
			
			try{
				configFile.createNewFile();
				OutputStream out = new FileOutputStream(configFile);
				props.store(out, message);
				out.close();
			}catch (IOException e) {
				
			}
			
		}
	}
	
	
}
