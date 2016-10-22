package db;

public class DatabaseFactory {

	public static Database createDb(String dbName) throws DbException{
		Database db = null;
				
		try {
			Class<?> clazz = Class.forName(dbName);	
			db = (Database)clazz.newInstance();
		} catch (Exception e){
			throw new DbException("Couldn't find this db: "+dbName);
		}
		
		return db;
	}
	
}
