package view.panels;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ShopController;
import view.tableModels.CustomersTableModel;
import javax.swing.DefaultComboBoxModel;

import domain.DomainException;
import domain.products.ProductTypeEnum;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

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
		
		Dimension dimension = new Dimension(600,600);
		this.setSize(dimension);
		setLayout(null);		
		
		JLabel searcLbl = new JLabel("Search:");
		searcLbl.setBounds(10, 11, 46, 14);
		add(searcLbl);
		
		searchTF = new JTextField();
		searchTF.setBounds(66, 8, 86, 20);
		add(searchTF);
		searchTF.setColumns(10);
		
		
		
		JButton okBtn = new JButton("Ok");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		okBtn.setBounds(501, 566, 89, 23);
		add(okBtn);		
		
		JButton baclBtn = new JButton("Back");
		baclBtn.setBounds(402, 566, 89, 23);
		add(baclBtn);
		
		JButton addBtn = new JButton("Add");
		addBtn.setBounds(10, 566, 89, 23);
		add(addBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 519);
		add(scrollPane);
		
		customersTblMdl = new CustomersTableModel(shopController);
		customersTbl = new JTable(customersTblMdl);
		scrollPane.setViewportView(customersTbl);
		
		customersTblMdl.updateTable();
	}
}
