package admingui.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame {

	private JFrame mainFrame;

	private JTabbedPane tabbedPaneAdmin;

	private UserView userView;
	private ClientView clientView;
	private AcPoliciesPanel policiesPanel;
	private AttributesPanel attributesPanel;
	private ObjectEnviromentAttributesPanel attributesPanel1;
	private JsFunctionsPanel functionsPanel;

	public MainFrame() {
		initComponents();
	}

	public MainFrame(UserView p1, ClientView p2, AcPoliciesPanel p3, AttributesPanel p4,
			ObjectEnviromentAttributesPanel p5, JsFunctionsPanel p6) {
		userView = p1;
		clientView = p2;
		policiesPanel = p3;
		attributesPanel = p4;
		attributesPanel1 = p5;
		functionsPanel = p6;
		initComponents();
	}

	protected void initComponents() {
		initMainFrame();
	}

	protected void initMainFrame() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(400, 150, 470, 450);
		mainFrame.setTitle("AdminGUI");
		mainFrame.setVisible(false);
		mainFrame.setResizable(false);
		addTabs();
		mainFrame.add(tabbedPaneAdmin);
	}

	protected void addTabs() {
		tabbedPaneAdmin = new JTabbedPane();
		tabbedPaneAdmin.addTab("Users and Clients Attributes", attributesPanel.getMainPanel());
		tabbedPaneAdmin.addTab("Objects and Environment Attributes", attributesPanel1.getMainPanel());
		tabbedPaneAdmin.addTab("JavaScript Functions", functionsPanel.getMainPanel());
		tabbedPaneAdmin.addTab("Users", userView.getMainPanel());
		tabbedPaneAdmin.addTab("Clients", clientView.getMainPanel());
		tabbedPaneAdmin.addTab("Access Control Policies", policiesPanel.getMainPanel());
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public JTabbedPane getTabbedPaneAdmin() {
		return tabbedPaneAdmin;
	}

	public void setTabbedPaneAdmin(JTabbedPane tabbedPaneAdmin) {
		this.tabbedPaneAdmin = tabbedPaneAdmin;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	public ClientView getClientView() {
		return clientView;
	}

	public void setClientView(ClientView clientView) {
		this.clientView = clientView;
	}

	public AcPoliciesPanel getPoliciesPanel() {
		return policiesPanel;
	}

	public void setPoliciesPanel(AcPoliciesPanel policiesPanel) {
		this.policiesPanel = policiesPanel;
	}

	public AttributesPanel getAttributesPanel() {
		return attributesPanel;
	}

	public void setAttributesPanel(AttributesPanel attributesPanel) {
		this.attributesPanel = attributesPanel;
	}

	public ObjectEnviromentAttributesPanel getAttributesPanel1() {
		return attributesPanel1;
	}

	public void setAttributesPanel1(ObjectEnviromentAttributesPanel attributesPanel1) {
		this.attributesPanel1 = attributesPanel1;
	}
}