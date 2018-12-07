package admingui.views.user.dialogs;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import admingui.models.AssignedClientTableModel;
import net.miginfocom.swing.MigLayout;

public class AssignedClientsDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private AssignedClientTableModel assignedClientsTableModel;

	private JLabel assignedClients;
	private JTable assignedClientsTable;

	private JButton ok;

	public AssignedClientsDialog() {
		initComponents();
	}

	protected void initComponents() {
		initLabels();
		initTables();
		initButtons();
		initMainPanel();
		initMainDialog();
	}

	protected void initLabels() {
		assignedClients = new JLabel();
		assignedClients.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	protected void initTables() {
		assignedClientsTableModel = new AssignedClientTableModel();
		assignedClientsTable = new JTable(assignedClientsTableModel);
	}

	protected void initButtons() {
		ok = new JButton("Ok");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[]", "[][]"));
		mainPanel.add(assignedClients, "cell 0 0,alignx center");
		mainPanel.add(createAssignedClientsTable(), "cell 0 1");
		mainPanel.add(createOkPanel(), "cell 0 2,alignx right");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Assigned Clients");
		mainDialog.setBounds(500, 150, 250, 300);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.setResizable(false);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected JScrollPane createAssignedClientsTable() {
		JScrollPane scrollPane = new JScrollPane(assignedClientsTable);
		return scrollPane;
	}

	protected JPanel createOkPanel() {
		JPanel panel = new JPanel(new MigLayout("", "push[]"));
		panel.add(ok);
		return panel;
	}

	public void setLabelText(int userId) {
		assignedClients.setText("User " + userId + " Clients");
	}

	public void show() {
		mainDialog.setVisible(true);
	}

	public void dispose() {
		mainDialog.dispose();
	}

	public void addCancelListener(ActionListener e) {
		ok.addActionListener(e);
	}

	public JDialog getMainDialog() {
		return mainDialog;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JTable getAssignedClients() {
		return assignedClientsTable;
	}

	public AssignedClientTableModel getAssigendClientsTableModel() {
		return assignedClientsTableModel;
	}

	public JButton getCancel() {
		return ok;
	}
}