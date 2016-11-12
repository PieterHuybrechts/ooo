package view.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ShopController;
import controller.event.EventEnum;
import controller.event.MainWindowChangedFiringSource;
import domain.DomainException;
import domain.products.ProductTypeEnum;
import domain.products.producstates.ProductStateEnum;
import view.custom.Button;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ProductAddPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField idTF;
	private JTextField titleTF;
	private JComboBox<ProductTypeEnum> typeCB;
	private JComboBox<ProductStateEnum> stateCB;
	
	public ProductAddPanel(ShopController shopController){
		
		MainWindowChangedFiringSource listener = MainWindowChangedFiringSource.getInstance();
		
		Dimension dimension = new Dimension(600,600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel pageTitleLbl = new JLabel("Add Product");
		pageTitleLbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pageTitleLbl.setBounds(10, 11, 580, 31);
		add(pageTitleLbl);
		
		JLabel idLbl = new JLabel("Id:");
		idLbl.setBounds(10, 52, 46, 14);
		add(idLbl);
		
		idTF = new JTextField();
		idTF.setBounds(88, 49, 104, 20);
		add(idTF);

		JLabel titleLbl = new JLabel("Title:");
		titleLbl.setBounds(10, 76, 84, 14);
		add(titleLbl);
		
		titleTF = new JTextField();
		titleTF.setBounds(88, 73, 104, 20);
		add(titleTF);
		titleTF.setColumns(10);
		
		JLabel typeLbl = new JLabel("Type:");
		typeLbl.setBounds(10, 101, 46, 14);
		add(typeLbl);
		
		typeCB = new JComboBox<ProductTypeEnum>();
		typeCB.setModel(new DefaultComboBoxModel<ProductTypeEnum>(ProductTypeEnum.values()));
		typeCB.setBounds(88, 98, 104, 20);
		add(typeCB);
		
		JLabel stateLbl = new JLabel("State");
		stateLbl.setBounds(10, 126, 46, 14);
		add(stateLbl);
		
		stateCB = new JComboBox<ProductStateEnum>();
		stateCB.setModel(new DefaultComboBoxModel<ProductStateEnum>(ProductStateEnum.values()));
		stateCB.setBounds(88, 123, 104, 20);
		add(stateCB);
		
		Button cancelBtn = new Button("Cancel");
		cancelBtn.addActionListener(listener);
		cancelBtn.setActionCommand(EventEnum.CANCELADDPRODUCTBUTTONEVENT);
		cancelBtn.setBounds(402, 566, 89, 23);
		add(cancelBtn);
		
		Button okBtn = new Button("OK");
		okBtn.setActionCommand(EventEnum.OKADDPRODUCTBUTTONEVENT);
		OkBtnListener okBtnListener= new OkBtnListener(shopController,listener);
		okBtn.addActionListener(okBtnListener);
		okBtn.setBounds(501, 566, 89, 23);
		add(okBtn);
	}
	
	private class OkBtnListener implements ActionListener{

		private MainWindowChangedFiringSource listener;
		private ShopController shopController;
		
		public OkBtnListener(ShopController shopController,MainWindowChangedFiringSource listener) {
			this.shopController = shopController;
			this.listener = listener;
		}
		
		public void actionPerformed(ActionEvent e) {
			try {
				ProductTypeEnum type = (ProductTypeEnum)typeCB.getSelectedItem();
				String className = type.getClassName();
				ProductStateEnum state = (ProductStateEnum)stateCB.getSelectedItem();
				shopController.addProduct(className, idTF.getText() , titleTF.getText(), state);
				listener.actionPerformed(e);
			} catch (DomainException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
}
