package domain;

import java.lang.reflect.Constructor;

import app.MagicStrings;

public class ProductFactory {

	public ProductFactory(){
		
	}
	
	public static Product createProduct(String className, int id, String title, ProductStateEnum state) throws DomainException{
		Product p = null;
		
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> ctor = clazz.getConstructor(int.class,String.class,ProductStateEnum.class);
			p = (Product)ctor.newInstance(id,title,state);
		} catch (Exception e){
			throw new DomainException(MagicStrings.FACTORYERROR.getError());
		}
		
		return p;
	}
	
}
