package admingui.models;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import admingui.db.services.ClientService;
import admingui.utils.AdminGuiStrings;

@SuppressWarnings("serial")
public class AvailableClientTableModel extends DefaultTableModel {

	private ClientService clientService;
	private List<Client> avaibleClients;

	public AvailableClientTableModel() {
		clientService = new ClientService();
		addColumnNames();
		addData();
	}

	public void addColumnNames() {
		for (String columnName : AdminGuiStrings.clientTableColumns) {
			super.addColumn(columnName);
		}
	}

	public void addData() {
		avaibleClients = clientService.getAllAvailableClients();
		for (Client client : avaibleClients) {
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