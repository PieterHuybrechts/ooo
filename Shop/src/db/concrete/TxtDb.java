package db.concrete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import common.MagicStrings;
import db.Database;
import db.DbException;
import domain.Customer;
import domain.products.Product;
import domain.products.producstates.ProductStateEnum;

public class TxtDb implements Database{

	private File productsFile;
	private File customersFile;
	private String delimiter;
	
	public TxtDb(String url) throws DbException {
		setFiles(url+"\\txt");
		delimiter = ";";
	}
	
	private void setFiles(String url){
		this.productsFile = new File(url+"\\products.txt");
		this.customersFile = new File(url+"\\customers.txt");
		
		File dir = new File(url);
		if(!dir.exists()){
			dir.mkdir();
		}
		
		createFile(productsFile);
		createFile(customersFile);
	}
	
	private void createFile(File file){
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e){}		
		}
	}
	
	private File getFile(){
		return this.productsFile;
	}
	
	public void addProduct(Product p) throws DbException{
		Product pr=null;
		
		try{
			pr=this.getProduct(p.getId());
		}catch (DbException e) {
			
		}
		
		if(pr!=null){
			throw new DbException(MagicStrings.PRODUCTEXISTINGINDB.getError()+p.getId());
		}
		
		String output = "";
		
		output += p.getId();
		output += this.delimiter;
		output += p.getTitle();
		output += this.delimiter;
		output += p.getClass().getName();
		output += this.delimiter;
		output += p.getCurrentState().toString();
		output += "\n";
		
		try {
			Writer writer = new BufferedWriter(new FileWriter(getFile(),true));
			writer.append(output);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Product getProduct(int id) throws DbException {
		BufferedReader in;
		String line;
		Product product = null;
		
		try{
			in = new BufferedReader(new FileReader(getFile()));
			while((line = in.readLine()) != null){
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(delimiter);
				
				String idStr,nameStr,classNameStr,currentStateStr;
				
				idStr=scanner.next();
				if(id==Integer.parseInt(idStr)){
					nameStr=scanner.next();
					classNameStr = scanner.next();
					currentStateStr = scanner.next();
					Class<?> clazz = Class.forName(classNameStr);
					Constructor<?> ctor = clazz.getConstructor(int.class,String.class,ProductStateEnum.class);
					product = (Product)ctor.newInstance(Integer.parseInt(idStr),nameStr,ProductStateEnum.valueOf(currentStateStr));					
					scanner.close();
					break;
				}
				scanner.close();
				
			}
			in.close();
		}
		catch(Exception e){
			
		}
		
		if(product == null){
			throw new DbException(MagicStrings.PRODUCTNOTFOUNDINDB.getError()+id);
		}
		
		return product;
	}
	
	public void deleteProduct(int id) throws DbException{
		File tempFile = new File("temp.txt");
		BufferedReader in;
		BufferedWriter out;
		String line;
		boolean deleted = false;
		
		try{
			in = new BufferedReader(new FileReader(getFile()));
			out = new BufferedWriter(new FileWriter(tempFile));
			
			while((line = in.readLine()) != null){
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(delimiter);
				
				if(id!=Integer.parseInt(scanner.next())){
					out.write(line.trim()+"\n");
					scanner.close();
				}else{
					deleted = true;
				}
				scanner.close();
			}
			out.close();
			in.close();
			Files.delete(getFile().toPath());
			tempFile.renameTo(getFile());
			Files.delete(tempFile.toPath());
		}catch (Exception e) {
			
		}
		
		if(!deleted){
			throw new DbException(MagicStrings.PRODUCTNOTFOUNDINDB.getError()+id);
		}
	}

	public void updateProduct(Product p) throws DbException {
		int id = p.getId();
		this.deleteProduct(id);
		this.addProduct(p);
	}

	public void addCustomer(Customer c) throws DbException {
		// TODO Auto-generated method stub
		
	}

	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getAllSubscribedCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Product getLastAddedProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(Customer c) throws DbException {
		// TODO Auto-generated method stub
		
	}

	public void deleteCustomer(int id) throws DbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getAllProducts() throws DbException {
		// TODO Auto-generated method stub
		return null;
	}
}
