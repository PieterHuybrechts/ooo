package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public enum PropertiesEnum {
	DBTYPE("dbType"),
	DBURL("dbUrl");
	
	String property;
	
	private PropertiesEnum(String propertyName){
		File confFile = new File("cfg\\config.cfg");
		Properties props = new Properties();
		InputStream in;
		try {
			in = new FileInputStream(confFile);
			props.load(in);
			this.property=props.getProperty(propertyName);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The installer should be ran before running the app. (Found in the app package)");
		}
		
		
		
	}
	
	public String getProperty(){
		return this.property;
	}
	
	
}
