package admingui.controllers.attributes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import admingui.db.services.ClientAttributeService;
import admingui.db.services.UserAttributeService;
import admingui.models.Attribute;
import admingui.models.AttributeTableModel;
import admingui.views.attribute.AdditionalAttributesTablePanel;
import admingui.views.attribute.AttributesPanel;

public class AttributesPanelController {

	private final static String EMPTY_NAME_MSG = "      Name can't be empty";
	private final static String SELECT_ATTRIBUTE_MSG = "      Select an Attribute first";
	private final static String DELETE_WARNING = "Delete Warning";
	private final static String DELETE_ATTRIBUTE_MSG = "Do you want to delete this attribute?";
	private final static String ATTRIBUTE_CREATED__MSG = "New Attribute created successfully";
	private final static String ATTRIBUTE_UPDATED__MSG = "Attribute updated successfully";
	private final static String ATTRIBUTE_DELETED__MSG = "Attribute deleted successfully";

	private AttributesPanel attribPanel;
	private AdditionalAttributesTablePanel userAttributePanel;
	private AdditionalAttributesTablePanel clientAttributePanel;

	private UserAttributeService userAttributeService;
	private ClientAttributeService clientAttributeService;

	private JTable userAttributesTable;
	private JTable clientAttributesTable;

	private AttributeTableModel userAttributesTableModel;
	private AttributeTableModel clientAttributesTableModel;

	public AttributesPanelController(AttributesPanel attribPanel) {
		this.attribPanel = attribPanel;
		initialize();
	}

	protected void initialize() {
		initAttributePanels();
		initTables();
		initTableModels();
		initServices();
		addListeners();
	}

	protected void initAttributePanels() {
		userAttributePanel = attribPanel.getUserAdditionalAttributesPanel();
		clientAttributePanel = attribPanel.getClientAdditionalAttributesPanel();
	}

	protected void initTables() {
		userAttributesTable = userAttributePanel.getAttributeTable();
		clientAttributesTable = clientAttributePanel.getAttributeTable();
	}

	protected void initTableModels() {
		userAttributesTableModel = (AttributeTableModel) userAttributesTable.getModel();
		clientAttributesTableModel = (AttributeTableModel) clientAttributesTable.getModel();
	}

	protected void initServices() {
		userAttributeService = new UserAttributeService();
		clientAttributeService = new ClientAttributeService();
	}

	protected void addListeners() {
		attribPanel.addDialogsSubmmitButtonsListener(new DialogsSubmmitButtonsListener());
		attribPanel.addCrudButtonsListener(new CrudButtonsListener());
		attribPanel.addCancelButtonsListeners(new DialogsCancelButtonsListener());
	}

	private boolean isAttributeSelected(JTable table, int selectedRow) {
		selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(attribPanel.getMainPanel(), SELECT_ATTRIBUTE_MSG);
			return false;
		}
		return true;
	}

	private int deleteOption(int deleteOption) {
		return deleteOption = JOptionPane.showConfirmDialog(attribPanel.getMainPanel(), DELETE_ATTRIBUTE_MSG,
				DELETE_WARNING, JOptionPane.YES_NO_OPTION);
	}

	private void showMessage(String msg) {
		JOptionPane.showMessageDialog(attribPanel.getMainPanel(), msg);
	}

	private boolean isAddAttributeDialogEmpty(AdditionalAttributesTablePanel panel) {
		if (panel.getAddAttribute().isEmptyFieldData()) {
			showMessage(EMPTY_NAME_MSG);
			return true;
		}
		return false;
	}

	private boolean isEditAttributeDialogEmpty(AdditionalAttributesTablePanel panel) {
		if (panel.getEditAttribute().isEmptyFieldData()) {
			showMessage(EMPTY_NAME_MSG);
			return true;
		}
		return false;
	}

	protected void openUserEditDialog(Attribute selectedAttribute, int selectedRow) {
		if (isAttributeSelected(userAttributesTable, selectedRow) == true) {
			selectedAttribute = userAttributePanel.getSelectedAttribute();
			userAttributePanel.getEditAttribute().setFieldData(selectedAttribute);
			userAttributePanel.getEditAttribute().show();
		}
	}

	protected void deleteUserAttribute(Attribute selectedAttribute, int deleteOption) {
		if (deleteOption(deleteOption) == 0) {
			selectedAttribute = userAttributePanel.getSelectedAttribute();
			userAttributeService.deleteUserAttribute(selectedAttribute.getName());
			userAttributesTableModel.removeRow(userAttributesTable.getSelectedRow());
			showMessage(ATTRIBUTE_DELETED__MSG);
		}
	}

	protected void openClientEditDialog(Attribute selectedAttribute, int selectedRow) {
		if (isAttributeSelected(clientAttributesTable, selectedRow) == true) {
			selectedAttribute = clientAttributePanel.getSelectedAttribute();
			clientAttributePanel.getEditAttribute().setFieldData(selectedAttribute);
			clientAttributePanel.getEditAttribute().show();
		}
	}

	protected void deleteClientAttribute(Attribute selectedAttribute, int deleteOption) {
		if (deleteOption(deleteOption) == 0) {
			selectedAttribute = clientAttributePanel.getSelectedAttribute();
			clientAttributeService.deleteClientAttribute(selectedAttribute.getName());
			clientAttributesTableModel.removeRow(clientAttributesTable.getSelectedRow());
			showMessage(ATTRIBUTE_DELETED__MSG);
		}
	}

	protected void createUserAttribute(Attribute createdAttribute) {
		if (isAddAttributeDialogEmpty(userAttributePanel) == false) {
			createdAttribute = userAttributePanel.getAddAttribute().createAttribute();
			userAttributeService.createUserAttribute(createdAttribute);
			userAttributesTableModel.addAttributeDataToRow(createdAttribute);
			userAttributePanel.getAddAttribute().clear();
			showMessage(ATTRIBUTE_CREATED__MSG);
		}
	}

	protected void updateUserAttribute(Attribute createdAttribute, Attribute selectedAttribute) {
		if (isEditAttributeDialogEmpty(userAttributePanel) == false) {
			createdAttribute = userAttributePanel.getEditAttribute().createAttribute();
			selectedAttribute = userAttributePanel.getSelectedAttribute();
			userAttributeService.updateUserAttribute(createdAttribute, selectedAttribute.getName());
			userAttributesTableModel.updateAttributeDataInRow(createdAttribute, selectedAttribute.getName());
			showMessage(ATTRIBUTE_UPDATED__MSG);
			userAttributePanel.getEditAttribute().dispose();
		}
	}

	protected void createClientAttribute(Attribute createdAttribute) {
		if (isAddAttributeDialogEmpty(clientAttributePanel) == false) {
			createdAttribute = clientAttributePanel.getAddAttribute().createAttribute();
			clientAttributeService.createClientAttribute(createdAttribute);
			clientAttributesTableModel.addAttributeDataToRow(createdAttribute);
			clientAttributePanel.getAddAttribute().clear();
			showMessage(ATTRIBUTE_CREATED__MSG);
		}
	}

	protected void updateClientAttribute(Attribute createdAttribute, Attribute selectedAttribute) {
		if (isEditAttributeDialogEmpty(clientAttributePanel) == false) {
			createdAttribute = clientAttributePanel.getEditAttribute().createAttribute();
			selectedAttribute = clientAttributePanel.getSelectedAttribute();
			clientAttributesTableModel.updateAttributeDataInRow(createdAttribute, selectedAttribute.getName());
			clientAttributeService.updateClientAttribute(createdAttribute, selectedAttribute.getName());
			showMessage(ATTRIBUTE_UPDATED__MSG);
			clientAttributePanel.getEditAttribute().dispose();
		}
	}

	private class CrudButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Attribute selectedAttribute = null;
			int selectedRow = 0;
			int deleteOption = 0;

			switch (e.getActionCommand()) {
			case "User Create Dialog":
				userAttributePanel.getAddAttribute().show();
				break;

			case "User Edit Dialog":
				openUserEditDialog(selectedAttribute, selectedRow);
				break;

			case "Delete User Attribute":
				deleteUserAttribute(selectedAttribute, deleteOption);
				break;

			case "Client Create Dialog":
				clientAttributePanel.getAddAttribute().show();
				break;

			case "Client Edit Dialog":
				openClientEditDialog(selectedAttribute, selectedRow);
				break;

			case "Delete Client Attribute":
				deleteClientAttribute(selectedAttribute, deleteOption);
				break;
			}
		}
	}

	private class DialogsSubmmitButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Attribute createdAttribute = null;
			Attribute selectedAttribute = null;

			switch (e.getActionCommand()) {
			case "Create User Attribute":
				createUserAttribute(createdAttribute);
				break;

			case "Update User Attribute":
				updateUserAttribute(createdAttribute, selectedAttribute);
				break;

			case "Create Client Attribute":
				createClientAttribute(createdAttribute);
				break;

			case "Update Client Attribute":
				updateClientAttribute(createdAttribute, selectedAttribute);
				break;
			}
		}
	}

	private class DialogsCancelButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {
			case "Cancel":
				userAttributePanel.getAddAttribute().dispose();
				userAttributePanel.getEditAttribute().dispose();
				clientAttributePanel.getAddAttribute().dispose();
				clientAttributePanel.getEditAttribute().dispose();
				break;
			}
		}
	}
}