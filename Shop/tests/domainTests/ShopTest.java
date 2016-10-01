package domainTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Shop;
import domain.DomainException;
import domain.Game;
import domain.Movie;
import domain.Product;

public class ShopTest {
	
	private Shop shop;
	List<Product> products;
	
	@Before
	public void setUp() throws Exception{
		this.shop = new Shop();
		this.products = new ArrayList<Product>();
	}
	
	@Test
	public void testAddProductSucceed() throws DomainException{
		Product p = new Movie(0,"title");
		shop.addProduct(p);
	}
	
	@Test (expected=DomainException.class)
	public void testAddProductWithExistingId() throws DomainException{
		Product p = new Movie(0,"title");
		shop.addProduct(p);
		
		Product p2 = new Game(0,"title");
		shop.addProduct(p2);
	}
	
	@Test
	public void testGetProductSucceed() throws DomainException{
		Product expected = new Movie(0,"title");
		shop.addProduct(expected);
		
		Product actual = shop.getProduct(0);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetProductWithNonExistingId(){
		assertEquals(null, shop.getProduct(987));
	}
	
}
