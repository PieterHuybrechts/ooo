package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.Scanner;

import domain.Product;
import domain.ProductStateEnum;

public class TxtDb implements Database{

	private File file;
	private String delimiter;
	
	public TxtDb() throws DbException {
		setFile(new File("db.txt"));
		delimiter = ";";
	}
	
	private void setFile(File file) throws DbException{
		this.file = file;
		
		if(!this.getFile().exists()){
			try {
				this.getFile().createNewFile();
			} catch (IOException e) {
				throw new DbException(e);
			}
		}
		
		System.out.println(file.getAbsolutePath());
	}
	
	private File getFile(){
		return this.file;
	}
	
	public void addProduct(Product p) throws DbException{
		Product pr=null;
		
		try{
			pr=this.getProduct(p.getId());
		}catch (DbException e) {
			
		}
		
		if(pr!=null){
			throw new DbException("There is already an item with id:\""+p.getId()+"\"in the database");
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
				
				}
				scanner.close();
				break;
			}
			in.close();
		}
		catch(Exception e){
			
		}
		
		if(product == null){
			throw new DbException("Product could not be found in the database.");
		}
		
		return product;
	}
	
	public void deleteProduct(int id){
		File tempFile = new File("temp.txt");
		BufferedReader in;
		BufferedWriter out;
		String line;
		
		try{
			in = new BufferedReader(new FileReader(getFile()));
			out = new BufferedWriter(new FileWriter(tempFile));
			
			while((line = in.readLine()) != null){
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(delimiter);
				
				if(id!=Integer.parseInt(scanner.next())){
					out.write(line.trim()+"\n");
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
	}

	/*public void drop() throws IOException {
		Files.delete(file.toPath());
	}*/

}
