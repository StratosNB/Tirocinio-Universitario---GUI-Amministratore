package admingui.db.dao.services;

import java.util.List;

import javax.swing.JTable;

import admingui.db.dao.impl.ClientDaoImpl;
import admingui.model.Client;

public class ClientService {
	private final ClientDaoImpl dao = new ClientDaoImpl();

	public void createClient(Client client) {
		dao.createClient(client);
	}

	public Client getClientById(int clientId) {
		return dao.getClientById(clientId);
	}

	public List<Client> getAllClients() {
		return dao.getAllClients();
	}

	public List<Client> getAllAvailableClients() {
		return dao.getAllAvailableClients();
	}

	public List<Client> getClientsAssignedToUser(int userId) {
		return dao.getClientsAssignedToUser(userId);
	}

	public void setAvailableClientsInTable(JTable table) {
		dao.setAvailableClientsInTable(table);
	}

	public void setAssignedClientsInTable(JTable table, int userId) {
		dao.setAssignedClientsInTable(table, userId);
	}

	public void updateClient(Client client, int oldId) {
		dao.updateClient(client, oldId);
	}

	public void updateClientUserId(int userId, int clientId) {
		dao.updateClientUserId(userId, clientId);
	}

	public void deleteClient(int clientId) {
		dao.deleteClient(clientId);
	}
}
