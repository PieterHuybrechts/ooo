package db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import domain.Product;

public class TxtDb implements Database {

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
	}
	
	private File getFile(){
		return this.file;
	}
	
	@Override
	public void addProduct(Product p) {
		String output = "";
		
		output += p.getTitle();
		output += this.delimiter;
		output += p.getId();
		output += this.delimiter;
		output += p.getCurrentState().getName();
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(output);
			writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}

	@Override
	public void getProduct(int id) {
		// TODO Auto-generated method stub

	}

}
