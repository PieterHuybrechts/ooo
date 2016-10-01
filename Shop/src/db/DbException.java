package db;

public class DbException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 452897794834597564L;

	public DbException(){
		super();
	}
	
	public DbException(String message){
		super(message);
	}
	
	public DbException(Throwable throwable){
		super(throwable);
	}
	
	public DbException(String message,Throwable throwable){
		super(message,throwable);
	}
	
}
