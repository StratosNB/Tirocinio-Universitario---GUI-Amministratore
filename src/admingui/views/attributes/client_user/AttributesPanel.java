package admingui.views.attributes.client_user;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

import admingui.db.contract.DbContract;
import admingui.utils.AdminGuiComponentsConstants;

import net.miginfocom.swing.MigLayout;

public class AttributesPanel {

	private JPanel mainPanel;

	private JTable userFixedAttributesTable;
	private JTable clientFixedAttributesTable;

	private AdditionalAttributesTablePanel userAdditionalAttributesPanel;
	private AdditionalAttributesTablePanel clientAdditionalAttributesPanel;

	public AttributesPanel() {
		initComponents();
	}

	protected void initComponents() {
		initTables();
		initMainPanel();
		setActionCommands();
	}

	protected void initTables() {
		userFixedAttributesTable = new JTable(AdminGuiComponentsConstants.USERS_FIXED_ATTRIBUTES_TABLE_DATA,
				AdminGuiComponentsConstants.FIXED_ATTRIBUTES_TABLE_COLUMNS);
		userFixedAttributesTable.setFillsViewportHeight(true);
		clientFixedAttributesTable = new JTable(AdminGuiComponentsConstants.CLIENTS_FIXED_ATTRIBUTES_TABLE_DATA,
				AdminGuiComponentsConstants.FIXED_ATTRIBUTES_TABLE_COLUMNS);
		clientFixedAttributesTable.setFillsViewportHeight(true);

		userAdditionalAttributesPanel = new AdditionalAttributesTablePanel(DbContract.USERS_ATTRIBUTES_TABLE_NAME);
		clientAdditionalAttributesPanel = new AdditionalAttributesTablePanel(DbContract.CLIENTS_ATTRIBUTES_TABLE_NAME);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		mainPanel.add(createUserAttributesPanel());
		mainPanel.add(createClientsAttributesPanel());
		mainPanel.setVisible(true);
	}

	protected JPanel createUserAttributesPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]", "0[]0[]0"));
		panel.setBorder(BorderFactory.createTitledBorder("Users Attributes"));
		panel.add(createTitlePanel("Fixed"), "cell 0 0");
		panel.add(createTablePanel(userFixedAttributesTable), "cell 0 1");
		panel.add(createTitlePanel("Additional"), "cell 1 0");
		panel.add(userAdditionalAttributesPanel.getMainPanel(), "cell 1 1");
		return panel;
	}

	protected JPanel createClientsAttributesPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]", "0[]0[]0"));
		panel.setBorder(BorderFactory.createTitledBorder("Clients Attributes"));
		panel.add(createTitlePanel("Fixed"), "cell 0 0");
		panel.add(createTablePanel(clientFixedAttributesTable), "cell 0 1");
		panel.add(createTitlePanel("Additional"), "cell 1 0");
		panel.add(clientAdditionalAttributesPanel.getMainPanel(), "cell 1 1");
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
		// scrollPane.setPreferredSize(new Dimension(330, 225));
		return scrollPane;
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public AdditionalAttributesTablePanel getUserAdditionalAttributesPanel() {
		return userAdditionalAttributesPanel;
	}

	public AdditionalAttributesTablePanel getClientAdditionalAttributesPanel() {
		return clientAdditionalAttributesPanel;
	}

	public void addDialogsSubmmitButtonsListener(ActionListener e) {
		userAdditionalAttributesPanel.getAddAttribute().getOk().addActionListener(e);
		userAdditionalAttributesPanel.getEditAttribute().getOk().addActionListener(e);
		clientAdditionalAttributesPanel.getAddAttribute().getOk().addActionListener(e);
		clientAdditionalAttributesPanel.getEditAttribute().getOk().addActionListener(e);
	}

	public void addCrudButtonsListener(ActionListener e) {
		userAdditionalAttributesPanel.addCrudListeners(e);
		clientAdditionalAttributesPanel.addCrudListeners(e);
	}

	public void addCancelButtonsListeners(ActionListener e) {
		userAdditionalAttributesPanel.addCancelListener(e);
		clientAdditionalAttributesPanel.addCancelListener(e);
	}

	public void addTableChangesListener(TableModelListener e) {
		userAdditionalAttributesPanel.addChangesListener(e);
		clientAdditionalAttributesPanel.addChangesListener(e);
	}

	protected void setActionCommands() {
		setCrudButtonsActionCommands();
		setSubmmitButtonsActionCommands();
	}

	protected void setCrudButtonsActionCommands() {
		userAdditionalAttributesPanel.getCreateAttribute().setActionCommand("User Create Dialog");
		userAdditionalAttributesPanel.getUpdateAttribute().setActionCommand("User Edit Dialog");
		userAdditionalAttributesPanel.getDeleteAttribute().setActionCommand("Delete User Attribute");
		clientAdditionalAttributesPanel.getCreateAttribute().setActionCommand("Client Create Dialog");
		clientAdditionalAttributesPanel.getUpdateAttribute().setActionCommand("Client Edit Dialog");
		clientAdditionalAttributesPanel.getDeleteAttribute().setActionCommand("Delete Client Attribute");
	}

	protected void setSubmmitButtonsActionCommands() {
		userAdditionalAttributesPanel.getAddAttribute().getOk().setActionCommand("Create User Attribute");
		userAdditionalAttributesPanel.getEditAttribute().getOk().setActionCommand("Update User Attribute");
		clientAdditionalAttributesPanel.getAddAttribute().getOk().setActionCommand("Create Client Attribute");
		clientAdditionalAttributesPanel.getEditAttribute().getOk().setActionCommand("Update Client Attribute");
	}
}