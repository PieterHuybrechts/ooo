package db.concrete;

import java.io.File;
import java.io.IOException;
import java.util.List;

import db.Database;
import db.DbException;
import domain.Customer;
import domain.Product;
import jxl.Workbook;
import jxl.write.WritableWorkbook;

public class ExcelDb implements Database
{
	private File productsFile;
	private File customersFile;
	
	
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
		WritableWorkbook workbook = null;
		try{
			workbook = Workbook.createWorkbook(productsFile);
			workbook.createSheet("products", 0);
		}catch (IOException e) {
			System.out.println("Exception thrown");
		}
	}

	public Product getProduct(int id) throws DbException {
		return null;
	}

	public void deleteProduct(int id) throws DbException {
		
	}

	public void updateProduct(Product p) throws DbException {
		
	}

	public void addCustomer(Customer c) throws DbException {
		
	}

	public Customer getCustomer(int id) throws DbException {
		return null;
	}

	public void deleteCustomer(int id) throws DbException {
		
	}

	public List<Customer> getAllSubscribedCustomers() throws DbException {
		return null;
	}

	public Product getLastAddedProduct() throws DbException {
		return null;
	}

	public List<Customer> getAllCustomers() throws DbException {
		return null;
	}

}
