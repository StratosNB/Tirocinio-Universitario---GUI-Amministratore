package view.clientPanes;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JTextField;

import models.Client;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ClientView extends JPanel {

	private JTextField txtClientID = new JTextField(5);
	private JTextField txtName = new JTextField(15);

	private JLabel ownerUserID = new JLabel();

	public ClientView() {
		initComponents();

	}

	protected void initComponents() {
		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		setLayout(layout);

		// put all the panels together
		add(getClientPanel(), "span 1 4");

	}

	public Client getFieldData() {
		Client client = new Client();

		client.setClientID(txtClientID.getText());
		client.setClientName(txtName.getText());

		return client;
	}

	public void setFieldData(Client client) {
		txtClientID.setText(client.getClientID());
		txtName.setText(client.getClientName());

	}

	private JPanel getClientPanel() {

		JLabel lbl1 = new JLabel("");
		lbl1.setVisible(false);
		JLabel lbl2 = new JLabel("");
		lbl2.setVisible(false);
		JLabel lbl3 = new JLabel("");
		lbl3.setVisible(false);
		TextField txt1 = new TextField(15);
		txt1.setVisible(false);
		TextField txt2 = new TextField(15);
		txt2.setVisible(false);
		TextField txt3 = new TextField(15);
		txt3.setVisible(false);

		JPanel panel = getPanel("Client");
		panel.setLayout(new MigLayout("wrap 2", "[] 16 []"));

		panel.add(new JLabel("Client ID:"), "right");
		panel.add(txtClientID);

		panel.add(new JLabel("Name:"), "right");
		panel.add(txtName);

		panel.add(new JLabel("User ID:"), "right");
		panel.add(ownerUserID);

		panel.add(lbl2, "right");
		panel.add(txt2);

		panel.add(lbl3, "right");
		panel.add(txt3);

		return panel;

	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	public boolean isEmptyFieldData() {
		return (txtClientID.getText().trim().isEmpty() && txtName.getText().trim().isEmpty());
	}

	public JLabel getLblOwnerUserID() {
		return this.ownerUserID;
	}

}