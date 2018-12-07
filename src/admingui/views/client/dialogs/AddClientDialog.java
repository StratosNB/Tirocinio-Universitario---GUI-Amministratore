package admingui.views.client.dialogs;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admingui.models.Client;
import admingui.views.client.ClientForm;

import net.miginfocom.swing.MigLayout;

public class AddClientDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private ClientForm clientForm;

	private JButton cancel;
	private JButton ok;

	public AddClientDialog() {
		initComponents();
	}

	protected void initComponents() {
		clientForm = new ClientForm("Add Client");
		initButtons();
		initMainPanel();
		initMainDialog();
	}

	protected void initButtons() {
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel((new MigLayout("", "[]", "[]push[]")));
		mainPanel.add(clientForm.getMainPanel());
		mainPanel.add(createButtonsPanel(), "south");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Add a New Client");
		mainDialog.setBounds(500, 200, 270, 430);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.add(mainPanel);
	}

	private JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "push[][]"));
		panel.add(ok);
		panel.add(cancel);
		return panel;
	}

	public JButton getOk() {
		return ok;
	}

	public boolean isEmptyFixedAttributes() {
		return clientForm.isEmptyData();
	}

	public void clear() {
		clientForm.clearTexts();
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

	public void addOkListener(ActionListener e) {
		ok.addActionListener(e);
	}

	public ClientForm getClientForm() {
		return clientForm;
	}

	public Client getNewClient() {
		return clientForm.createClient();
	}

	public JDialog getDialog() {
		return this.mainDialog;
	}

}
