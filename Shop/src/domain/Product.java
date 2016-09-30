package domain;

public abstract class Product {
private int id;
private String title;

public int getId(){
	return id;
}
public String title(){
	return title;
}

public void setId(int id)throws DomainException{
	if (id < 0){
		throw new DomainException("geef een getal groter dan 0");
	}
	this.id = id;
	
	
}

public void setTitle(String title)throws DomainException{
	if (title.isEmpty()){
		throw new DomainException("Typ een naam");
	}
	this.title = title;
}

public Product(){
	
}

public Product(String title, int id) throws DomainException{
	setTitle(title);
	setId(id);
}

public abstract int getPrice(int days);
}
