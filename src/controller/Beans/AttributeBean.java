package controller.Beans;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
import javax.swing.JOptionPane;
import com.sun.rowset.JdbcRowSetImpl;

import dbContract.DbContract;
import models.Attribute;
import models.User;

public class AttributeBean {

	private JdbcRowSet rowSet = null;

	public AttributeBean(String tableName) {

		try {

			Class.forName("org.postgresql.Driver");
			rowSet = new JdbcRowSetImpl();
			rowSet.setUrl(DbContract.HOST + DbContract.DB_NAME);
			rowSet.setUsername(DbContract.USERNAME);
			rowSet.setPassword(DbContract.PASSWORD);
			rowSet.setCommand("SELECT * FROM " + tableName);
			rowSet.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
   

	public Attribute create(Attribute attribute) {
		try {

			rowSet.moveToInsertRow();

			rowSet.updateString("name", attribute.getAttributeName());
			rowSet.updateString("type", attribute.getAttributeType());

			rowSet.insertRow();
			rowSet.moveToCurrentRow();

		} catch (SQLException ex) {

			JOptionPane.showMessageDialog(null, "Attribute Name already taken");
			try {
				rowSet.rollback();
				attribute = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return attribute;
	}

	public ArrayList<Attribute> create(ArrayList<Attribute> attributes) {
		try {

			for (Attribute a : attributes) {

				rowSet.moveToInsertRow();

				
				rowSet.updateString("userid", a.getUserID());
				rowSet.updateString("clientid", a.getClientID());
				rowSet.updateString("name", a.getAttributeName());
				rowSet.updateString("value", a.getAttributeValue());

				rowSet.insertRow();
				rowSet.moveToCurrentRow();
			}

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
				attributes = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return attributes;
	}

	public Attribute update(Attribute attribute) {
		try {

			rowSet.updateString("name", attribute.getAttributeName());
			rowSet.updateString("value", attribute.getAttributeValue());
			rowSet.updateRow();
			rowSet.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return attribute;
	}

	public void delete() {
		try {
			rowSet.moveToCurrentRow();
			rowSet.deleteRow();
		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}

	}

	public User moveFirst() {
		User user = new User();
		try {
			rowSet.first();
			user.setUserID(rowSet.getString("userid"));
			user.setName(rowSet.getString("name"));
			user.setSurname(rowSet.getString("surname"));
			user.setPassword(rowSet.getString("password"));
			user.setClientID(rowSet.getString("clientid"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public User moveLast() {
		User user = new User();
		try {

			rowSet.last();
			user.setUserID(rowSet.getString("userid"));
			user.setName(rowSet.getString("name"));
			user.setSurname(rowSet.getString("surname"));
			user.setPassword(rowSet.getString("password"));
			user.setClientID(rowSet.getString("clientid"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public User moveNext() {
		User user = new User();
		try {
			if (rowSet.next() == false)
				rowSet.previous();
			user.setUserID(rowSet.getString("userid"));
			user.setName(rowSet.getString("name"));
			user.setSurname(rowSet.getString("surname"));
			user.setPassword(rowSet.getString("password"));
			user.setClientID(rowSet.getString("clientid"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public User movePrevious() {
		User user = new User();
		try {
			if (rowSet.previous() == false)
				rowSet.next();
			user.setUserID(rowSet.getString("userid"));
			user.setName(rowSet.getString("name"));
			user.setSurname(rowSet.getString("surname"));
			user.setPassword(rowSet.getString("password"));
			user.setClientID(rowSet.getString("clientid"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public User getCurrent() {
		User user = new User();
		try {
			rowSet.moveToCurrentRow();
			user.setUserID(rowSet.getString("userid"));
			user.setName(rowSet.getString("name"));
			user.setSurname(rowSet.getString("surname"));
			user.setPassword(rowSet.getString("password"));
			user.setClientID(rowSet.getString("clientid"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public int getRowCount() {

		int count = 0;
		try {
			rowSet.last();
			count = rowSet.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// gets all the attributes present in the table attribute_value
	public ArrayList<Attribute> getAll() {

		ArrayList<Attribute> attributes = new ArrayList<Attribute>();

		try {
			rowSet.beforeFirst();

			while (rowSet.next()) {

				String userId = rowSet.getString("userid");
				String clientId = rowSet.getString("clientid");
				String attrName = rowSet.getString("name");
				String attrValue = rowSet.getString("value");
				String attrType = rowSet.getString("type");

				attributes.add(new Attribute(userId, clientId, attrName, attrValue, attrType));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		for (Attribute a : attributes) {
			System.out.println("attribute 0 namm from method getall: " + attributes.get(0).getAttributeName());
		}
		return attributes;
	}

	public ArrayList<Attribute> getAllFromUsers() {

		ArrayList<Attribute> attributes = new ArrayList<Attribute>();

		if (!(getRowCount() == 0)) {
			try {
				rowSet.beforeFirst();

				while (rowSet.next()) {

					String attrName = rowSet.getString("name");
					String attrType = rowSet.getString("type");

					attributes.add(new Attribute("", "", attrName, "", attrType));
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return attributes;
	}

	public ArrayList<Attribute> getAllFromClients() {

		ArrayList<Attribute> attributes = new ArrayList<Attribute>();

		if (!(getRowCount() == 0)) {
			try {
				rowSet.beforeFirst();

				while (rowSet.next()) {

					String attrName = rowSet.getString("name");
					String attrType = rowSet.getString("type");

					attributes.add(new Attribute("", "", attrName, "", attrType));
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return attributes;
	}

	public JdbcRowSet getRowSet() {
		return this.rowSet;
	}

}
