package admingui.view.dialogs;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admingui.model.User;
import admingui.view.UserForm;
import net.miginfocom.swing.MigLayout;

public class EditUserDialog {
	
	private JDialog mainDialog;
	private JPanel mainPanel;

	private UserForm userForm;

	private JButton ok;
	private JButton cancel;

	public EditUserDialog() {
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
		mainDialog.setTitle("Edit an User");
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
	
	public boolean isEmptyFixedAttributes() {
		return userForm.isEmptyData();
	}

	public JButton getCancel() {
		return cancel;
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

	public void setFixedAttributes(User user) {
		userForm.setFixedAttributesData(user);
	}
	
	public JDialog getDialog() {
		return mainDialog;
	}
	
	public UserForm getUserForm(){
		return userForm;
	}

	public JButton getOk() {
		return ok;
	}
	
	public User getNewUser(){
		return userForm.createUser();
	}
	
	protected void setActionCommands(){
		ok.setActionCommand("Edit user");
	}
}