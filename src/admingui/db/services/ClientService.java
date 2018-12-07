package admingui.db.services;

import java.util.List;

import admingui.db.daoImpl.ClientDaoImpl;
import admingui.models.Client;

public class ClientService {
	private final ClientDaoImpl clientDaoImpl = new ClientDaoImpl();

	public void createClient(Client client) {
		clientDaoImpl.createClient(client);
	}

	public Client getClientById(int clientId) {
		return clientDaoImpl.getClientById(clientId);
	}

	public List<Client> getAllClients() {
		return clientDaoImpl.getAllClients();
	}

	public List<Client> getAllAvailableClients() {
		return clientDaoImpl.getAllAvailableClients();
	}

	public List<Client> getClientsAssignedToUser(int userId) {
		return clientDaoImpl.getClientsAssignedToUser(userId);
	}

	public void updateClient(Client client, int oldId) {
		clientDaoImpl.updateClient(client, oldId);
	}

	public void updateClientUserId(int userId) {
		clientDaoImpl.updateClientUserId(userId);
	}

	public void deleteClient(int clientId) {
		clientDaoImpl.deleteClient(clientId);
	}
}
