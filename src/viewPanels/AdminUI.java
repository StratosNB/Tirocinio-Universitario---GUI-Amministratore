package viewPanels;

import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controllerViews.AttributeController;
import controllerViews.ClientController;
/*import controllerViews.TabController;*/
import controllerViews.UserController;
import views.AttributesView;
import views.ClientView;
import views.UserView;

public class AdminUI extends JFrame {

	private JTabbedPane tabbedPaneAdmin;
	static JTabbedPane tabbedPaneUsers;
	static JTabbedPane tabbedPaneClients;

	private static UserController userController;
	private static ClientController clientController;
	private static AttributeController attributeController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				/*	frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							
							closeConnections();
							
						}
					});*/

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public AdminUI() throws SQLException {

		PaneUser paneUser = new PaneUser(new UserView(), new AttributesView());
		PaneClient paneClient = new PaneClient(new ClientView(), new AttributesView());
		PaneAttributes paneAttributes = new PaneAttributes();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 500);
		setTitle("Management");

		tabbedPaneAdmin = new JTabbedPane();

		tabbedPaneAdmin.addTab("Users", paneUser);
		tabbedPaneAdmin.addTab("Clients", paneClient);
		tabbedPaneAdmin.addTab("Attributes", paneAttributes);
		tabbedPaneAdmin.addTab("Policies", new Panel());

		/*TabController tabController = new TabController(tabbedPaneAdmin);*/

		userController = new UserController(paneUser);
		userController.initTextBoxs();

		clientController = new ClientController(paneClient);
		clientController.initTextBoxs();

		attributeController = new AttributeController(paneAttributes, paneUser, paneClient);
		attributeController.initComponents();

		getContentPane().add(tabbedPaneAdmin);

	}

	public static void closeConnections() {
		userController.closeConnections();
		clientController.closeConnections();
		attributeController.closeConnection();
	}
}
