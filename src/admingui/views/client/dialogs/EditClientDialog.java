package admingui.views.client.dialogs;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admingui.models.Client;
import admingui.views.client.ClientForm;
import net.miginfocom.swing.MigLayout;

public class EditClientDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private ClientForm clientForm;

	private JButton ok;
	private JButton cancel;

	public EditClientDialog() {
		initComponents();
	}

	protected void initComponents() {
		clientForm = new ClientForm("Edit Client");
		initButtons();
		initMainPanel();
		initMainDialog();
	}

	protected void initButtons() {
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[]", "[]push[]"));
		mainPanel.add(clientForm.getMainPanel());
		mainPanel.add(createButtonsPanel(), "south");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Edit a Client");
		mainDialog.setBounds(500, 200, 270, 430);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "push[][]"));
		panel.add(ok);
		panel.add(cancel);
		return panel;
	}

	public void show() {
		mainDialog.setVisible(true);
	}

	public void dispose() {
		mainDialog.dispose();
	}

	public void addListener(ActionListener e) {
		cancel.addActionListener(e);
	}

	public void setFixedAttributes(Client client) {
		clientForm.setFixedAttributesData(client);
	}

	public boolean isEmptyFixedAttributes() {
		return clientForm.isEmptyData();
	}

	public JDialog getDialog() {
		return mainDialog;
	}

	public ClientForm getClientForm() {
		return clientForm;
	}

	public JButton getOk() {
		return ok;
	}

	public Client getNewClient() {
		return clientForm.createClient();
	}

}
