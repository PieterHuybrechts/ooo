package db;

import java.lang.reflect.Constructor;

public class DatabaseFactory {

	public static Database createDb(String dbName,String url) throws DbException{
		Database db = null;
				
		try {
			Class<?> clazz = Class.forName(dbName);
			Constructor<?> ctor = clazz.getConstructor(String.class);
			db = (Database)ctor.newInstance(url);
		} catch (Exception e){
			throw new DbException("Couldn't find this db: "+dbName);
		}
		
		return db;
	}
	
}
