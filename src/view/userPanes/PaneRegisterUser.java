package view.userPanes;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import models.User;
import net.miginfocom.swing.MigLayout;
import view.attributePanes.AttributesView;

@SuppressWarnings("serial")
public class PaneRegisterUser extends JPanel {

	private JPanel viewUserPane;
	private AttributesView attributeView;

	private JButton assignClientBtn = new JButton("Add");
	private JButton registerButton = new JButton("Register");

	private JButton backButton = new JButton("Back");
	private JButton finishButton = new JButton("Finish");

	private JTextField txtUserID = new JTextField(5);
	private JTextField txtName = new JTextField(15);
	private JTextField txtSurname = new JTextField(15);
	private JTextField txtPassword = new JTextField(15);

	private JLabel lblAvaibleClients = new JLabel("Avaible clients:");
	private JComboBox boxAvaibleClients = new JComboBox();

	private JPanel registerUserView;

	public PaneRegisterUser() {
		setLayout(new CardLayout());
		initComponents();
	}

	private void initComponents() {

		attributeView = new AttributesView();

		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		viewUserPane = new JPanel(layout);

		// put all the panels together
		viewUserPane.add(getRegisterUserView(), "flowx,alignx left,growy");
		viewUserPane.add(attributeView, "flowx,cell 0 1");
		viewUserPane.add(registerButton, "cell 0 1");
		viewUserPane.add(getNavButtonsPanel(), "south");

		add(viewUserPane, "viewUserPane");

	}

	private JPanel getRegisterUserView() {
		registerUserView = getPanel("User data");
		registerUserView.setLayout(new MigLayout("wrap 2", "[] 16 []"));

		registerUserView.add(new JLabel("User ID:"), "right");
		registerUserView.add(txtUserID);

		registerUserView.add(new JLabel("Name:"), "right");
		registerUserView.add(txtName);

		registerUserView.add(new JLabel("Surname:"), "right");
		registerUserView.add(txtSurname);

		registerUserView.add(new JLabel("Password:"), "right");
		registerUserView.add(txtPassword);

		registerUserView.add(lblAvaibleClients, "right");
		registerUserView.add(boxAvaibleClients, "flowx");

		registerUserView.add(assignClientBtn, "cell 1 4");

		return registerUserView;
	}

	public User getFieldData() {

		User user = new User();
		user.setUserID(txtUserID.getText());
		user.setName(txtName.getText());
		user.setSurname(txtSurname.getText());
		user.setPassword(txtPassword.getText());

		return user;
	}

	public boolean isEmptyFieldData() {
		return (txtUserID.getText().trim().isEmpty() && txtName.getText().trim().isEmpty()
				&& txtSurname.getText().trim().isEmpty() && txtPassword.getText().trim().isEmpty());
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

	public void addNavListener(ActionListener navListener) {
		// TODO Auto-generated method stub
		backButton.addActionListener(navListener);
		finishButton.addActionListener(navListener);
	}

	public void addRegListener(ActionListener regListener) {
		registerButton.addActionListener(regListener);
	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}
}
