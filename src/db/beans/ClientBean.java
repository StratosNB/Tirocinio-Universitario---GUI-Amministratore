package db.beans;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;

import db.contract.DbContract;
import models.Client;

public class ClientBean {

	private JdbcRowSet jrc = null;

	public ClientBean() {

		try {

			Class.forName("org.postgresql.Driver");
			jrc = new JdbcRowSetImpl();
			jrc.setUrl(DbContract.HOST + DbContract.DB_NAME);
			jrc.setUsername(DbContract.USERNAME);
			jrc.setPassword(DbContract.PASSWORD);
			jrc.setCommand("SELECT * FROM clients");
			jrc.execute();

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

			jrc.moveToInsertRow();
			jrc.updateString("clientid", client.getClientID());
			jrc.updateString("clientname", client.getClientName());

			jrc.insertRow();
			jrc.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				jrc.rollback();
				client = null;
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return client;
	}

	public Client update(Client client) {
		try {

			jrc.updateString("clientid", client.getClientID());
			jrc.updateString("clientname", client.getClientName());

			jrc.updateRow();
			jrc.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				jrc.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}
		return client;
	}

	public void delete() {
		try {
			jrc.moveToCurrentRow();
			jrc.deleteRow();
		} catch (SQLException ex) {
			try {
				jrc.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}

	}

	public Client moveFirst() {
		Client client = new Client();
		try {
			jrc.first();
			client.setClientID(jrc.getString("clientid"));
			client.setClientName(jrc.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client moveLast() {
		Client client = new Client();
		try {

			jrc.last();
			client.setClientID(jrc.getString("clientid"));
			client.setClientName(jrc.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client moveNext() {
		Client client = new Client();
		try {
			if (jrc.next() == false)
				jrc.previous();
			client.setClientID(jrc.getString("clientid"));
			client.setClientName(jrc.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client movePrevious() {
		Client client = new Client();
		try {
			if (jrc.previous() == false)
				jrc.next();
			client.setClientID(jrc.getString("clientid"));
			client.setClientName(jrc.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public Client getCurrent() {
		Client client = new Client();
		try {
			jrc.moveToCurrentRow();
			client.setClientID(jrc.getString("clientid"));
			client.setClientName(jrc.getString("clientname"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public ArrayList<String> getAvaibleClients() {

		ArrayList<String> clients = new ArrayList<String>();

		try {
			jrc.beforeFirst();
			while (jrc.next()) {

				if (jrc.getString("userid") == null) {
					
					clients.add(jrc.getString("clientid"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clients;

	}

	public JdbcRowSet getRowSet() {
		return this.jrc;
	}


}