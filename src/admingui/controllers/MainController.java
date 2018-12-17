package admingui.controllers;

import java.awt.EventQueue;

import javax.swing.UIManager;

import admingui.controllers.attributes.ObjectEnviromentAttributesPanelController;
import admingui.controllers.attributes.UserClientAttributesPanelController;
import admingui.controllers.clients.ClientViewController;
import admingui.controllers.login.LoginFrameController;
import admingui.controllers.policies.AcPoliciesPanelController;
import admingui.controllers.users.UserViewController;
import admingui.views.MainFrame;
import admingui.views.attributes.client_user.AttributesPanel;
import admingui.views.attributes.object_enviroment.ObjectEnviromentAttributesPanel;
import admingui.views.client.ClientView;
import admingui.views.login.LoginFrame;
import admingui.views.policies.AcPoliciesPanel;
import admingui.views.user.UserView;

public class MainController {

	private static LoginFrame loginFrame;
	private static MainFrame mainFrame;

	private UserView userView;
	private ClientView clientView;
	private AcPoliciesPanel policiesPanel;
	private AttributesPanel attributesPanel;
	private ObjectEnviromentAttributesPanel attributesPanel1;

	private LoginFrameController loginController;
	private UserViewController userViewController;
	private ClientViewController clientViewController;
	private AcPoliciesPanelController policiesPanelController;
	private UserClientAttributesPanelController attributesPanelController;
	private ObjectEnviromentAttributesPanelController attributesPanelController1;

	public MainController() {
		// initialize();
	}

	protected void initialize() {
		initPanels();
		initControllers();
		mainFrame = new MainFrame(userView, clientView, policiesPanel, attributesPanel, attributesPanel1);
		loginController = new LoginFrameController(loginFrame, mainFrame);
	}

	protected void initPanels() {
		loginFrame = new LoginFrame();
		userView = new UserView();
		clientView = new ClientView();
		policiesPanel = new AcPoliciesPanel();
		attributesPanel = new AttributesPanel();
		attributesPanel1 = new ObjectEnviromentAttributesPanel();
	}

	protected void initControllers() {
		userViewController = new UserViewController(userView, attributesPanel);
		clientViewController = new ClientViewController(clientView, attributesPanel);
		policiesPanelController = new AcPoliciesPanelController(policiesPanel);
		attributesPanelController = new UserClientAttributesPanelController(attributesPanel);
		attributesPanelController1 = new ObjectEnviromentAttributesPanelController(attributesPanel1);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainController mainController = new MainController();
					mainController.initialize();
					loginFrame.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}