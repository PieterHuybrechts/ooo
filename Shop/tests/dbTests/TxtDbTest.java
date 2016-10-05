package dbTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import db.DbException;
import db.TxtDb;
import domain.DomainException;
import domain.Game;
import domain.Movie;
import domain.Product;
import domain.ProductStateEnum;

public class TxtDbTest {
	
	TxtDb db;
	Map<Integer,Product> products;
	
	@Before
	public void setUp() throws DbException, DomainException{
		db = new TxtDb();
		products = new HashMap<Integer,Product>();
		
		products.put(0,new Game(75311, "testGame" ,ProductStateEnum.RENTED));
		products.put(1,new Movie(41372, "testMovie" ,ProductStateEnum.DAMAGED));
		products.put(2,new Game(78963, "testGame2" ,ProductStateEnum.RENTABLE));
		
		for(int i : products.keySet()){
			db.addProduct(products.get(i));
		}
	}
	
	@After
	public void cleanUp() throws IOException{
		for(int i : products.keySet()){
			db.deleteProduct(products.get(i).getId());
		}
		db = null;
		products = null;
	}
	
	@Test
	public void testGetProductSucces() throws DbException{
		Product actual = db.getProduct(products.get(0).getId());
		assertEquals(products.get(0),actual);
	}
	
	@Test (expected = DbException.class)
	public void testGetProductFail() throws DbException{
		db.getProduct(3246584);
	}
	
	@Test (expected = DbException.class)
	public void testDeleteProductSucces() throws DbException{
		db.deleteProduct(products.get(0).getId());
		db.getProduct(products.get(0).getId());
	}
	
	@Test
	public void testAddProductSucces() throws DomainException, DbException{
		db.addProduct(new Game(2, "addProductTest", ProductStateEnum.RENTABLE));
		db.deleteProduct(2);
	}
	
	@Test (expected=DbException.class)
	public void testAddProductWithExistingId() throws DbException{
		db.addProduct(products.get(0));
	}
}
