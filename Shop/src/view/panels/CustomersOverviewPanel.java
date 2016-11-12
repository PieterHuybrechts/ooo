package view.panels;

import java.awt.Dimension;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ShopController;
import controller.event.EventEnum;
import controller.event.MainWindowChangedFiringSource;
import view.custom.Button;
import view.tableModels.CustomersTableModel;

import domain.DomainException;
import javax.swing.JScrollPane;
import java.awt.Font;

public class CustomersOverviewPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810257151911564333L;
	private JTextField searchTF;
	private CustomersTableModel customersTblMdl;
	private JTable customersTbl;
	
	public CustomersOverviewPanel(ShopController shopController) throws DomainException{
		super();
		
		MainWindowChangedFiringSource listener = MainWindowChangedFiringSource.getInstance();
		
		Dimension dimension = new Dimension(600,600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel titleLbl = new JLabel("Customers");
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titleLbl.setBounds(10, 11, 580, 31);
		add(titleLbl);
		
		JLabel searcLbl = new JLabel("Search:");
		searcLbl.setBounds(10, 52, 58, 14);
		add(searcLbl);
		
		searchTF = new JTextField();
		searchTF.setBounds(88, 49, 104, 20);
		add(searchTF);
		searchTF.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 580, 477);
		add(scrollPane);
		
		customersTblMdl = new CustomersTableModel(shopController);
		customersTbl = new JTable(customersTblMdl);
		scrollPane.setViewportView(customersTbl);
		
		Button addBtn = new Button("Add");
		addBtn.addActionListener(listener);
		addBtn.setActionCommand(EventEnum.ADDCUSTOMERBUTTONEVENT);
		addBtn.setBounds(501, 566, 89, 23);
		add(addBtn);
		
		customersTblMdl.updateTable();
	}
}
