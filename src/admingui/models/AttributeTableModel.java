package admingui.models;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import admingui.db.contract.DbContract;
import admingui.db.services.ClientAttributeService;
import admingui.db.services.UserAttributeService;
import admingui.utils.AdminGuiStrings;

@SuppressWarnings("serial")
public class AttributeTableModel extends DefaultTableModel {

	private final String dbTableName;

	private UserAttributeService userAttributeService;
	private ClientAttributeService clientAttributeService;

	private List<Attribute> allAttributes;

	public AttributeTableModel(String dbTableName) {
		this.dbTableName = dbTableName;
		initServices(this.dbTableName);
		addColumnNames();
		addData();
	}

	protected void initServices(String tableName) {
		if (tableName.equals(DbContract.USERS_ATTRIBUTES_TABLE_NAME)) {
			userAttributeService = new UserAttributeService();
			allAttributes = userAttributeService.getAllUserAttributes();
			return;
		}
		if (tableName.equals(DbContract.CLIENTS_ATTRIBUTES_TABLE_NAME)) {
			clientAttributeService = new ClientAttributeService();
			allAttributes = clientAttributeService.getAllClientAttributes();
			return;
		}
	}

	public void addColumnNames() {
		for (String columnName :  AdminGuiStrings.fixedAttributesTableColumns) {
			super.addColumn(columnName);
		}
	}

	public void addData() {
		for (Attribute attribute : allAttributes) {
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

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		default:
			return String.class;
		}
	}
}