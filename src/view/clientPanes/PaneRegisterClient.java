package view.clientPanes;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import models.Client;
import net.miginfocom.swing.MigLayout;
import view.attributePanes.AttributesView;

@SuppressWarnings("serial")
public class PaneRegisterClient extends JPanel {

	private JPanel viewClientPane;
	private AttributesView attributeView;

	private JButton registerButton = new JButton("Register");

	private JButton backButton = new JButton("Back");
	private JButton finishButton = new JButton("Finish");

	public JTextField txtClientID = new JTextField(5);

	public JTextField txtName = new JTextField(15);

	private JPanel registerClientView;

	public PaneRegisterClient() {
		setLayout(new CardLayout());
		initComponents();
	}

	private void initComponents() {

		attributeView = new AttributesView();

		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		viewClientPane = new JPanel(layout);

		// put all the panels together
		viewClientPane.add(getRegisterUserView(), "flowx,alignx left,growy");
		viewClientPane.add(attributeView, "flowx,cell 0 1");
		viewClientPane.add(registerButton, "cell 0 1");
		viewClientPane.add(getNavButtonsPanel(), "south");

		add(viewClientPane, "viewUserPane");

	}

	private JPanel getRegisterUserView() {
		registerClientView = getPanel("Client data");
		registerClientView.setLayout(new MigLayout("wrap 2", "[] 16 []"));

		registerClientView.add(new JLabel("Client ID:"), "right");
		registerClientView.add(txtClientID);

		registerClientView.add(new JLabel("Name:"), "right");
		registerClientView.add(txtName);

		return registerClientView;
	}

	private JPanel getNavButtonsPanel() {

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setLayout(new MigLayout("center", "[] []"));

		panel.add(backButton);
		panel.add(finishButton);

		return panel;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public boolean isEmptyFieldData() {
		return (txtClientID.getText().trim().isEmpty() && txtName.getText().trim().isEmpty());
	}

	public Client getFieldData() {
		Client client = new Client();

		String clientID = txtClientID.getText();
		String clientName = txtName.getText();

		client.setClientID(clientID);
		client.setClientName(clientName);

		System.out.println("method getfieldata: " + client.getClientID() + client.getClientName());

		return client;
	}

	public void setFieldData(Client client) {
		txtClientID.setText(client.getClientID());
		txtName.setText(client.getClientName());

	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	public void addNavListener(ActionListener navListener) {
		// TODO Auto-generated method stub
		backButton.addActionListener(navListener);
		finishButton.addActionListener(navListener);
	}

	public void addRegListener(ActionListener regListener) {
		registerButton.addActionListener(regListener);
	}

	public JTextField getTxtClientID() {
		return txtClientID;
	}

	public JTextField getTxtName() {
		return txtName;
	}

}
