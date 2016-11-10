package view.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ShopController;
import domain.Customer;
import domain.DomainException;

public class CustomersTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2270538492578310630L;
	
	private static final String[] columnsNames = {"id","First Name","Last name", "E-mail","Address","ZIPCode","City","Newsletter"};
	private final List<Customer> customers;
	private ShopController shopController;
	
	public CustomersTableModel(ShopController shopController) throws DomainException{
		customers = new ArrayList<Customer>();
		this.shopController = shopController;
	}
	
	public void updateTable() throws DomainException{
		customers.clear();
		this.customers.addAll(shopController.getAllCustomers());
		fireTableRowsInserted(this.customers.size()-1, this.customers.size()-1);		
	}
	
	public void setCustomers(List<Customer> customers){
		this.customers.clear();
		this.customers.addAll(customers);
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public int getColumnCount() {
		return columnsNames.length;
	}
	
	@Override
	public String getColumnName(int column){
		return columnsNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer c = customers.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return c.getId();
		case 1:
			return c.getFirstName();
		case 2:
			return c.getLastName();			
		case 3:
			return c.getEmailAddress();
		case 4:
			return c.getAddress();
		case 5:
			return c.getZipCode();
		case 6:
			return c.getCity();
		case 7:
			return c.isSubscribed();
		default:
			return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int column){
		switch(column){
		case 0:
			return int.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return Boolean.class;
		default:
			return null;
		}
	}
}
