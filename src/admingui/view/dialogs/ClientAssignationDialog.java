package admingui.view.dialogs;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import admingui.model.Client;
import admingui.view.AdditionalAttributesViewPanel;
import net.miginfocom.swing.MigLayout;

public class ClientAssignationDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private JSplitPane splitPane1;
	private JSplitPane splitPane2;

	private JScrollPane sp1;
	private JScrollPane sp2;

	private AdditionalAttributesViewPanel avPanel1;
	private AdditionalAttributesViewPanel avPanel2;

	private JTable assignedClientsTable;
	private JTable availableClientsTable;

	private JLabel lbl1;
	private JLabel lbl2;

	private JButton addAvaibleClientButton;
	private JButton removeAssignedClientButton;
	private JButton closeButton;

	public ClientAssignationDialog() {
		initComponents();
	}

	protected void initComponents() {
		initButtons();
		initLabels();
		sp1 = new JScrollPane();
		sp2 = new JScrollPane();
		initTables();
		initPanels();
		initMainPanel();
		initMainDialog();
	}

	protected void initButtons() {
		addAvaibleClientButton = new JButton("<<");
		removeAssignedClientButton = new JButton(">>");
		closeButton = new JButton("Close");

		addAvaibleClientButton.setActionCommand("ASSIGN CLIENT");
		removeAssignedClientButton.setActionCommand("REMOVE CLIENT");
		closeButton.setActionCommand("CLOSE CLIENT ASSIGNATION DIALOG");
	}

	protected void initLabels() {
		lbl1 = new JLabel();
		lbl2 = new JLabel("Available Clients:");
	}

	protected void initTables() {

		assignedClientsTable = new JTable();
		availableClientsTable = new JTable();

		availableClientsTable.getModel();

		assignedClientsTable.setFillsViewportHeight(true);
		availableClientsTable.setFillsViewportHeight(true);

		sp1 = new JScrollPane(assignedClientsTable);
		sp2 = new JScrollPane(availableClientsTable);
	}

	protected void initPanels() {
		avPanel1 = new AdditionalAttributesViewPanel();
		avPanel2 = new AdditionalAttributesViewPanel();

		splitPane1 = new JSplitPane(SwingConstants.VERTICAL, avPanel1.getScrollPane(), sp1);
		splitPane2 = new JSplitPane(SwingConstants.VERTICAL, sp2, avPanel2.getScrollPane());

		splitPane1.setDividerLocation(210);
		splitPane2.setDividerLocation(160);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[][][]", "[][][]"));
		mainPanel.add(lbl1, "cell 0 0,alignx right");
		mainPanel.add(splitPane1, "cell 0 1");
		mainPanel.add(createControlButtonsPanel(), "cell 1 1,alignx center,aligny center");
		mainPanel.add(lbl2, "cell 2 0");
		mainPanel.add(splitPane2, "cell 2 1");
		mainPanel.add(closeButton, "cell 2 2,alignx right,aligny bottom");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Client Assignation Manager");
		mainDialog.setBounds(200, 200, 850, 350);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		// mainDialog.setResizable(false);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected JPanel createControlButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[]", "[]60[]"));
		panel.add(addAvaibleClientButton, "cell 0 0");
		panel.add(removeAssignedClientButton, "cell 0 1");
		return panel;
	}

	public void setLblText(String userId) {
		lbl1.setText("User " + userId + " Clients:");
	}

	public Client getSelectedClientFromTable(JTable table) {
		String selectedClientId = (String) table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
		String selectedClientName = (String) table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
		Client c = new Client();
		c.setId(Integer.valueOf(selectedClientId));
		c.setName(selectedClientName);
		return c;
	}
	
	public void addClientToTable(JTable table, Client client){
		String [] clientData = {String.valueOf(client.getId()),client.getName()};
		((DefaultTableModel)table.getModel()).addRow(clientData);
	}

	
	public void removeClientFromTable(JTable table){
		((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
	}

	public void show() {
		this.mainDialog.setVisible(true);
	}

	public JDialog getMainDialog() {
		return this.mainDialog;
	}

	public JTable getAssignedClientsTable() {
		return assignedClientsTable;
	}

	public JTable getAvailableClientsTable() {
		return availableClientsTable;
	}

	public AdditionalAttributesViewPanel getAvPanel1() {
		return avPanel1;
	}

	public AdditionalAttributesViewPanel getAvPanel2() {
		return avPanel2;
	}

	public JButton getAddAvaibleClientButton() {
		return addAvaibleClientButton;
	}

	public JButton getRemoveAssignedClientButton() {
		return removeAssignedClientButton;
	}

	public JButton getCloseButton() {
		return closeButton;
	}
}