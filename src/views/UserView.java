package views;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.User;
import net.miginfocom.swing.MigLayout;

public class UserView extends JPanel {

	private JTextField txtUserID = new JTextField(5);
	private JTextField txtName = new JTextField(15);
	private JTextField txtSurname = new JTextField(15);
	private JTextField txtPassword = new JTextField(15);
	private JTextField txtClientID = new JTextField(5);

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
		user.setClientID(txtClientID.getText());

		return user;
	}

	public void setFieldData(User user) {
		
		txtUserID.setText(user.getUserID());
		txtName.setText(user.getName());
		txtSurname.setText(user.getSurname());
		txtPassword.setText(user.getPassword());
		txtClientID.setText(user.getClientID());

	}

	private JPanel getUserPanel() {
		JPanel panel = getPanel("User");
		panel.setLayout(new MigLayout("wrap 2", "[] 16 []"));

		panel.add(new JLabel("User ID:"), "right");
		panel.add(txtUserID);

		panel.add(new JLabel("Name:"), "right");
		panel.add(txtName);

		panel.add(new JLabel("Surname:"), "right");
		panel.add(txtSurname);

		panel.add(new JLabel("Password:"), "right");
		panel.add(txtPassword);

		panel.add(new JLabel("Client ID:"), "right");
		panel.add(txtClientID);

		return panel;

	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	public boolean isEmptyFieldData() {
		return (txtUserID.getText().trim().isEmpty() && txtName.getText().trim().isEmpty()
				&& txtSurname.getText().trim().isEmpty() && txtPassword.getText().trim().isEmpty()
				&& txtClientID.getText().trim().isEmpty());
	}
}

