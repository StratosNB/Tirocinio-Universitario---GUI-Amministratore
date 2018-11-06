package view.userPanes;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.User;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class UserView extends JPanel {

	private JTextField txtUserID = new JTextField(5);
	private JTextField txtName = new JTextField(15);
	private JTextField txtSurname = new JTextField(15);
	private JTextField txtPassword = new JTextField(15);

	private JLabel lblUserClients = new JLabel("User Clients ID:");
	private JComboBox boxClientsOfUser = new JComboBox();// the clients of the
														// user

	private JLabel lblAvaibleClients = new JLabel("Avaible Clients ID:");

	JPanel userPanel;

	public UserView() {
		initComponents();

	}

	protected void initComponents() {
		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		setLayout(layout);

		// put all the panels together
		add(getUserPanel());

	}

	// creates a user with the input data
	public User getFieldData() {

		User user = new User();
		user.setUserID(txtUserID.getText());
		user.setName(txtName.getText());
		user.setSurname(txtSurname.getText());
		user.setPassword(txtPassword.getText());

		return user;
	}

	public void setFieldData(User user) {

		txtUserID.setText(user.getUserID());
		txtName.setText(user.getName());
		txtSurname.setText(user.getSurname());
		txtPassword.setText(user.getPassword());

	}

	private JPanel getUserPanel() {

		userPanel = getPanel("User");
		userPanel.setLayout(new MigLayout("wrap 2", "[] 16 []"));

		userPanel.add(new JLabel("User ID:"), "right");
		userPanel.add(txtUserID);

		userPanel.add(new JLabel("Name:"), "right");
		userPanel.add(txtName);

		userPanel.add(new JLabel("Surname:"), "right");
		userPanel.add(txtSurname);

		userPanel.add(new JLabel("Password:"), "right");
		userPanel.add(txtPassword);

		userPanel.add(lblUserClients, "right");
		userPanel.add(boxClientsOfUser);

		return userPanel;

	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	public boolean isEmptyFieldData() {
		return (txtUserID.getText().trim().isEmpty() && txtName.getText().trim().isEmpty()
				&& txtSurname.getText().trim().isEmpty() && txtPassword.getText().trim().isEmpty());
	}

	public JComboBox getBoxUserClients() {
		return this.boxClientsOfUser;
	}

	// cambia puestos
	public void swap() {
		this.userPanel.remove(lblUserClients);
		this.userPanel.remove(boxClientsOfUser);

		this.userPanel.add(lblAvaibleClients, "right");

		this.userPanel.revalidate();

	}

	// regresa a como esta antes
	public void swapBack() {

		this.userPanel.remove(lblAvaibleClients);
		this.userPanel.add(lblUserClients, "right");
		this.userPanel.add(boxClientsOfUser);

		this.userPanel.revalidate();
	}

	public void initBoxUserClients(ArrayList<String> clientsID) {

		for (String c : clientsID) {
			this.boxClientsOfUser.addItem(c);

		}
	}

}
