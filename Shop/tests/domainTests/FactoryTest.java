package domainTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.MagicStrings;
import domain.Customer;
import domain.DomainException;
import domain.products.Game;
import domain.products.Product;
import domain.products.ProductFactory;
import domain.products.producstates.ProductStateEnum;

public class FactoryTest {
	
	Product p;
	
	@Before
	public void setup() throws DomainException{
		p = new Game(10, "test", ProductStateEnum.RENTABLE);
	}
	
	@After
	public void breakDown(){
		p=null;
	}
	
	
	@Test
	public void testCreateProductSucces() throws DomainException{
		Product actual = ProductFactory.createProduct(p.getClass().getName(), p.getId(), p.getTitle(), p.getCurrentState());
		
		assertEquals(p,actual);
	}
	
	@Test
	public void testCreateProductWithWrongClass(){
		try{
			ProductFactory.createProduct(Customer.class.getName(), 123 ,"qwe", ProductStateEnum.RENTABLE );
		}catch(DomainException e){
			assertEquals(MagicStrings.FACTORYERROR.getError(), e.getMessage());
		}
		
	}
	
}
