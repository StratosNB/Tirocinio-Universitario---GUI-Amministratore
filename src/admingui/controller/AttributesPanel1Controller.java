package admingui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import admingui.db.dao.services.EnviromentAttributeService;
import admingui.db.dao.services.ObjectAttributeService;
import admingui.model.Attribute;
import admingui.model.AttributeTableModel;
import admingui.view.AdditionalAttributesTablePanel;
import admingui.view.AttributesPanel1;

public class AttributesPanel1Controller {

	private final static String EMPTY_NAME_MSG = "      Name can't be empty";
	private final static String SELECT_ATTRIBUTE_MSG = "      Select an Attribute first";
	private final static String DELETE_WARNING = "Delete Warning";
	private final static String DELETE_ATTRIBUTE_MSG = "Do you want to delete this attribute?";
	private final static String ATTRIBUTE_CREATED__MSG = "New Attribute created successfully";
	private final static String ATTRIBUTE_UPDATED__MSG = "Attribute updated successfully";
	private final static String ATTRIBUTE_DELETED__MSG = "Attribute deleted successfully";

	private AttributesPanel1 attribPanel;
	private AdditionalAttributesTablePanel objectAttributePanel;
	private AdditionalAttributesTablePanel enviromentAttributePanel;

	private ObjectAttributeService objectAttributeService;
	private EnviromentAttributeService enviromentAttributeService;

	private JTable userAttributesTable;
	private JTable clientAttributesTable;

	private AttributeTableModel userAttributesTableModel;
	private AttributeTableModel clientAttributesTableModel;

	public AttributesPanel1Controller(AttributesPanel1 attribPanel) {
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
		objectAttributePanel = attribPanel.getObjectAdditionalAttributesPanel();
		enviromentAttributePanel = attribPanel.getEnviromentAdditionalAttributesPanel();
	}

	protected void initTables() {
		userAttributesTable = objectAttributePanel.getAttributeTable();
		clientAttributesTable = enviromentAttributePanel.getAttributeTable();
	}

	protected void initTableModels() {
		userAttributesTableModel = (AttributeTableModel) userAttributesTable.getModel();
		clientAttributesTableModel = (AttributeTableModel) clientAttributesTable.getModel();
	}

	protected void initServices() {
		objectAttributeService = new ObjectAttributeService();
		enviromentAttributeService = new EnviromentAttributeService();
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

	protected void openObjectEditDialog(Attribute selectedAttribute, int selectedRow) {
		if (isAttributeSelected(userAttributesTable, selectedRow) == true) {
			selectedAttribute = objectAttributePanel.getSelectedAttribute();
			objectAttributePanel.getEditAttribute().setFieldData(selectedAttribute);
			objectAttributePanel.getEditAttribute().show();
		}
	}

	protected void deleteObjectAttribute(Attribute selectedAttribute, int deleteOption) {
		if (deleteOption(deleteOption) == 0) {
			selectedAttribute = objectAttributePanel.getSelectedAttribute();
			objectAttributeService.deleteAttribute(selectedAttribute.getName());
			userAttributesTableModel.removeRow(userAttributesTable.getSelectedRow());
			showMessage(ATTRIBUTE_DELETED__MSG);
		}
	}

	protected void openEnviromentEditDialog(Attribute selectedAttribute, int selectedRow) {
		if (isAttributeSelected(clientAttributesTable, selectedRow) == true) {
			selectedAttribute = enviromentAttributePanel.getSelectedAttribute();
			enviromentAttributePanel.getEditAttribute().setFieldData(selectedAttribute);
			enviromentAttributePanel.getEditAttribute().show();
		}
	}

	protected void deleteEnviromentAttribute(Attribute selectedAttribute, int deleteOption) {
		if (deleteOption(deleteOption) == 0) {
			selectedAttribute = enviromentAttributePanel.getSelectedAttribute();
			enviromentAttributeService.deleteAttribute(selectedAttribute.getName());
			clientAttributesTableModel.removeRow(clientAttributesTable.getSelectedRow());
			showMessage(ATTRIBUTE_DELETED__MSG);
		}
	}

	protected void createObjectAttribute(Attribute createdAttribute) {
		if (isAddAttributeDialogEmpty(objectAttributePanel) == false) {
			createdAttribute = objectAttributePanel.getAddAttribute().createAttribute();
			objectAttributeService.createAttribute(createdAttribute);
			userAttributesTableModel.addAttributeDataToRow(createdAttribute);
			objectAttributePanel.getAddAttribute().clear();
			showMessage(ATTRIBUTE_CREATED__MSG);
		}
	}

	protected void updateObjectAttribute(Attribute createdAttribute, Attribute selectedAttribute) {
		if (isEditAttributeDialogEmpty(objectAttributePanel) == false) {
			createdAttribute = objectAttributePanel.getEditAttribute().createAttribute();
			selectedAttribute = objectAttributePanel.getSelectedAttribute();
			objectAttributeService.updateAttribute(createdAttribute, selectedAttribute.getName());
			userAttributesTableModel.updateAttributeDataInRow(createdAttribute, selectedAttribute.getName());
			showMessage(ATTRIBUTE_UPDATED__MSG);
			objectAttributePanel.getEditAttribute().dispose();
		}
	}

	protected void createEnviromentAttribute(Attribute createdAttribute) {
		if (isAddAttributeDialogEmpty(enviromentAttributePanel) == false) {
			createdAttribute = enviromentAttributePanel.getAddAttribute().createAttribute();
			enviromentAttributeService.createAttribute(createdAttribute);
			clientAttributesTableModel.addAttributeDataToRow(createdAttribute);
			enviromentAttributePanel.getAddAttribute().clear();
			showMessage(ATTRIBUTE_CREATED__MSG);
		}
	}

	protected void updateEnviromentAttribute(Attribute createdAttribute, Attribute selectedAttribute) {
		if (isEditAttributeDialogEmpty(enviromentAttributePanel) == false) {
			createdAttribute = enviromentAttributePanel.getEditAttribute().createAttribute();
			selectedAttribute = enviromentAttributePanel.getSelectedAttribute();
			clientAttributesTableModel.updateAttributeDataInRow(createdAttribute, selectedAttribute.getName());
			enviromentAttributeService.updateAttribute(createdAttribute, selectedAttribute.getName());
			showMessage(ATTRIBUTE_UPDATED__MSG);
			enviromentAttributePanel.getEditAttribute().dispose();
		}
	}

	private class CrudButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Attribute selectedAttribute = null;
			int selectedRow = 0;
			int deleteOption = 0;

			switch (e.getActionCommand()) {
			case "Object Create Dialog":
				objectAttributePanel.getAddAttribute().show();
				break;

			case "Object Edit Dialog":
				openObjectEditDialog(selectedAttribute, selectedRow);
				break;

			case "Delete Object Attribute":
				deleteObjectAttribute(selectedAttribute, deleteOption);
				break;

			case "Enviroment Create Dialog":
				enviromentAttributePanel.getAddAttribute().show();
				break;

			case "Enviroment Edit Dialog":
				openEnviromentEditDialog(selectedAttribute, selectedRow);
				break;

			case "Delete Enviroment Attribute":
				deleteEnviromentAttribute(selectedAttribute, deleteOption);
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
			case "Create Object Attribute":
				createObjectAttribute(createdAttribute);
				break;

			case "Update Object Attribute":
				updateObjectAttribute(createdAttribute, selectedAttribute);
				break;

			case "Create Enviroment Attribute":
				createEnviromentAttribute(createdAttribute);
				break;

			case "Update Enviroment Attribute":
				updateEnviromentAttribute(createdAttribute, selectedAttribute);
				break;
			}
		}
	}

	private class DialogsCancelButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {
			case "Cancel":
				objectAttributePanel.getAddAttribute().dispose();
				objectAttributePanel.getEditAttribute().dispose();
				enviromentAttributePanel.getAddAttribute().dispose();
				enviromentAttributePanel.getEditAttribute().dispose();
				break;
			}
		}
	}
}