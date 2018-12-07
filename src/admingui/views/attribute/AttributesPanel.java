package admingui.views.attribute;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

import admingui.db.contract.DbContract;
import admingui.utils.AdminGuiStrings;
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
		userFixedAttributesTable = new JTable(AdminGuiStrings.userFixedAttributesTableData,
				AdminGuiStrings.fixedAttributesTableColumns);
		clientFixedAttributesTable = new JTable(AdminGuiStrings.clientFixedAttributesTableData,
				AdminGuiStrings.fixedAttributesTableColumns);

		userAdditionalAttributesPanel = new AdditionalAttributesTablePanel(DbContract.USERS_ATTRIBUTES_TABLE_NAME);
		clientAdditionalAttributesPanel = new AdditionalAttributesTablePanel(DbContract.CLIENTS_ATTRIBUTES_TABLE_NAME);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		mainPanel.add(createFixedAttributesPanel());
		mainPanel.add(createAdditionalAttributePanel());
		mainPanel.setVisible(true);
	}

	protected JPanel createFixedAttributesPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Fixed Attributes"));
		panel.setPreferredSize(new Dimension(435, 190));
		panel.setLayout(new MigLayout("", "[][]", "[][]"));
		panel.add(createBoldJlabel("Users"), "cell 0 0");
		panel.add(createScrollPane(userFixedAttributesTable), "top,cell 0 1");
		panel.add(createBoldJlabel("Clients"), "cell 1 0");
		panel.add(createScrollPane(clientFixedAttributesTable), "cell 1 1");
		return panel;
	}

	protected JPanel createAdditionalAttributePanel() {
		JPanel panel = new JPanel(new MigLayout("", "0[]0[]0", "[]0[][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Additional Attributes"));
		panel.add(createBoldJlabel("  Users"), "cell 0 0");
		panel.add(userAdditionalAttributesPanel.getMainPanel(), "cell 0 1");
		panel.add(createBoldJlabel(" Clients"), "cell 1 0");
		panel.add(clientAdditionalAttributesPanel.getMainPanel(), "cell 1 1");
		return panel;
	}

	protected JLabel createBoldJlabel(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		return lbl;
	}

	protected JScrollPane createScrollPane(JTable table) {
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(330, 300));
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
	
	public void addTableChangesListener(TableModelListener e){
		userAdditionalAttributesPanel.addChangesListener(e);
		clientAdditionalAttributesPanel.addChangesListener(e);
	}

	protected void setActionCommands() {
		setCrudButtonsActionCommands();
		setSubmmitButtonsActionCommands();
	}
	
	protected void setCrudButtonsActionCommands(){
		userAdditionalAttributesPanel.getCreateAttribute().setActionCommand("User Create Dialog");
		userAdditionalAttributesPanel.getUpdateAttribute().setActionCommand("User Edit Dialog");
		userAdditionalAttributesPanel.getDeleteAttribute().setActionCommand("Delete User Attribute");
		clientAdditionalAttributesPanel.getCreateAttribute().setActionCommand("Client Create Dialog");
		clientAdditionalAttributesPanel.getUpdateAttribute().setActionCommand("Client Edit Dialog");
		clientAdditionalAttributesPanel.getDeleteAttribute().setActionCommand("Delete Client Attribute");
	}
	
	protected void setSubmmitButtonsActionCommands(){
		userAdditionalAttributesPanel.getAddAttribute().getOk().setActionCommand("Create User Attribute");
		userAdditionalAttributesPanel.getEditAttribute().getOk().setActionCommand("Update User Attribute");
		clientAdditionalAttributesPanel.getAddAttribute().getOk().setActionCommand("Create Client Attribute");
		clientAdditionalAttributesPanel.getEditAttribute().getOk().setActionCommand("Update Client Attribute");
	}
}
