package admingui.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import admingui.views.attribute.AttributesPanel;
import admingui.views.client.ClientView;
import admingui.views.policies.PoliciesPanel;
import admingui.views.user.UserView;

public class MainFrame {

	private JFrame frame;

	private JTabbedPane tabbedPaneAdmin;

	private UserView userView;
	private ClientView clientView;
	private AttributesPanel attributesPanel;
	private PoliciesPanel policiesPanel;

	public MainFrame(UserView p1, ClientView p2, AttributesPanel p3, PoliciesPanel p4) {
		this.userView = p1;
		this.clientView = p2;
		this.attributesPanel = p3;
		this.policiesPanel = p4;
		initComponents();
	}

	protected void initComponents() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(400, 150, 470, 520);
		frame.setTitle("AdminGUI");
		frame.setVisible(false);
		frame.setResizable(false);
		addTabs();
	}

	protected void addTabs() {
		tabbedPaneAdmin = new JTabbedPane();
		tabbedPaneAdmin.addTab("Users", userView.getMainPanel());
		tabbedPaneAdmin.addTab("Clients", clientView.getMainPanel());
		tabbedPaneAdmin.addTab("Attributes", attributesPanel.getMainPanel());
		tabbedPaneAdmin.addTab("Policies", policiesPanel.getMainPanel());

		frame.add(tabbedPaneAdmin);
	}

	public JFrame getFrame() {
		return this.frame;
	}
}
