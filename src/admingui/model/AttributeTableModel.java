package admingui.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import admingui.db.DbContract;
import admingui.db.dao.services.ClientAttributeService;
import admingui.db.dao.services.EnviromentAttributeService;
import admingui.db.dao.services.ObjectAttributeService;
import admingui.db.dao.services.UserAttributeService;
import admingui.utils.AdminGuiConstants;

@SuppressWarnings("serial")
public class AttributeTableModel extends DefaultTableModel {

	private final String dbTableName;

	private UserAttributeService userAttributeService;
	private ClientAttributeService clientAttributeService;
	private ObjectAttributeService objectsAttributesService;
	private EnviromentAttributeService enviromentAttributesService;

	private List<Attribute> usersAttributes;;
	private List<Attribute> clientsAttributes;
	private List<Attribute> objectAttributes;
	private List<Attribute> enviromentAttributes;

	public AttributeTableModel(String dbTableName) {
		this.dbTableName = dbTableName;
		initServices(this.dbTableName);
		addColumnNames();
		addData();
	}

	protected void initServices(String tableName) {
		if (tableName.equals(DbContract.USERS_ATTRIBUTES_TABLE_NAME)) {
			userAttributeService = new UserAttributeService();
			usersAttributes = userAttributeService.getAllUserAttributes();
			return;
		}
		if (tableName.equals(DbContract.CLIENTS_ATTRIBUTES_TABLE_NAME)) {
			clientAttributeService = new ClientAttributeService();
			clientsAttributes = clientAttributeService.getAllClientAttributes();
			return;
		}
		if (tableName.equals(DbContract.OBJECT_ATTRIBUTES_TABLE_NAME)) {
			objectsAttributesService = new ObjectAttributeService();
			objectAttributes = objectsAttributesService.getAllAttributes();
			return;
		}
		if (tableName.equals(DbContract.ENVIROMENT_ATTRIBUTES_TABLE_NAME)) {
			enviromentAttributesService = new EnviromentAttributeService();
			enviromentAttributes = enviromentAttributesService.getAllAttributes();
			return;
		}
	}

	public void addColumnNames() {
		for (String columnName : AdminGuiConstants.FIXED_ATTRIBUTES_TABLE_COLUMNS) {
			super.addColumn(columnName);
		}
	}

	public void addData() {
		if (usersAttributes != null) {
			for (Attribute attribute : usersAttributes) {
				Object[] attributeData = { attribute.getName().trim(), attribute.getType().trim() };
				super.addRow(attributeData);
			}
		}

		if (clientsAttributes != null) {
			for (Attribute attribute : clientsAttributes) {
				Object[] attributeData = { attribute.getName().trim(), attribute.getType().trim() };
				super.addRow(attributeData);
			}
		}

		if (objectAttributes != null && !objectAttributes.isEmpty()) {
			for (Attribute attribute : objectAttributes) {
				System.out.println(attribute.toString());
				Object[] attributeData = { attribute.getName().trim(), attribute.getType().trim() };
				super.addRow(attributeData);
			}
		}
		if (enviromentAttributes != null) {
			for (Attribute attribute : enviromentAttributes) {
				Object[] attributeData = { attribute.getName().trim(), attribute.getType().trim() };
				super.addRow(attributeData);
			}
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