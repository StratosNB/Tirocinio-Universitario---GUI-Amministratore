package admingui.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import admingui.db.dao.services.ClientAttributeService;
import admingui.db.dao.services.ClientService;
import admingui.db.dao.services.UserAttributeService;
import admingui.db.dao.services.UserService;
import admingui.utils.AdminGuiStrings;

@SuppressWarnings("serial")
public class SearchResultsTableModel extends DefaultTableModel {

	private final String entity;

	private final String[] columnNames = AdminGuiStrings.FIXED_ATTRIBUTES_TABLE_COLUMNS;

	private UserService userService;
	private ClientService clientService;

	private UserAttributeService userAttributeService;
	private ClientAttributeService clientAttributeService;

	private List<?> entities;
	private List<Attribute> entitiesAttributes;

	public SearchResultsTableModel(String entity) {
		this.entity = entity;
		initServices(this.entity);
		addColumnNames();
		addData();
	}

	protected void initServices(String tableName) {
		if (tableName.equals("users")) {
			userService = new UserService();
			userAttributeService = new UserAttributeService();
			entities = userService.getAllUsers();
			return;
		}
		if (tableName.equals("clients")) {
			clientAttributeService = new ClientAttributeService();
			entitiesAttributes = clientAttributeService.getAllClientAttributes();
			entities = clientService.getAllClients();
			return;
		}
	}

	public void addColumnNames() {
		for (String columnName : columnNames) {
			super.addColumn(columnName);
		}
	}

	public void addData() {
		for (Attribute attribute : entitiesAttributes) {
			Object[] attributeData = { attribute.getName().trim(), attribute.getType().trim() };
			super.addRow(attributeData);
		}
	}

	public void addAttributeDataToRow(Attribute attribute) {
		Object[] attributeData = { attribute.getName().trim(), attribute.getType().trim() };
		super.addRow(attributeData);
	}

	public void updateAttributeDataInRow(Attribute attribute, String oldName) {
		Object[] attributeData = { attribute.getName(), attribute.getType() };
		for (int i = 0; i < getRowCount(); i++) {
			if (oldName.equals(getValueAt(i, 0))) {
				for (int j = 0; j < attributeData.length; j++) {
					super.setValueAt(attributeData[j], i, j);
				}
			}
		}
	}
}
