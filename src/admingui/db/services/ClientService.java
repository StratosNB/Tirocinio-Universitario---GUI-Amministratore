package admingui.db.services;

import java.util.List;

import admingui.db.daoImpl.ClientDaoImpl;
import admingui.models.Client;

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

	public void updateClient(Client client, int oldId) {
		dao.updateClient(client, oldId);
	}

	public void updateClientUserId(int userId) {
		dao.updateClientUserId(userId);
	}

	public void deleteClient(int clientId) {
		dao.deleteClient(clientId);
	}
}
