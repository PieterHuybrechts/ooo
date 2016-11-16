package db.concrete;

import domain.Customer;
import domain.DomainException;
import domain.products.Product;
import domain.products.ProductFactory;
import domain.products.producstates.ProductStateEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.MagicStrings;
import db.Database;
import db.DbException;

public class DerbyDb implements Database {

	private static String productsTableName = "products";
	private static String customersTableName = "customers";
	private static Connection conn = null;
	private static PreparedStatement stmt = null;

	public DerbyDb(String url) throws DbException {
		String dbUrl = String.format("jdbc:derby:%s;create=true", url + "\\derby");
		conn = this.createConnection(dbUrl);
		createTables();
	}

	private Connection createConnection(String url) throws DbException {
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private void createTables() {
		String sql = "CREATE TABLE " + productsTableName
				+ " (id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), title VARCHAR(20) NOT NULL, class VARCHAR(40) NOT NULL, state VARCHAR(20) NOT NULL, time_stamp TIMESTAMP NOT NULL)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}
		}

		sql = "CREATE TABLE " + customersTableName
				+ " (id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), first_name VARCHAR(20), last_name VARCHAR(20), address VARCHAR(20), zip_code VARCHAR(4), city VARCHAR(20), email_address VARCHAR(50), subscribed BOOLEAN)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}
		}
	}

	public void addProduct(Product p) throws DbException {

		try {
			String sql = "INSERT INTO " + productsTableName
					+ "(title,class,state,time_stamp) VALUES(?,?,?,CURRENT_TIMESTAMP)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getTitle());
			stmt.setString(2, p.getClass().getName());
			stmt.setString(3, p.getCurrentState().toString());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}
			throw new DbException(MagicStrings.PRODUCTEXISTINGINDB.getError() + p.getId());
		}

	}

	public Product getProduct(int id) throws DbException {
		Product p = null;

		try {
			String sql = "SELECT * FROM " + productsTableName + " WHERE id= ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "" + id);
			ResultSet set = stmt.executeQuery();

			set.next();
			int i = Integer.parseInt(set.getString("id"));
			String title = set.getString("title");
			String className = set.getString("class");
			ProductStateEnum state = ProductStateEnum.valueOf(set.getString("state"));
			stmt.close();

			p = ProductFactory.createProduct(className, i, title, state);

		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}

			throw new DbException(e.getMessage());
		} catch (DomainException e) {
			p = null;
		}
		return p;
	}

	@Override
	public List<Product> getAllProducts() throws DbException {
		List<Product> products = new ArrayList<Product>();

		try {
			String sql = "SELECT * FROM " + productsTableName;
			stmt = conn.prepareStatement(sql);

			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				int i = Integer.parseInt(set.getString("id"));
				String title = set.getString("title");
				String className = set.getString("class");
				ProductStateEnum state = ProductStateEnum.valueOf(set.getString("state"));
				products.add(ProductFactory.createProduct(className, i, title, state));
			}
			stmt.close();
		} catch (SQLException | DomainException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}

			throw new DbException(e.getMessage());
		}

		return products;
	}

	public void deleteProduct(int id) throws DbException {
		try {
			String sql = "DELETE FROM " + productsTableName + " WHERE id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id + "");
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (Exception e1) {
			}

			throw new DbException(MagicStrings.PRODUCTNOTDELETABLEFROMDB.getError() + id);
		}
	}

	public void updateProduct(Product p) throws DbException {
		try {
			String sql = "UPDATE " + productsTableName + " SET title=?,state=?,class=? WHERE id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getTitle());
			stmt.setString(2, p.getCurrentState().toString());
			stmt.setString(3, p.getClass().getName());
			stmt.setInt(4, p.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}
			throw new DbException("Could not update this product");
		}

	}

	public void addCustomer(Customer c) throws DbException {
		try {
			String sql = "insert into person(first_name, last_name, address, zip_code, city, email_address ,subscribed) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getFirstName());
			stmt.setString(2, c.getLastName());
			stmt.setString(3, c.getAddress());
			stmt.setString(4, c.getZipCode());
			stmt.setString(5, c.getCity());
			stmt.setString(6, c.getEmailAddress());
			stmt.setBoolean(7, c.isSubscribed());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}
			throw new DbException(MagicStrings.CUSTOMEREXISTINGINDB.getError() + c.getId());
		}
	}

	public Customer getCustomer(int id) throws DbException {
		Customer c = null;
		try {
			String sql = "SELECT * FROM " + customersTableName + " WHERE id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				int i = Integer.parseInt(set.getString("id"));
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String address = set.getString("address");
				String zipCode = set.getString("zip_code");
				String city = set.getString("city");
				String emailAddress = set.getString("email_address");
				boolean subscribed = set.getString("subscribed").toLowerCase().equals("true");

				c = new Customer(i, firstName, lastName, address, zipCode, city, emailAddress, subscribed);
			}

			stmt.close();
		} catch (Exception e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}
			throw new DbException(MagicStrings.CUSTOMERNOTFOUNDINDB.getError() + id);
		}

		return c;
	}

	public void deleteCustomer(int id) throws DbException {
		try {
			String sql = "DELETE FROM " + customersTableName + " WHERE id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (Exception e1) {
			}

			throw new DbException("Couldn't delete the customer");
		}
	}

	public List<Customer> getAllCustomers() throws DbException {
		List<Customer> customers = new ArrayList<Customer>();

		try {
			String sql = "SELECT * FROM " + customersTableName;
			stmt = conn.prepareStatement(sql);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				int i = Integer.parseInt(set.getString("id"));
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String address = set.getString("address");
				String zipCode = set.getString("zip_code");
				String city = set.getString("city");
				String emailAddress = set.getString("email_address");
				boolean subscribed = set.getString("subscribed").toLowerCase().equals("true");

				customers.add(new Customer(i, firstName, lastName, address, zipCode, city, emailAddress, subscribed));
			}
			stmt.close();
		} catch (Exception e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}

			throw new DbException("Couldn't get all customers.");
		}

		return customers;
	}

	public List<Customer> getAllSubscribedCustomers() throws DbException {
		List<Customer> customers = new ArrayList<Customer>();

		try {
			String sql = "SELECT * FROM " + customersTableName + " WHERE subscribed='true'";
			stmt = conn.prepareStatement(sql);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				int i = Integer.parseInt(set.getString("id"));
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String address = set.getString("address");
				String zipCode = set.getString("zip_code");
				String city = set.getString("city");
				String emailAddress = set.getString("email_address");
				boolean subscribed = set.getString("subscribed").toLowerCase().equals("true");

				customers.add(new Customer(i, firstName, lastName, address, zipCode, city, emailAddress, subscribed));
			}
			stmt.close();
		} catch (Exception e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}

			throw new DbException("Couldn't get all subscribed customers.");
		}

		return customers;
	}

	public Product getLastAddedProduct() throws DbException {
		Product p = null;
		try {
			String sql = "SELECT * FROM " + productsTableName + " WHERE time_stamp IN (SELECT max(time_stamp) FROM "
					+ productsTableName + ")";
			stmt = conn.prepareStatement(sql);
			stmt.executeQuery();
			ResultSet set = stmt.executeQuery();
			set.next();
			int id = Integer.parseInt(set.getString("id"));
			String title = set.getString("title");
			String className = set.getString("class");
			ProductStateEnum state = ProductStateEnum.valueOf(set.getString("state"));
			stmt.close();

			p = ProductFactory.createProduct(className, id, title, state);
		} catch (SQLException e) {
			try {
				stmt.close();
			} catch (SQLException e1) {
			}
			throw new DbException(e.getMessage());
		} catch (DomainException e) {
			p = null;
		}

		return p;
	}

}
