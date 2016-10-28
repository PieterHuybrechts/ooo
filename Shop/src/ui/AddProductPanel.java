package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.DomainException;
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

		public void actionPerformed(ActionEvent e) {
			String title = titleField.getText();
			int id = Integer.parseInt(idField.getText());
			String className = ProductTypeEnum.valueOf(typeComboBox.getSelectedItem().toString()).getClassName();
			ProductStateEnum state = ProductStateEnum.valueOf(stateComboBox.getSelectedItem().toString());
			
			try {
				shop.addProduct(className, id, title, state);
			} catch (DomainException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"",JOptionPane.ERROR_MESSAGE);
			}
			
			frame.setContentPane(new MenuPanel(shop,frame));
			frame.start();
		}
		
		
		
		
	}
}
