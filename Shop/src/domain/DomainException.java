package domain;

public class DomainException extends Exception{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7506237481098417807L;

	public DomainException(){
		super();
	}
	
	public DomainException(String message){
		super(message);
	}
	
	public DomainException(Throwable arg0){
		super(arg0);
	}
	
	public DomainException(String arg0,Throwable arg1){
		super(arg0, arg1);
	}
	
}
