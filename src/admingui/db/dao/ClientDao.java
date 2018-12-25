package admingui.db.dao;

import java.util.List;

import javax.swing.JTable;

import admingui.model.Client;

public interface ClientDao {

	public void createClient(Client client);

	public Client getClientById(int userId);

	public List<Client> getAllClients();

	public List<Client> getAllAvailableClients();

	public List<Client> getClientsAssignedToUser(int UserId);
	
	public void setAvailableClientsInTable(JTable table);
	
	public void setAssignedClientsInTable(JTable table, int userId);

	public void updateClient(Client client, int oldId);

	public void updateClientUserId(int userId, int clientId);

	public void deleteClient(int clientId);
}