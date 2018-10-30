package viewPanels;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtPassword;
	private JTextField txtUserName;

	public static void main(String[] args) {

		try {
			LoginUI dialog = new LoginUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public LoginUI() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUserName = new JLabel("UserName:");
		lblUserName.setBounds(24, 37, 68, 23);
		contentPanel.add(lblUserName);

		txtUserName = new JTextField();
		txtUserName.setBounds(141, 38, 107, 20);
		contentPanel.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(24, 71, 68, 14);
		contentPanel.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(141, 68, 107, 20);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userName = txtUserName.getText();
				String password = txtPassword.getText();

				if (userName.equals("")) {
					// Error msg

				} else if (password.equals("")) {
					// Error msg

				} else if (password.equals("admin")) {

					closeDialog();
					AdminUI frame;
					try {
						frame = new AdminUI();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					// querys the db for credentials

					closeDialog();

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {

							} catch (Exception e) {
								e.printStackTrace();

							}
						}

					});
				}

			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private void closeDialog() {
		this.setVisible(false);

	}

}
