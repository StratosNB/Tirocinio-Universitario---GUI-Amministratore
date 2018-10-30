package controller.Beans;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;

import dbContract.DbContract;
import models.Client;
import models.User;

public class ClientBean {

	private JdbcRowSet rowSet = null;

	public ClientBean() {

		try {

			Class.forName("org.postgresql.Driver");
			rowSet = new JdbcRowSetImpl();
			rowSet.setUrl(DbContract.HOST + DbContract.DB_NAME);
			rowSet.setUsername(DbContract.USERNAME);
			rowSet.setPassword(DbContract.PASSWORD);
			rowSet.setCommand("SELECT * FROM clients");
			rowSet.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Client create(Client client) {
		try {

			rowSet.moveToInsertRow();
			rowSet.updateString("clientid", client.getClientID());
			rowSet.updateString("clientname", client.getClientName());

			rowSet.insertRow();
			rowSet.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
				client = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return client;
	}

	public Client update(Client client) {
		try {

			rowSet.updateString("clientid", client.getClientID());
			rowSet.updateString("clientname", client.getClientName());

			rowSet.updateRow();
			rowSet.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				rowSet.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return client;
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

	public Client moveFirst() {
		Client client = new Client();
		try {
			rowSet.first();
			client.setClientID(rowSet.getString("clientid"));
			client.setClientName(rowSet.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client moveLast() {
		Client client = new Client();
		try {

			rowSet.last();
			client.setClientID(rowSet.getString("clientid"));
			client.setClientName(rowSet.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client moveNext() {
		Client client = new Client();
		try {
			if (rowSet.next() == false)
				rowSet.previous();
			client.setClientID(rowSet.getString("clientid"));
			client.setClientName(rowSet.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client movePrevious() {
		Client client = new Client();
		try {
			if (rowSet.previous() == false)
				rowSet.next();
			client.setClientID(rowSet.getString("clientid"));
			client.setClientName(rowSet.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client getCurrent() {
		Client client = new Client();
		try {
			rowSet.moveToCurrentRow();
			client.setClientID(rowSet.getString("clientid"));
			client.setClientName(rowSet.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public JdbcRowSet getRowSet() {
		return this.rowSet;
	}
}