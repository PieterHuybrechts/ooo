package dbTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import db.DbException;
import db.RelationalDb;
import domain.DomainException;
import domain.Game;
import domain.Product;
import domain.ProductStateEnum;

public class RelationalDbTest {

	RelationalDb db;
	
	@Before
	public void setUp() throws Exception {
		db = new RelationalDb();
	}

	@Test
	public void addProductTest() throws DomainException, DbException {
		Product p = new Game(1351,"addProductTestGame",ProductStateEnum.RENTABLE);
		db.addProduct(p);
	}
	
	@Test
	public void getProductTest() throws DbException{
		db.getProduct(0);
	}
	
	@Test
	public void deleteProductTest(){
		
	}
	
	@Test
	public void addProductTestWithExistingId(){
		
	}
	
	@Test
	public void getProductWithUnexistingId(){
		
	}
	
	@Test
	public void deleteProductWithNonExistingId(){
		
	}
}
