package view.tableModels;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;

import domain.Customer;

public class CustomersTableModel extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2270538492578310630L;
	
	private static final String[] columnsNames = {"First Name", "Surname",  "E-mail", "Newsletter"};
	private final List<Customer> data;
	
	public CustomersTableModel(){
		data = new LinkedList<Customer>();
	}
}
