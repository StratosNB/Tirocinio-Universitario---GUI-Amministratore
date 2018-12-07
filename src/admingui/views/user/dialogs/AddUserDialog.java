package admingui.views.user.dialogs;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admingui.models.User;
import admingui.views.user.UserForm;

import net.miginfocom.swing.MigLayout;

public class AddUserDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private UserForm userForm;

	private JButton cancel;
	private JButton ok;

	public AddUserDialog() {
		initComponents();
	}

	protected void initComponents() {
		userForm = new UserForm("User Data");
		initButtons();
		initMainPanel();
		initMainDialog();
		setActionCommands();
	}

	protected void initButtons() {
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[]", "[]push[]"));
		mainPanel.add(userForm.getMainPanel());
		mainPanel.add(createButtonsPanel(), "south");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Add a New User");
		mainDialog.setBounds(500, 200, 270, 430);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.add(mainPanel);
	}

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "push[][]"));
		panel.add(ok);
		panel.add(cancel);
		return panel;
	}

	public User getNewUser() {
		return userForm.createUser();
	}

	public void setFixedAttributes(User user) {
		// TODO Auto-generated method stub

	}

	public JDialog getDialog() {
		return mainDialog;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JButton getOk() {
		return ok;
	}

	public UserForm getUserForm() {
		return userForm;
	}

	public boolean isEmptyFixedAttributes() {
		return userForm.isEmptyData();
	}

	public void clear() {
		userForm.clearTexts();
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

	protected void setActionCommands() {
		ok.setActionCommand("Add user");
	}
}