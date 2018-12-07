package admingui.views.user.dialogs;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import admingui.models.AvailableClientTableModel;
import admingui.models.Client;
import admingui.views.attribute.shared.AdditionalAttributesPanel;
import net.miginfocom.swing.MigLayout;

public class AssignClientsDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;
	private JSplitPane splitPane;

	private JTable availableClientsTable;
	private JScrollPane tableScrollPane;
	private JPanel clientDataPanel;

	private AvailableClientTableModel availableClientsTableModel;

	private JLabel availableClients;

	private JLabel id;
	private JLabel name;
	private JLabel ownerId;

	private JLabel clientId;
	private JLabel clientName;
	private JLabel clientUserId;

	private AdditionalAttributesPanel additionalAttributesPanel;

	private JButton assign;
	private JButton cancel;

	public AssignClientsDialog() {
		initComponents();
	}

	protected void initComponents() {
		additionalAttributesPanel = new AdditionalAttributesPanel();
		initLabels();
		initTables();
		initButtons();
		initClientDataPanel();
		initSplitPane();
		initMainPanel();
		initMainDialog();
	}

	protected void initLabels() {
		availableClients = new JLabel();

		id = new JLabel("ID:");
		ownerId = new JLabel("Owner ID:");
		name = new JLabel("Name:");
		setBold();

		clientId = new JLabel();
		clientUserId = new JLabel();
		clientName = new JLabel();
	}

	protected void initTables() {
		availableClientsTableModel = new AvailableClientTableModel();
		availableClientsTable = new JTable(availableClientsTableModel);

		tableScrollPane = new JScrollPane(availableClientsTable);
	}

	protected void initButtons() {
		assign = new JButton("Assign");
		cancel = new JButton("Ok");
	}

	protected void initClientDataPanel() {
		clientDataPanel = new JPanel(new MigLayout("", "[]", "[][]"));
		clientDataPanel.add(createClientFixedAttributesPanel(), "wrap");
		clientDataPanel.add(additionalAttributesPanel.getMainPanel());
	}

	protected void initSplitPane() {
		splitPane = new JSplitPane(SwingConstants.VERTICAL, tableScrollPane, clientDataPanel);
		splitPane.setDividerLocation(170);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[]", "[][][]"));
		mainPanel.add(availableClients, "wrap, cell 0 0");
		mainPanel.add(splitPane, "wrap, cell 0 1");
		mainPanel.add(createButtonsPanel(), "alignx right, cell 0 2");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Assign Clients to an User");
		mainDialog.setBounds(400, 150, 500, 350);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.setResizable(false);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected JPanel createClientFixedAttributesPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 4", "[][]15[][]", "[][][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Fixed Attributes"));
		panel.add(id, "right");
		panel.add(clientId);
		panel.add(ownerId, "right");
		panel.add(clientUserId);
		panel.add(name, "right");
		panel.add(clientName);
		return panel;
	}

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "push[]"));
		panel.add(assign);
		panel.add(cancel);
		return panel;
	}

	protected void setBold() {
		availableClients.setFont(new Font("Tahoma", Font.BOLD, 11));
		id.setFont(new Font("Tahoma", Font.BOLD, 11));
		name.setFont(new Font("Tahoma", Font.BOLD, 11));
		ownerId.setFont(new Font("Tahoma", Font.BOLD, 11));
	} 

	public void setFixedAttributesData(Client client) {
		clientId.setText(String.valueOf(client.getId()));
		clientName.setText(client.getName());
		clientUserId.setText(String.valueOf(client.getUserId()));
	}

	public void setLabelText(int userId) {
		availableClients.setText("User " + userId + " Clients");
	}

	public void show() {
		mainDialog.setVisible(true);
	}

	public void dispose() {
		mainDialog.dispose();
	}

	public void addCancelListener(ActionListener e) {
		cancel.addActionListener(e);
	}

	public JDialog getMainDialog() {
		return mainDialog;
	}

	public JTable getAvailableClientsTable() {
		return availableClientsTable;
	}

	public JPanel getClientData() {
		return clientDataPanel;
	}

	public JTable getAvaibleClientsTable() {
		return availableClientsTable;
	}

	public AvailableClientTableModel getAvaibleClientTableModel() {
		return availableClientsTableModel;
	}

	public AdditionalAttributesPanel getAdditionalAttributesPanel() {
		return additionalAttributesPanel;
	}

	public JButton getCancel() {
		return cancel;
	}
}