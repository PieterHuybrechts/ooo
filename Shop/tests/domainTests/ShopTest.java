package domainTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.MagicStrings;
import db.DbException;
import db.concrete.DerbyDb;
import domain.Shop;
import domain.products.Game;
import domain.products.Movie;
import domain.products.Product;
import domain.products.producstates.ProductStateEnum;
import domain.DomainException;

public class ShopTest {
	
	private Shop shop;
	Map<Integer,Product> products;
	
	@Before
	public void setUp() throws Exception{
		this.shop = new Shop(new DerbyDb("resources/shopDb"));
		this.products = new HashMap<Integer, Product>();
		products.put(0, new Game(321698,"testGame",ProductStateEnum.RENTABLE));
		products.put(1, new Movie(12357,"testMovie",ProductStateEnum.DAMAGED));
		products.put(2, new Game(987234,"testGame2",ProductStateEnum.DELETED));
		
		for(int i : products.keySet()){
			shop.addProduct(products.get(i));
		}
	}
	
	@After
	public void cleanUp() throws DomainException{
		for(int i:products.keySet()){
			shop.removeProductFromDb(products.get(i).getId());
		}
		shop=null;
		products=null;
	}
	
	@Test
	public void testAddProductSucceed() throws DbException, DomainException{
		Product p=new Movie(3216549,"title",ProductStateEnum.RENTABLE);	
		products.put(3,p);
		shop.addProduct(p);
	}
	
	@Test
	public void testAddProductWithExistingId() throws DomainException{
		int id = products.get(0).getId();
		
		Product p = new Movie(id,"title",ProductStateEnum.RENTABLE);
		try{
			shop.addProduct(p);
		}catch(DomainException e){
			if(e.getMessage().equals(MagicStrings.PRODUCTEXISTINGINDB.getError()+id)){
				return;
			}
		}
		
	}
	
	@Test
	public void testGetProductSuccess() throws DomainException{
		Product expected = products.get(0);
		Product actual = shop.getProduct(expected.getId());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetProductWithNonExistingId() throws DomainException{
		int id=654951;
		try{
			shop.getProduct(id);
		}catch(DomainException e){
			if(e.getMessage().equals(MagicStrings.PRODUCTNOTFOUNDINDB.getError()+id)){
				return;
			}
		}
	}
	
}
