package view.mainPanes;

import java.awt.EventQueue;
import java.awt.Panel;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import controllers.clients.PaneClientAttributeSetController;
import controllers.clients.PaneClientController;
import controllers.clients.PaneRegisterClientController;
import controllers.users.PaneRegisterUserController;
import controllers.users.PaneUserAttributeSetController;
import controllers.users.PaneUserController;
import view.clientPanes.PaneClient;
import view.userPanes.PaneUser;

@SuppressWarnings("serial")
public class PaneMain extends JFrame {

	private JTabbedPane tabbedPaneAdmin;
	static JTabbedPane tabbedPaneUsers;
	static JTabbedPane tabbedPaneClients;

	private static PaneUserController userController;
	private static PaneClientController clientController;

	private static PaneUserAttributeSetController userAttributeSetController;
	private static PaneClientAttributeSetController clientAttributeSetController;

	private static PaneRegisterUserController paneRegisterUserController;
	private static PaneRegisterClientController paneRegisterClientController;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public PaneMain() throws SQLException {
		setType(Type.POPUP);

		PaneUser paneUser = new PaneUser();
		PaneClient paneClient = new PaneClient();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 363, 500);
		setTitle("Management");

		tabbedPaneAdmin = new JTabbedPane();

		tabbedPaneAdmin.addTab("Users", paneUser);
		tabbedPaneAdmin.addTab("Clients", paneClient);
		tabbedPaneAdmin.addTab("Policies", new Panel());

		userController = new PaneUserController(paneUser);

		clientController = new PaneClientController(paneClient);

		userAttributeSetController = new PaneUserAttributeSetController(paneUser);

		clientAttributeSetController = new PaneClientAttributeSetController(paneClient);

		paneRegisterUserController = new PaneRegisterUserController(paneUser);

		paneRegisterClientController = new PaneRegisterClientController(paneClient);

		getContentPane().add(tabbedPaneAdmin);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					PaneMain frame = new PaneMain();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
