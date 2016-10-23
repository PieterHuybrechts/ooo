package dbTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import app.MagicStrings;
import app.PropertiesEnum;
import db.Database;
import db.DatabaseFactory;
import db.DbException;
import db.TxtDb;
import domain.DomainException;
import domain.Game;
import domain.Movie;
import domain.Product;
import domain.ProductStateEnum;

public class TxtDbTest {
	
	Database db;
	Map<Integer,Product> products;
	
	@Before
	public void setUp() throws DbException, DomainException{
		db = DatabaseFactory.createDb(TxtDb.class.getName(),PropertiesEnum.DBURL.getProperty());
		products = new HashMap<Integer,Product>();
		
		products.put(0,new Game(75311, "testGame" ,ProductStateEnum.RENTED));
		products.put(1,new Movie(41372, "testMovie" ,ProductStateEnum.DAMAGED));
		products.put(2,new Game(78963, "testGame2" ,ProductStateEnum.RENTABLE));
		
		for(int i : products.keySet()){
			db.addProduct(products.get(i));
		}
	}
	
	@After
	public void cleanUp() throws IOException, DbException{
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
	
	@Test
	public void testGetProductFail() throws DbException{
		int id = 3246584;
		try{
			db.getProduct(id);
		}catch(DbException e){
			if(e.getMessage().replace(""+id, "").equals(MagicStrings.PRODUCTNOTFOUNDINDB.getError())){
				return;
			}
		}
	}
	
	@Test
	public void testDeleteProductSucces() throws DbException{
		int key = 0;
		int id = products.get(key).getId();
		products.remove(key);
		db.deleteProduct(id);
		
		try{
			db.getProduct(id);
		}catch (Exception e) {
			if(e.getMessage().replaceAll(""+id, "").equals(MagicStrings.PRODUCTNOTFOUNDINDB.getError())){
				return;
			}
		}
	}
	
	@Test
	public void testAddProductSucces() throws DomainException, DbException{
		Product p = new Game(2, "addProductTest", ProductStateEnum.RENTABLE);
		products.put(3, p);
		
		db.addProduct(p);
	}
	
	@Test
	public void testAddProductWithExistingId() throws DbException{
		Product p = products.get(0);
		
		try{
			db.addProduct(p);
		}catch (DbException e) {
			if(e.getMessage().equals(MagicStrings.PRODUCTEXISTINGINDB.getError()+p.getId())){
				return;
			}
		}
	}

	@Test
	public void testUpdateProductSucces() throws DomainException, DbException{
		Product p = products.get(0);
		p.returnToShop(false);
		db.updateProduct(p);
		
		Product product = db.getProduct(p.getId());
		
		if(p.getId()==product.getId() && p.getTitle().equals(product.getTitle()) && p.getCurrentState()==product.getCurrentState()){
			return;
		}
	}
}
