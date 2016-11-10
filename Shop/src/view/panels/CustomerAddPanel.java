package view.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ShopController;
import controller.event.EventEnum;
import controller.event.MainWindowChangedFiringSource;
import domain.DomainException;
import view.MainWindow;
import view.custom.Button;

import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAddPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8611186219872971380L;
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField addressTF;
	private JTextField zipCodeTF;
	private JTextField cityTF;
	private JTextField idTF;
	private JTextField eMailAddressTF;
	private JCheckBox subscribeCB;
	
	private Button okBtn;
	
	private ShopController controller;
	private MainWindowChangedFiringSource buttonListener;

	public CustomerAddPanel(final ShopController shopController, MainWindowChangedFiringSource listener){
		this.controller = shopController;
		this.buttonListener = listener;
		
		Dimension dimension = new Dimension(600,600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel titleLbl = new JLabel("Add Customer");
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titleLbl.setBounds(10, 11, 580, 31);
		add(titleLbl);
		
		JLabel idLbl = new JLabel("Id:");
		idLbl.setBounds(10, 52, 46, 14);
		add(idLbl);
		
		idTF = new JTextField();
		idTF.setBounds(88, 49, 104, 20);
		add(idTF);
		idTF.setColumns(10);
		
		JLabel firstNameLbl = new JLabel("First Name: ");
		firstNameLbl.setBounds(10, 76, 84, 14);
		add(firstNameLbl);
		
		firstNameTF = new JTextField();
		firstNameTF.setBounds(88, 73, 104, 20);
		add(firstNameTF);
		firstNameTF.setColumns(10);
		
		JLabel lastNameLbl = new JLabel("Last Name:");
		lastNameLbl.setBounds(10, 101, 84, 14);
		add(lastNameLbl);
		
		lastNameTF = new JTextField();
		lastNameTF.setBounds(88, 98, 104, 20);
		add(lastNameTF);
		lastNameTF.setColumns(10);
		
		JLabel eMailAddressLbl = new JLabel("Emailaddress:");
		eMailAddressLbl.setBounds(10, 126, 84, 14);
		add(eMailAddressLbl);
		
		eMailAddressTF = new JTextField();
		eMailAddressTF.setBounds(88, 123, 104, 20);
		add(eMailAddressTF);
		eMailAddressTF.setColumns(10);
		
		JLabel addressLbl = new JLabel("Address:");
		addressLbl.setBounds(10, 150, 84, 14);
		add(addressLbl);
		
		addressTF = new JTextField();
		addressTF.setBounds(88, 147, 104, 20);
		add(addressTF);
		addressTF.setColumns(10);
		
		JLabel zipCodeLbl = new JLabel("ZIP-Code:");
		zipCodeLbl.setBounds(10, 175, 84, 14);
		add(zipCodeLbl);
		
		zipCodeTF = new JTextField();
		zipCodeTF.setBounds(88, 172, 104, 20);
		add(zipCodeTF);
		zipCodeTF.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(10, 200, 84, 14);
		add(lblCity);
		
		cityTF = new JTextField();
		cityTF.setBounds(88, 197, 104, 20);
		add(cityTF);
		cityTF.setColumns(10);
		
		JLabel lblNewletter = new JLabel("NewLetter:");
		lblNewletter.setBounds(10, 225, 58, 14);
		add(lblNewletter);
		
		subscribeCB = new JCheckBox("Subscribe");
		subscribeCB.setBounds(88, 221, 97, 23);
		add(subscribeCB);
		
		okBtn = new Button("OK");
		okBtn.setActionCommand(EventEnum.OKADDCUSTOMERBUTTONEVENT);
		OkBtnListener okBtnListener= new OkBtnListener(shopController,listener);
		okBtn.addActionListener(okBtnListener);
		
		
		okBtn.setBounds(501, 566, 89, 23);
		add(okBtn);
		
		Button cancelBtn = new Button("Cancel");
		cancelBtn.addActionListener(listener);
		cancelBtn.setActionCommand(EventEnum.CANCELADDCUSTOMERBUTTONEVENT);
		cancelBtn.setBounds(402, 566, 89, 23);
		add(cancelBtn);
		
		
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
				shopController.addCustomer(idTF.getText(), firstNameTF.getText(), lastNameTF.getText(), addressTF.getText(), zipCodeTF.getText(), cityTF.getText(), eMailAddressTF.getText(), subscribeCB.isSelected());
				listener.actionPerformed(e);
			} catch (DomainException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
}
