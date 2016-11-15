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
	private JTextField eMailAddressTF;
	private JCheckBox subscribeCB;
	
	private Button okBtn;

	public CustomerAddPanel(final ShopController shopController){
		
		MainWindowChangedFiringSource listener = MainWindowChangedFiringSource.getInstance();
		
		Dimension dimension = new Dimension(600,600);
		this.setSize(dimension);
		setLayout(null);
		
		JLabel titleLbl = new JLabel("Add Customer");
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titleLbl.setBounds(10, 11, 580, 31);
		add(titleLbl);
		
		JLabel firstNameLbl = new JLabel("First Name: ");
		firstNameLbl.setBounds(10, 56, 84, 14);
		add(firstNameLbl);
		
		firstNameTF = new JTextField();
		firstNameTF.setBounds(88, 53, 104, 20);
		add(firstNameTF);
		firstNameTF.setColumns(10);
		
		JLabel lastNameLbl = new JLabel("Last Name:");
		lastNameLbl.setBounds(10, 81, 84, 14);
		add(lastNameLbl);
		
		lastNameTF = new JTextField();
		lastNameTF.setBounds(88, 78, 104, 20);
		add(lastNameTF);
		lastNameTF.setColumns(10);
		
		JLabel eMailAddressLbl = new JLabel("Emailaddress:");
		eMailAddressLbl.setBounds(10, 106, 84, 14);
		add(eMailAddressLbl);
		
		eMailAddressTF = new JTextField();
		eMailAddressTF.setBounds(88, 103, 104, 20);
		add(eMailAddressTF);
		eMailAddressTF.setColumns(10);
		
		JLabel addressLbl = new JLabel("Address:");
		addressLbl.setBounds(10, 130, 84, 14);
		add(addressLbl);
		
		addressTF = new JTextField();
		addressTF.setBounds(88, 127, 104, 20);
		add(addressTF);
		addressTF.setColumns(10);
		
		JLabel zipCodeLbl = new JLabel("ZIP-Code:");
		zipCodeLbl.setBounds(10, 155, 84, 14);
		add(zipCodeLbl);
		
		zipCodeTF = new JTextField();
		zipCodeTF.setBounds(88, 152, 104, 20);
		add(zipCodeTF);
		zipCodeTF.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(10, 180, 84, 14);
		add(lblCity);
		
		cityTF = new JTextField();
		cityTF.setBounds(88, 177, 104, 20);
		add(cityTF);
		cityTF.setColumns(10);
		
		JLabel lblNewletter = new JLabel("NewLetter:");
		lblNewletter.setBounds(10, 205, 58, 14);
		add(lblNewletter);
		
		subscribeCB = new JCheckBox("Subscribe");
		subscribeCB.setBounds(88, 201, 97, 23);
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
				shopController.addCustomer(0, firstNameTF.getText(), lastNameTF.getText(), addressTF.getText(), zipCodeTF.getText(), cityTF.getText(), eMailAddressTF.getText(), subscribeCB.isSelected());
				listener.actionPerformed(e);
			} catch (DomainException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
}
