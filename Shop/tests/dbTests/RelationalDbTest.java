package dbTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import app.MagicStrings;
import db.DbException;
import db.SqlDb;
import domain.Customer;
import domain.DomainException;
import domain.Game;
import domain.Movie;
import domain.Product;
import domain.ProductStateEnum;

public class RelationalDbTest {
	
	SqlDb db;
	Map<Integer,Product> products;
	Map<Integer,Customer> customers;
	
	@Before
	public void setUp() throws DbException, DomainException, SQLException{
		db = new SqlDb();
		
		products = new HashMap<Integer,Product>();
		products.put(0,new Game(75311, "testGame" ,ProductStateEnum.RENTED));
		products.put(1,new Movie(41372, "testMovie" ,ProductStateEnum.DAMAGED));
		products.put(2,new Game(78963, "testGame2" ,ProductStateEnum.RENTABLE));
		
		customers= new HashMap<Integer, Customer>();
		customers.put(0, new Customer(741234, "Pieter", "Huybrechts", "Donkelstraat 16", "3350", "Linter", "huybrechtspieter@gmail.com", true));
		customers.put(1, new Customer(123456, "testFirst", "testLast", "teststraat 123", "1234", "Testcity", "test.test@test.com", false));
		customers.put(1, new Customer(756689, "testFirst2", "testLast2", "teststraat 1232", "1234", "Testcity2", "test2.test2@test.com", true));
		
		for(int i : products.keySet()){
			db.addProduct(products.get(i));
		}
		
		for(int i : customers.keySet()){
			db.addCustomer(customers.get(i));
		}
	}
	
	@After
	public void cleanUp() throws IOException, DbException{
		for(int i : products.keySet()){
			db.deleteProduct(products.get(i).getId());
		}
		
		for(int i : customers.keySet()){
			db.deleteCustomer(customers.get(i).getId());
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
	public void testGetProductFail(){
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
	public void testAddProductSucces() throws DomainException, DbException{
		Product p = new Game(2, "addProductTest", ProductStateEnum.RENTABLE);
		products.put(3, p);
		
		db.addProduct(p);
	}
	
	@Test
	public void testAddProductWithExistingId(){
		Product p = products.get(0);
		
		try{
			db.addProduct(p);
		}catch (DbException e) {
			if(e.getMessage().equals(MagicStrings.EXISTINGIDINDB.getError()+p.getId())){
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
	public void testDeleteProductWithUnExistingId(){
		
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

	
	
	@Test
	public void testAddCustomerSucces(){
		//TODO
	}
	
	@Test
	public void testAddCustomerWithExistingId(){
		//TODO
	}
	
	@Test
	public void testGetCustomerSucces(){
		//TODO
	}
	
	@Test
	public void testGetCustomerWithUnExistingId(){
		//TODO
	}
	
	@Test
	public void testDeleteCustomerSucces(){
		//TODO
	}
	
	@Test
	public void testDeleteCustomerWithUnexistingId(){
		//TODO
	}
	
	@Test
	public void testGetAllCustomers(){
		//TODO
	}
	
	@Test
	public void testGetAllSubscribedCustomers(){
		//TODO
	}
	
	@Test
	public void testGetLastAddedProduct(){
		//TODO
	}
	
	
	
}
