package dbTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.junit.*;

import app.MagicStrings;
import db.DbException;
import db.DerbyDb;
import domain.Customer;
import domain.DomainException;
import domain.Game;
import domain.Movie;
import domain.Product;
import domain.ProductStateEnum;

public class RelationalDbTest {
	
	DerbyDb db;
	Map<Integer,Product> products;
	Map<Integer,Customer> customers;
	
	@Before
	public void setUp() throws DbException, DomainException, SQLException, InterruptedException{
		Properties props = new Properties();
		File confFile = new File("cfg\\config.cfg");
		InputStream in;
		try {
			in = new FileInputStream(confFile);
			props.load(in);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "The app should be ran before running the tests","Warning", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		db = new DerbyDb(props.getProperty("dburl"));
		
		products = new HashMap<Integer,Product>();
		products.put(0,new Game(75311, "testGame" ,ProductStateEnum.RENTED));
		products.put(1,new Movie(41372, "testMovie" ,ProductStateEnum.DAMAGED));
		products.put(2,new Game(78963, "testGame2" ,ProductStateEnum.RENTABLE));
		
		customers= new HashMap<Integer, Customer>();
		customers.put(0, new Customer(741234, "Pieter", "Huybrechts", "Donkelstraat 16", "3350", "Linter", "huybrechtspieter@gmail.com", true));
		customers.put(1, new Customer(123456, "testFirst", "testLast", "teststraat 123", "1234", "Testcity", "test.test@test.com", false));
		customers.put(1, new Customer(756689, "testFirst2", "testLast2", "teststraat 1232", "1234", "Testcity2", "test2.test2@test.com", true));
		
		for(int i : products.keySet()){
			Thread.sleep(1);
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
		Product p = new Game(7413565, "addProductTest", ProductStateEnum.RENTABLE);
		products.put(3, p);
		
		db.addProduct(p);
	}
	
	@Test
	public void testAddProductWithExistingId(){
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
		int id = 321654987;
		try{
			this.db.deleteProduct(id);			
		}catch(DbException e){
			if(!e.getMessage().equals(MagicStrings.PRODUCTNOTDELETABLEFROMDB.getError().replaceAll(""+id, ""))){
				fail();
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
		}else{
			fail();
		}
	}

	
	
	@Test
	public void testAddCustomerSucces() throws DomainException, DbException{
		Customer c = new Customer(1628, "testAddFirst", "testAddLast", "testAddStreet", "7456", "testAddCity", "testAddMail@test.com", false);
		db.addCustomer(c);
		this.customers.put(4, c);
	}
	
	@Test
	public void testAddCustomerWithExistingId(){
		Customer c = customers.get(0);
		try{
			db.addCustomer(c);
		}catch(DbException e){
			if(e.getMessage().replaceAll(""+c.getId(), "").equals(MagicStrings.CUSTOMEREXISTINGINDB.getError()));
		}
	}
	
	@Test
	public void testGetCustomerSucces() throws DbException{
		int i =0;
		Customer c = db.getCustomer(customers.get(i).getId());		
		assertEquals(customers.get(i), c);
	}
	
	@Test
	public void testGetCustomerWithUnExistingId(){
		int id = 15634;
		try{
			db.getCustomer(id);
		}catch(DbException e){
			assertEquals(MagicStrings.CUSTOMERNOTFOUNDINDB.getError(), e.getMessage().replaceAll(""+id, ""));
		}
	}
	
	@Test
	public void testDeleteCustomerSucces() throws DbException{
		int i = 0;
		db.deleteCustomer(customers.get(i).getId());
		customers.remove(i);
	}
	
	@Test
	public void testDeleteCustomerWithUnexistingId(){
		int id = 125678;
		try{
			db.deleteCustomer(id);
		}catch(DbException e){
			assertEquals(MagicStrings.PRODUCTNOTDELETABLEFROMDB.getError(), e.getMessage().replaceAll(""+id, ""));
		}
	}
	
	@Test
	public void testGetAllCustomers() throws DbException{
		
		List<Customer> dbCustomers = db.getAllCustomers();
		List<Customer> newList = new ArrayList<Customer>();
		for(int i : customers.keySet()){
			newList.add(customers.get(i));
		}
		
		for(Customer c : dbCustomers){
			if(!newList.contains(c)){
				fail();
			}
			newList.remove(c);
		}
		if(!newList.isEmpty()){
			fail();
		}
		
		
	}
	
	@Test
	public void testGetAllSubscribedCustomers() throws DbException{
		List<Customer> subscribed = new ArrayList<Customer>();
		for(int i : customers.keySet()){		
			Customer c = customers.get(i);
			if(c.isSubscribed()){
				subscribed.add(c);
			}
		}
		
		List<Customer> dbSubscribed = db.getAllSubscribedCustomers();
		if(dbSubscribed.size()==subscribed.size() && dbSubscribed.containsAll(subscribed)){
			return;
		}else{
			fail();
		}
	}
	
	@Test
	public void testGetLastAddedProduct() throws DbException{
		Product expected=products.get(2);
		Product actual=db.getLastAddedProduct();
		assertEquals(expected,actual);
	}
	
	
	
}
