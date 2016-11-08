package domain.products;

public enum ProductTypeEnum {
	GAME(Game.class.getName()),
	MOVIE(Movie.class.getName());
	
	String className;
	
	private ProductTypeEnum(String className){
		this.className = className;
	}
	
	public String getClassName(){
		return this.className;
	}
}
