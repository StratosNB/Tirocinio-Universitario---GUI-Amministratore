package admingui.controller;

import java.awt.EventQueue;

import javax.swing.UIManager;

import admingui.view.AcPoliciesPanel;
import admingui.view.AttributesPanel;
import admingui.view.ClientView;
import admingui.view.JsFunctionsPanel;
import admingui.view.LoginFrame;
import admingui.view.MainFrame;
import admingui.view.ObjectEnviromentAttributesPanel;
import admingui.view.UserView;

public class MainController {

	private static LoginFrame loginFrame;
	private static MainFrame mainFrame;

	private UserView userView;
	private ClientView clientView;
	private AcPoliciesPanel policiesPanel;
	private AttributesPanel attributesPanel;
	private ObjectEnviromentAttributesPanel attributesPanel1;
	private JsFunctionsPanel functionsPanel;

	private LoginFrameController loginController;
	private UserViewController userViewController;
	private ClientViewController clientViewController;
	private AcPoliciesPanelController policiesPanelController;
	private UserClientAttributesPanelController attributesPanelController;
	private ObjectEnviromentAttributesPanelController attributesPanelController1;
	private JavaScriptFunctionsController jsFunctionsController;

	public MainController() {
		// initialize();
	}

	protected void initialize() {
		initPanels();
		initControllers();
		mainFrame = new MainFrame(userView, clientView, policiesPanel, attributesPanel, attributesPanel1,
				functionsPanel);
		loginController = new LoginFrameController(loginFrame, mainFrame);
	}

	protected void initPanels() {
		loginFrame = new LoginFrame();
		userView = new UserView();
		clientView = new ClientView();
		policiesPanel = new AcPoliciesPanel();
		attributesPanel = new AttributesPanel();
		attributesPanel1 = new ObjectEnviromentAttributesPanel();
		functionsPanel = new JsFunctionsPanel();
	}

	protected void initControllers() {
		userViewController = new UserViewController(userView, attributesPanel);
		clientViewController = new ClientViewController(clientView, attributesPanel);
		policiesPanelController = new AcPoliciesPanelController(policiesPanel);
		attributesPanelController = new UserClientAttributesPanelController(attributesPanel);
		attributesPanelController1 = new ObjectEnviromentAttributesPanelController(attributesPanel1);
		jsFunctionsController = new JavaScriptFunctionsController(functionsPanel);
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