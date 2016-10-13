package domain;



import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import db.DbException;

public class EMailService implements Observer {

	private Shop shop;
	private Properties properties;

	public EMailService(Shop shop) {
		this.shop = shop;
		loadProperties();
	}
	
	private void loadProperties() {
		properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.password", "1ntegration");
		properties.put("mail.smtp.user", "integration.continuous");
	}
	
	private void sendFromGMail(String subject, String body, String... to) throws DomainException {
		try {
			Session session = Session.getDefaultInstance(properties);
			
			MimeMessage message = new MimeMessage(session);
			for (int i = 0; i < to.length; i++) {
				InternetAddress toAddress = new InternetAddress(to[i]);
				message.addRecipient(Message.RecipientType.TO, toAddress);
			}
			message.setSubject(subject);
			message.setText(body);
			
			String from = properties.getProperty("mail.smtp.user");
			String password = properties.getProperty("mail.smtp.password");
			Transport transport = session.getTransport("smtp");
			transport.connect(from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			throw new DomainException(e.getMessage(), e);
		}
	}

	public void update(){
		List<Customer> customers=null;
		try {
			customers = shop.getAllSubscribedCustomers();
		} catch (DbException e) {}

		String[] addresses=new String[customers.size()];
		
		for(int i = 0 ; i<customers.size() ; i++){
			addresses[i]=customers.get(i).getEmailAddress();
		}
		
		Product p=null;
		
		try {
			p = shop.getLastAddedProduct();
		} catch (DbException e) {}
		
		/*for(Customer c :customers){
			System.out.println("Send mail to "+c.getEmailAddress()+":\n"
							+ "A new product was added to the shop:\n"
							+ "------------------------------------\n"
								+ "\tId: "+p.getId()+"\n"
								+ "\tTitle: "+p.getTitle());
		}*/
		
		String subject="New product added to the shop.";
		String body = "A new product was added to the shop:\n"
					+ "------------------------------------\n"
						+ "\tId: "+p.getId()+"\n"
						+ "\tTitle: "+p.getTitle();
		
		
		try {
			sendFromGMail(subject, body, addresses);
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}

}
