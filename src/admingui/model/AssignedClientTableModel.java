package admingui.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import admingui.db.dao.services.ClientService;
import admingui.utils.AdminGuiConstants;

@SuppressWarnings("serial")
public class AssignedClientTableModel extends DefaultTableModel {
	private ClientService clientService;
	private List<Client> assignedClients;

	public AssignedClientTableModel() {
		clientService = new ClientService();
		assignedClients = clientService.getAllAvailableClients();
		addColumnNames();
	}

	public void addColumnNames() {
		for (String columnName : AdminGuiConstants.CLIENT_TABLE_COLUMNS) {
			super.addColumn(columnName);
		}
	}

	public void addData(int userId) {
		assignedClients = clientService.getClientsAssignedToUser(userId);
		for (Client client : assignedClients) {
			Object[] attributeData = { client.getId(), client.getName().trim() };
			super.addRow(attributeData);
		}
	}
	
	public void removeAllRows() {
		int rowCount = this.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			this.removeRow(i);
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		switch (column) {
		case 0:
			return false;
		case 1:
			return false;
		default:
			return false;
		}
	}
}