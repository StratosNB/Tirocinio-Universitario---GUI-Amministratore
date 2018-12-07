package admingui.db.dao;

import java.util.List;

import admingui.models.Client;

public interface ClientDao {

	public void createClient(Client client);

	public Client getClientById(int userId);

	public List<Client> getAllClients();

	public List<Client> getAllAvailableClients();

	public List<Client> getClientsAssignedToUser(int UserId);

	public void updateClient(Client client, int oldId);

	public void updateClientUserId(int userId);

	public void deleteClient(int clientId);
}