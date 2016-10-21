package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.DbException;
import domain.DomainException;
import domain.Product;
import domain.ProductFactory;
import domain.ProductStateEnum;
import domain.ProductTypeEnum;
import domain.Shop;

public class AddProductPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118631129545139359L;

	private Shop shop;
	private ShopFrame frame;
	
	private JLabel titleLabel = new JLabel("title: ");
	private JLabel idLabel = new JLabel("id: ");
	private JLabel typeLabel = new JLabel("type: ");
	private JLabel stateLabel = new JLabel("state ");
	
	private JTextField titleField = new JTextField();
	private JTextField idField = new JTextField();
	private JComboBox<ProductTypeEnum> typeComboBox = new JComboBox<ProductTypeEnum>(ProductTypeEnum.values());
	private JComboBox<ProductStateEnum> stateComboBox = new JComboBox<ProductStateEnum>(ProductStateEnum.values());
	
	private JButton addButton = new JButton("Add");
	
	public AddProductPanel(Shop shop, ShopFrame frame){
		this.shop=shop;
		this.frame = frame;
		
		add(idLabel);
		add(idField);
		
		add(titleLabel);
		add(titleField);
		
		add(typeLabel);
		add(typeComboBox);
		
		add(stateLabel);
		add(stateComboBox);
		
		addButton.addActionListener(new AddButtonListener());
		add(addButton);
	}
	
	private class AddButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String title = titleField.getText();
			int id = Integer.parseInt(idField.getText());
			String className = ProductTypeEnum.valueOf((String)typeComboBox.getSelectedItem()).getClassName();
			ProductStateEnum state = ProductStateEnum.valueOf((String)stateComboBox.getSelectedItem());
			
			
			
			try {
				Product p = ProductFactory.createProduct(className, id, title, state);
				shop.addProduct(p);
			} catch (DomainException e1) {
				JOptionPane.showMessageDialog(null,"Product could not be created");
			} catch (DbException e1) {
				JOptionPane.showMessageDialog(null, "Product could not be added to the database");
			}
			
			frame.setContentPane(new MenuPanel(shop,frame));
			frame.start();
		}
		
		
		
		
	}
}
