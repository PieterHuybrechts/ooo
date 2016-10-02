package domain;

public class Game extends Product {
	
	public int getPrice(int days){
		return days*3;
	}	
			
	public Game (){
		super();
	}
			
	public Game (int id, String title, ProductStateEnum state) throws DomainException{
		super (title,id,state);
	}

}
