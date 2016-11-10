package view.panels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ShopController;
import controller.event.EventEnum;
import controller.event.MainWindowChangedFiringSource;
import domain.DomainException;
import view.custom.Button;
import view.tableModels.ProductsTableModel;

public class ProductsOverviewPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2434848969986287009L;
	private JTextField searchTF;	
	private ProductsTableModel productsTblMdl;
	private JTable productsTbl;
	
	public ProductsOverviewPanel(ShopController shopController,MainWindowChangedFiringSource listener) throws DomainException{
		super();
		Dimension dimension = new Dimension(600,600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel titleLbl = new JLabel("Products");
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
		
		productsTblMdl = new ProductsTableModel(shopController);
		productsTbl = new JTable(productsTblMdl);
		scrollPane.setViewportView(productsTbl);
		
		Button addBtn = new Button("Add");
		addBtn.addActionListener(listener);
		addBtn.setActionCommand(EventEnum.ADDPRODUCTBUTTONEVENT);
		addBtn.setBounds(501, 566, 89, 23);
		add(addBtn);
		
		productsTblMdl.updateTable();
	}

}
