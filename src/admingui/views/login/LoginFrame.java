package admingui.views.login;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class LoginFrame {

	private JFrame frame;

	private JTextField userIDInput;
	private JPasswordField passwordInput;

	private JButton login;
	private JButton cancel;

	public LoginFrame() {
		initComponents();
	}

	protected void initComponents() {
		initTexts();
		initButtons();
		initFrame();
	}

	protected void initTexts() {
		userIDInput = new JTextField(5);
		passwordInput = new JPasswordField(12);
	}

	protected void initButtons() {
		login = new JButton("Login");
		cancel = new JButton("Cancel");
	}

	protected void initFrame() {
		
		frame = new JFrame();
		frame.getContentPane().setLayout(new MigLayout("wrap 1", "[grow] 16 [grow]"));
		frame.setTitle("Admin-Login Frame");
		frame.setSize(250, 160);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(createUserCredentialsPanel(), "cell 0 0,alignx right");
		frame.getContentPane().add(createLoginButtonsPanel(), "cell 0 2,alignx right");
	}

	private JPanel createUserCredentialsPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 2"));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(new JLabel("ID:"), "right");
		panel.add(userIDInput);
		panel.add(new JLabel("Password:"), "right");
		panel.add(passwordInput);
		return panel;
	}

	public JPanel createLoginButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 2"));
		panel.add(login);
		panel.add(cancel);
		return panel;
	}

	public void addLoginListener(ActionListener loginListener) {
		login.addActionListener(loginListener);
		cancel.addActionListener(loginListener);
	}

	public void show() {
		frame.setVisible(true);
	}

	public boolean isEmptyData() {
		if (userIDInput.getText().isEmpty() || passwordInput.getPassword().length == 0) {
			return true;
		}
		return false;
	}

	public String getUserIDInput() {
		return userIDInput.getText();
	}

	public String getPasswordInput() {
		return String.valueOf(passwordInput.getPassword());
	}

	public JFrame getMainFrame() {
		return frame;
	}
}