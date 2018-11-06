package db.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JoinRowSetImpl;

import db.beans.ClientBean;
import db.beans.UserBean;

public class UserClientsRecords extends ClientBean {

	private static JoinRowSet jrs = null;

	public UserClientsRecords() {

		try {
			jrs = new JoinRowSetImpl();

			// ResultSet rs1 = stmt.executeQuery("SELECT * FROM users");
			CachedRowSet users = new CachedRowSetImpl();
			users.populate(new UserBean().getRowSet());
			users.setMatchColumn(1);
			jrs.addRowSet(users);

			// ResultSet rs2 = stmt.executeQuery("SELECT * FROM clients");
			CachedRowSet clients = new CachedRowSetImpl();
			clients.populate(new ClientBean().getRowSet());
			clients.setMatchColumn(3); // EMP_ID is the first column
			jrs.addRowSet(clients);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<String> getUserClientsIDs(String userID) {
		ArrayList<String> results = new ArrayList<>();
		try {
			jrs.beforeFirst();

			while (jrs.next()) {

				if (jrs.getString("userid").equals(userID)) {
					results.add(jrs.getString("clientid"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;

	}

	public static ArrayList<String> getAvaibleUserClientsIDs() {

		ArrayList<String> clients = new ArrayList<String>();

		clients.addAll(new ClientBean().getAvaibleClients());

		System.out.println(clients.size() + " " + clients.get(0));

		return clients;

	}

	public void updateUserID(String userID) {

		try {

			jrs.updateString("userid", userID);

			jrs.updateRow();
			jrs.moveToCurrentRow();

		} catch (SQLException ex) {
			try {
				jrs.rollback();
			} catch (SQLException e) {

			}
			ex.printStackTrace();
		}

	}

}
