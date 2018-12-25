package admingui.view;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

import admingui.db.DbContract;
import admingui.utils.AdminGuiStrings;
import net.miginfocom.swing.MigLayout;

public class AttributesPanel1 {

	private JPanel mainPanel;

	private JTable objectFixedAttributesTable;
	private JTable enviromentFixedAttributesTable;

	private AdditionalAttributesTablePanel objectAdditionalAttributesPanel;
	private AdditionalAttributesTablePanel enviromentAdditionalAttributesPanel;

	public AttributesPanel1() {
		initComponents();
	}

	protected void initComponents() {
		initTables();
		initMainPanel();
		setActionCommands();
	}

	protected void initTables() {
		objectFixedAttributesTable = new JTable(AdminGuiStrings.OBJECTS_FIXED_ATTRIBUTES_TABLE_DATA,
				AdminGuiStrings.FIXED_ATTRIBUTES_TABLE_COLUMNS);
		enviromentFixedAttributesTable = new JTable();
		objectFixedAttributesTable.setFillsViewportHeight(true);
		enviromentFixedAttributesTable.setFillsViewportHeight(true);

		objectAdditionalAttributesPanel = new AdditionalAttributesTablePanel(DbContract.OBJECT_ATTRIBUTES_TABLE_NAME);
		enviromentAdditionalAttributesPanel = new AdditionalAttributesTablePanel(
				DbContract.ENVIROMENT_ATTRIBUTES_TABLE_NAME);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		mainPanel.add(createUsersAttributesPanel());
		mainPanel.add(createClientsAttributesPanel());
		mainPanel.setVisible(true);
	}

	protected JPanel createUsersAttributesPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]", "0[]0[]0"));
		panel.setBorder(BorderFactory.createTitledBorder("Objects Attributes"));
		panel.add(createTitlePanel("Fixed"), "cell 0 0");
		panel.add(createTablePanel(objectFixedAttributesTable), "cell 0 1");
		panel.add(createTitlePanel("Additional"), "cell 1 0");
		panel.add(objectAdditionalAttributesPanel.getMainPanel(), "cell 1 1");
		return panel;
	}

	protected JPanel createClientsAttributesPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]", "0[]0[]0"));
		panel.setBorder(BorderFactory.createTitledBorder("Enviroment Attributes"));
		panel.add(createTitlePanel("Fixed"), "cell 0 0");
		panel.add(createTablePanel(enviromentFixedAttributesTable), "cell 0 1");
		panel.add(createTitlePanel("Additional"), "cell 1 0");
		panel.add(enviromentAdditionalAttributesPanel.getMainPanel(), "cell 1 1");
		return panel;
	}

	protected JPanel createTitlePanel(String title) {
		JPanel panel = new JPanel(new MigLayout("", "[]0", "0[]0"));
		panel.add(new JLabel(title));
		return panel;
	}

	protected JPanel createTablePanel(JTable table) {
		JPanel panel = new JPanel(new MigLayout());
		panel.add(createTableScrollPane(table));
		return panel;
	}

	protected JScrollPane createTableScrollPane(JTable table) {
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public AdditionalAttributesTablePanel getObjectAdditionalAttributesPanel() {
		return objectAdditionalAttributesPanel;
	}

	public AdditionalAttributesTablePanel getEnviromentAdditionalAttributesPanel() {
		return enviromentAdditionalAttributesPanel;
	}

	public void addDialogsSubmmitButtonsListener(ActionListener e) {
		objectAdditionalAttributesPanel.getAddAttribute().getOk().addActionListener(e);
		objectAdditionalAttributesPanel.getEditAttribute().getOk().addActionListener(e);
		enviromentAdditionalAttributesPanel.getAddAttribute().getOk().addActionListener(e);
		enviromentAdditionalAttributesPanel.getEditAttribute().getOk().addActionListener(e);
	}

	public void addCrudButtonsListener(ActionListener e) {
		objectAdditionalAttributesPanel.addCrudListeners(e);
		enviromentAdditionalAttributesPanel.addCrudListeners(e);
	}

	public void addCancelButtonsListeners(ActionListener e) {
		objectAdditionalAttributesPanel.addCancelListener(e);
		enviromentAdditionalAttributesPanel.addCancelListener(e);
	}

	public void addTableChangesListener(TableModelListener e) {
		objectAdditionalAttributesPanel.addChangesListener(e);
		enviromentAdditionalAttributesPanel.addChangesListener(e);
	}

	protected void setActionCommands() {
		setCrudButtonsActionCommands();
		setSubmmitButtonsActionCommands();
	}

	protected void setCrudButtonsActionCommands() {
		objectAdditionalAttributesPanel.getCreateAttribute().setActionCommand("Object Create Dialog");
		objectAdditionalAttributesPanel.getUpdateAttribute().setActionCommand("Object Edit Dialog");
		objectAdditionalAttributesPanel.getDeleteAttribute().setActionCommand("Delete Object Attribute");
		enviromentAdditionalAttributesPanel.getCreateAttribute().setActionCommand("Enviroment Create Dialog");
		enviromentAdditionalAttributesPanel.getUpdateAttribute().setActionCommand("Enviroment Edit Dialog");
		enviromentAdditionalAttributesPanel.getDeleteAttribute().setActionCommand("Delete Enviroment Attribute");
	}

	protected void setSubmmitButtonsActionCommands() {
		objectAdditionalAttributesPanel.getAddAttribute().getOk().setActionCommand("Create Object Attribute");
		objectAdditionalAttributesPanel.getEditAttribute().getOk().setActionCommand("Update Object Attribute");
		enviromentAdditionalAttributesPanel.getAddAttribute().getOk().setActionCommand("Create Enviroment Attribute");
		enviromentAdditionalAttributesPanel.getEditAttribute().getOk().setActionCommand("Update Enviroment Attribute");
	}
}