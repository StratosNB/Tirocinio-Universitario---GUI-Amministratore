package admingui.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {

	public static Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DbContract.HOST + DbContract.DB_NAME, DbContract.USERNAME,
					DbContract.PASSWORD);
		} catch (SQLException ex) {
			System.out.println("Could't connect to the db");
			ex.printStackTrace();
		}
		return conn;
	}
}
