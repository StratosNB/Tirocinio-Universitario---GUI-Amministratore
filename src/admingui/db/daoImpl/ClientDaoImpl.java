package admingui.db.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import admingui.db.ConnectToDatabase;
import admingui.db.dao.ClientDao;
import admingui.models.Client;

public class ClientDaoImpl implements ClientDao {

	private final Connection conn = ConnectToDatabase.createConnection();
	private final String SQL_CREATE_CLIENT = "INSERT INTO clients (id, name) VALUES (?, ?)";
	private final String SQL_GET_CLIENT_BY_ID = "SELECT * FROM clients WHERE Id=?";
	private final String SQL_GET_ALL_CLIENTS = "SELECT * FROM clients ORDER BY id";
	private final String SQL_GET_ALL_AVAILBLE_CLIENTS = "SELECT c.id,c.name FROM clients as c WHERE c.user_id IS NULL ORDER BY id";
	private final String SQL_GET_ALL_ASSIGNED_CLIENTS = "SELECT * FROM clients WHERE user_id=? ORDER BY id";
	private final String SQL_UPDATE_CLIENT = "UPDATE clients SET id=?, name=? WHERE Id=?";
	private final String SQL_UPDATE_CLIENT_USER_ID = "UPDATE clients SET user_id=? WHERE id=?";
	private final String SQL_DELETE_CLIENT = "DELETE FROM clients WHERE Id=?";

	@Override
	public void createClient(Client client) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, client.getId());
			pstmt.setString(2, client.getName());
			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					client.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Client getClientById(int clientId) {
		Client client = new Client();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_CLIENT_BY_ID)) {
			pstmt.setInt(1, clientId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					client.setId(rs.getInt(1));
					client.setName(rs.getString(2));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return client;
	}

	@Override
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_CLIENTS);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setName(rs.getString(2).trim());
				client.setUserID(rs.getInt(3));
				clients.add(client);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return clients;
	}

	@Override
	public List<Client> getAllAvailableClients() {
		List<Client> clients = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_AVAILBLE_CLIENTS);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setName(rs.getString(2).trim());
				clients.add(client);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return clients;
	}

	@Override
	public List<Client> getClientsAssignedToUser(int userId) {
		List<Client> clients = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_ASSIGNED_CLIENTS)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setName(rs.getString(2).trim());
				clients.add(client);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return clients;
	}

	@Override
	public void updateClient(Client client, int oldId) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_CLIENT)) {
			pstmt.setInt(1, client.getId());
			pstmt.setString(2, client.getName());
			pstmt.setInt(3, oldId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateClientUserId(int userId) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_CLIENT_USER_ID)) {
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteClient(int clientId) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_CLIENT)) {
			pstmt.setInt(1, clientId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}