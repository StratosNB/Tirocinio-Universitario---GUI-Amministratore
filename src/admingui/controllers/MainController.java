package admingui.controllers;

import java.awt.EventQueue;

import javax.swing.UIManager;

import admingui.controllers.attributes.AttributesPanelController;
import admingui.controllers.clients.ClientViewController;
import admingui.controllers.login.LoginFrameController;
import admingui.controllers.users.UserViewController;
import admingui.views.MainFrame;
import admingui.views.attribute.AttributesPanel;
import admingui.views.client.ClientView;
import admingui.views.login.FrameLogin;
import admingui.views.policies.PoliciesPanel;
import admingui.views.user.UserView;

public class MainController {

	private static FrameLogin loginFrame;

	private static UserView userView;
	private static ClientView clientView;
	private static AttributesPanel attributesPanel;

	private static MainFrame mainFrame;

	private static LoginFrameController loginController;

	private static UserViewController userViewController;
	private static ClientViewController clientViewController;
	private static AttributesPanelController attributesPanelController;
	private static PoliciesPanel policiesPanel;

	public MainController() {
		initialize();
	}

	protected static void initialize() {
		initComponents();
		initControllers();
	}

	protected static void initComponents() {
		loginFrame = new FrameLogin();
		userView = new UserView();
		clientView = new ClientView();
		attributesPanel = new AttributesPanel();
		policiesPanel = new PoliciesPanel();
		mainFrame = new MainFrame(userView, clientView, attributesPanel, policiesPanel);
	}

	protected static void initControllers() {
		loginController = new LoginFrameController(loginFrame, mainFrame);
		userViewController = new UserViewController(userView, attributesPanel);
		clientViewController = new ClientViewController(clientView, attributesPanel);
		attributesPanelController = new AttributesPanelController(attributesPanel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainController mainController = new MainController();
					loginController.showLoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
