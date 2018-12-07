package admingui.views.policies;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;

public class PoliciesPanel {

	private JPanel mainPanel;

	private JTable usersPoliciesTable;
	private JTable clientsPoliciesTable;

	private JButton addUserPolicy;
	private JButton editUserPolicy;
	private JButton deleteUserPolicy;

	private JButton addClientPolicy;
	private JButton editClientPolicy;
	private JButton deleteClientPolicy;

	public PoliciesPanel() {
		initComponents();
	}

	protected void initComponents() {
		initButtons();
		initTables();
		initMainPanel();
	}

	protected void initButtons() {
		addUserPolicy = new JButton("Add...");
		editUserPolicy = new JButton("Edit...");
		deleteUserPolicy = new JButton("Delete");
		addClientPolicy = new JButton("Add...");
		editClientPolicy = new JButton("Edit...");
		deleteClientPolicy = new JButton("Delete");
	}

	protected void initTables() {
		usersPoliciesTable = new JTable();
		clientsPoliciesTable = new JTable();
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		mainPanel.add(createUsersPoliciesPanel());
		mainPanel.add(createClientsPoliciesPanel());
	}

	protected JPanel createUsersPoliciesPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Users Policies"));
		panel.add(new JScrollPane(usersPoliciesTable));
		panel.add(createUsersCrudButtonsPanel(), "alignx right");
		return panel;
	}

	protected JPanel createClientsPoliciesPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Clients Policies"));
		panel.add(new JScrollPane(clientsPoliciesTable));
		panel.add(createClientsrudButtonsPanel(), "alignx right");
		return panel;
	}

	protected JPanel createUsersCrudButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout());
		panel.add(addUserPolicy);
		panel.add(editUserPolicy);
		panel.add(deleteUserPolicy);
		return panel;
	}

	protected JPanel createClientsrudButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout());
		panel.add(addClientPolicy);
		panel.add(editClientPolicy);
		panel.add(deleteClientPolicy);
		return panel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}