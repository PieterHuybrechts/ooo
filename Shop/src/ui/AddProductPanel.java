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
import domain.Shop;
import domain.products.ProductTypeEnum;
import domain.products.producstates.ProductStateEnum;

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
	
	private JButton addButton = new JButton("Ok");
	private JButton cancelButton = new JButton("Cancel");
	
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
		
		cancelButton.addActionListener(new CancelButtonListener());
		add(cancelButton);
	}
	
	private class AddButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String title = titleField.getText();
			try{
				int id;
				id = Integer.parseInt(idField.getText());
				
				String className = ProductTypeEnum.valueOf(typeComboBox.getSelectedItem().toString()).getClassName();
				ProductStateEnum state = ProductStateEnum.valueOf(stateComboBox.getSelectedItem().toString());
				shop.addProduct(className, id, title, state);
			}catch(NumberFormatException e2){
				JOptionPane.showMessageDialog(null, "Wrong number format: \""+idField.getText()+"\"","",JOptionPane.ERROR_MESSAGE);
			}catch(DomainException e2){
				JOptionPane.showMessageDialog(null, e2.getMessage(),"",JOptionPane.ERROR_MESSAGE);
			}
			
			frame.setContentPane(new MenuPanel(shop,frame));
			frame.start();
		}
	}
		
	private class CancelButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {				
			frame.setContentPane(new MenuPanel(shop,frame));
			frame.start();
		}
	}
}
