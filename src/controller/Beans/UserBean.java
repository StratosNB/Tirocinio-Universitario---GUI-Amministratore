package controller.Beans;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.JOptionPane;

import com.sun.rowset.JdbcRowSetImpl;

import dbContract.DbContract;
import models.User;

public class UserBean {

	private JdbcRowSet rowSet = null;

	public UserBean() {

		try {

			Class.forName("org.postgresql.Driver");
			rowSet = new JdbcRowSetImpl();
			rowSet.setUrl(DbContract.HOST + DbContract.DB_NAME);
			rowSet.setUsername(DbContract.USERNAME);
			rowSet.setPassword(DbContract.PASSWORD);
			rowSet.setCommand("SELECT * FROM users");
			rowSet.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User create(User user) {
		try {

			rowSet.moveToInsertRow();
			rowSet.updateString("userid", user.getUserID());
			rowSet.updateString("name", user.getName());
			rowSet.updateString("surname", user.getSurname());
			rowSet.updateString("password", user.getPassword());
			rowSet.updateString("clientid", user.getClientID());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
				user = null;
			} catch (SQLException e) {

				
			}

			JOptionPane.showMessageDialog(null, "User ID already taken");
			ex.printStackTrace();
		}
		return user;
	}

	public User update(User user) {
		try {

			rowSet.updateString("userid", user.getUserID());
			rowSet.updateString("name", user.getName());
			rowSet.updateString("surname", user.getSurname());
			rowSet.updateString("password", user.getPassword());
			rowSet.updateString("clientid", user.getClientID());
			rowSet.updateRow();
			rowSet.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return user;
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
	
	public JdbcRowSet getRowSet(){
		return this.rowSet;
	}
	
}