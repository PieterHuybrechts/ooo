package db.concrete;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.MagicStrings;
import db.Database;
import db.DbException;
import domain.Customer;
import domain.CustomerFactory;
import domain.DomainException;
import domain.Product;
import domain.ProductFactory;
import domain.ProductStateEnum;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelDb implements Database
{
	private File productsFile;
	private File customersFile;
	private Product lastAddedProduct;	
	
	public ExcelDb(String url){
		setFiles(url+"\\excel");
	}
	
	private void setFiles(String url){
		productsFile = new File(url+"\\products.xls");
		customersFile = new File(url+"\\customers.xls");
		
		File dir = new File(url);
		if(!dir.exists()){
			dir.mkdir();
		}
		
		
	}
	
	public void addProduct(Product p) throws DbException {
		Product pr = null;
		
		try{
			pr = getProduct(p.getId());
		}catch(DbException e){}
		
		if(pr!=null){
			throw new DbException(MagicStrings.PRODUCTEXISTINGINDB.getError()+p.getId());
		}
		
		List<Product> products = getAllProducts();
		products.add(p);
		lastAddedProduct=p;
		
		writeProducts(products);
	}
	
	public Product getProduct(int id) throws DbException {
		for(Product p:getAllProducts()){
			if(p.getId()==id){
				return p;
			}
		}
		
		return null;
	}
	
	@Override
	public List<Product> getAllProducts() throws DbException {
		List<Product> products = new ArrayList<Product>();
		if (!this.productsFile.exists() || this.productsFile.isDirectory()) {
			return products;
		}		
		
		Workbook wb;
		try {
			wb = Workbook.getWorkbook(productsFile);
			Sheet sheet = wb.getSheet(0);
			int row = 0;
			
			while(row<sheet.getRows()){
				int id = Integer.parseInt(sheet.getCell(0,row).getContents());
				String title = sheet.getCell(1,row).getContents();
				String className = sheet.getCell(2,row).getContents();
				ProductStateEnum state = ProductStateEnum.valueOf(sheet.getCell(3,row).getContents());
			
				try {
					products.add(ProductFactory.createProduct(className, id, title, state));
				} catch (DomainException e) {}
				
				row++;
			}
		} catch (BiffException | IOException e) {}
		
		return products;
	}

	public void deleteProduct(int id) throws DbException {
		List<Product> products = getAllProducts();
		
		for(Product p : products){
			if(p.getId() == id){
				products.remove(p);
				break;
			}
		}
		
		writeProducts(products);
	}

	public void updateProduct(Product p) throws DbException {
		int id = p.getId();
		List<Product> products = getAllProducts();
		
		for(Product pr : products){
			if(pr.getId() == id){
				products.remove(pr);
				products.add(p);
				break;
			}
		}
		
		writeProducts(products);
	}
	
	private void writeProducts(List<Product> products){
		WritableWorkbook wb;
		try{
			wb = Workbook.createWorkbook(productsFile);	
			WritableSheet sheet = wb.createSheet("products", 0);
			
			for(int i=0;i<products.size();i++){
				Product temp = products.get(i);
				sheet.addCell(new Label(0,i,""+temp.getId()));
				sheet.addCell(new Label(1,i,temp.getTitle()));
				sheet.addCell(new Label(2,i,temp.getClass().getName()));
				sheet.addCell(new Label(3,i,temp.getCurrentState().toString()));
			}
						
			wb.write();
			wb.close();
		}catch(IOException | WriteException e){}
	}

	public void addCustomer(Customer c) throws DbException {
		List<Customer> customers = getAllCustomers();
		
		for(Customer c2 : customers){
			if(c2.getId() == c.getId()){
				throw new DbException(MagicStrings.CUSTOMEREXISTINGINDB.getError()+c.getId());
			}
		}
		
		customers.add(c);
		writeCustomers(customers);
	}

	public Customer getCustomer(int id) throws DbException {
		List<Customer> customers = getAllCustomers();
		
		for(Customer c:customers){
			if(c.getId()==id){
				return c;
			}
		}
		
		return null;
	}

	public void deleteCustomer(int id) throws DbException {
		List<Customer> customers = getAllCustomers();
		
		for(Customer c:customers){
			if(c.getId()==id){
				customers.remove(c);
			}
		}
		
		writeCustomers(customers);
	}

	public List<Customer> getAllSubscribedCustomers() throws DbException {
		List<Customer> customers = getAllCustomers();
		List<Customer> subscribed = new ArrayList<Customer>();
		
		for(Customer c : customers){
			if(c.isSubscribed()){
				subscribed.add(c);
			}
		}
		
		return subscribed;
	}

	public Product getLastAddedProduct() throws DbException {
		return lastAddedProduct;
	}

	public List<Customer> getAllCustomers() throws DbException {
		List<Customer> customers = new ArrayList<Customer>();
		
		if(!this.customersFile.exists() || this.customersFile.isDirectory()){
			return customers;
		}
		
		Workbook wb;
		try{
			wb = Workbook.getWorkbook(customersFile);
			Sheet sheet = wb.getSheet(0);
			int row = 0;
			
			while(row<sheet.getRows()){
				int id = Integer.parseInt(sheet.getCell(0,row).getContents());
				String firstName = sheet.getCell(1,row).getContents();
				String lastName = sheet.getCell(2,row).getContents();
				String address = sheet.getCell(3,row).getContents();
				String city = sheet.getCell(4,row).getContents();
				String zipCode = sheet.getCell(5,row).getContents();
				String emailAddress = sheet.getCell(6,row).getContents();
				boolean subscribed = sheet.getCell(7,row).getContents().toLowerCase().equals("true");
				
				try{
					customers.add(CustomerFactory.create(id, firstName, lastName, address, zipCode, city, emailAddress, subscribed));
				}catch(DomainException e){
					System.out.println("The customer could not be retrieved from the db because of wrong data");
				}
				
				row++;
				
			}
		}catch(IOException|BiffException e){System.out.println("retrieving customers to the db failed");}
		
		return customers;
	}
	
	private void writeCustomers(List<Customer> customers){
		WritableWorkbook wb;
		try{
			wb = Workbook.createWorkbook(customersFile);
			WritableSheet sheet = wb.createSheet("customers", 0);
			
			for(int i=0;i<customers.size();i++){
				Customer temp = customers.get(i);
				sheet.addCell(new Label(0,i,""+temp.getId()));
				sheet.addCell(new Label(1,i,temp.getFirstName()));
				sheet.addCell(new Label(2,i,temp.getLastName()));
				sheet.addCell(new Label(3,i,temp.getAddress()));
				sheet.addCell(new Label(4,i,temp.getCity()));
				sheet.addCell(new Label(5,i,temp.getZipCode()));
				sheet.addCell(new Label(6,i,temp.getEmailAddress()));
				sheet.addCell(new Label(7,i,""+temp.isSubscribed()));
			}
						
			wb.write();
			wb.close();
		}catch(IOException | WriteException e){}
	}
	
}
