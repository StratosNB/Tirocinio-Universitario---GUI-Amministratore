package admingui.views.policies;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import admingui.views.NavigationButtonsPanel;
import admingui.views.policies.dialogs.AddPolicyDialog;
import admingui.views.policies.dialogs.EditPolicyDialog;
import net.miginfocom.swing.MigLayout;

public class AcPoliciesPanel {

	private JPanel mainPanel;

	private JLabel id;
	private JLabel topicFilterExpression;
	private JLabel parametricPredicate;
	private JLabel permission;

	private JLabel policyId;
	private JLabel policyTopicFilterExpression;
	private JLabel policyParametricPredicate;
	private JLabel policyPermission;

	private JButton exportPoliciesToRedis;
	private JButton searchPolicy;
	private JButton editPolicy;

	private NavigationButtonsPanel navPanel;

	private AddPolicyDialog addPolicyDialog;
	private EditPolicyDialog editPolicyDialog;

	public AcPoliciesPanel() {
		initComponents();
	}

	protected void initComponents() {
		navPanel = new NavigationButtonsPanel();
		initDialogs();
		initLabels();
		initButtons();
		initMainPanel();
	}

	protected void initDialogs() {
		addPolicyDialog = new AddPolicyDialog();
		editPolicyDialog = new EditPolicyDialog();
	}

	protected void initLabels() {
		id = new JLabel("ID:");
		topicFilterExpression = new JLabel("Topic Filter Expression:");
		parametricPredicate = new JLabel("Parametric Predicate:");
		permission = new JLabel("Permission:");
		setBold();

		policyId = new JLabel();
		policyTopicFilterExpression = new JLabel();
		policyParametricPredicate = new JLabel();
		policyPermission = new JLabel();
	}

	protected void initButtons() {
		exportPoliciesToRedis = new JButton("Export");
		searchPolicy = new JButton("Search...");
		editPolicy = new JButton("Edit...");
		setActionCommands();
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[][]", "[][]push[]"));
		mainPanel.add(createPoliciesPanel());
		mainPanel.add(createButtonsPanel());
		mainPanel.add(exportPoliciesToRedis, "alignx right");
		mainPanel.add(navPanel.getMainPanel(), "south");
	}

	protected JPanel createPoliciesPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 2"));
		panel.setBorder(BorderFactory.createTitledBorder("Data"));
		panel.add(id, "right");
		panel.add(policyId);
		panel.add(topicFilterExpression, "right");
		panel.add(policyTopicFilterExpression);
		panel.add(parametricPredicate, "right");
		panel.add(policyParametricPredicate);
		panel.add(permission, "right");
		panel.add(policyPermission);
		return panel;
	}

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 1"));
		panel.add(searchPolicy);
		panel.add(editPolicy);
		return panel;
	}

	protected void setBold() {
		id.setFont(new Font("Tahoma", Font.BOLD, 11));
		topicFilterExpression.setFont(new Font("Tahoma", Font.BOLD, 11));
		parametricPredicate.setFont(new Font("Tahoma", Font.BOLD, 11));
		permission.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	public void addPoliciesPanelButtonsListener(ActionListener e) {
		exportPoliciesToRedis.addActionListener(e);
		editPolicy.addActionListener(e);
		navPanel.getAddButton().addActionListener(e);
		navPanel.getDeleteButton().addActionListener(e);
	}

	public void addNavListener(ActionListener e) {
		navPanel.getFirstButton().addActionListener(e);
		navPanel.getPreviosuButton().addActionListener(e);
		navPanel.getNextButton().addActionListener(e);
		navPanel.getLastButton().addActionListener(e);
	}

	protected void setActionCommands() {
		navPanel.getAddButton().setActionCommand("OPEN ADD POLICY DIALOG");
		searchPolicy.setActionCommand("OPEN SEARCH POLICY DIALOG");
		editPolicy.setActionCommand("OPEN EDIT POLICY DIALOG");
		navPanel.getDeleteButton().setActionCommand("DELETE POLICY");
		exportPoliciesToRedis.setActionCommand("EXPORT POLICIES TO REDIS");
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public AddPolicyDialog getAddPolicyDialog() {
		return addPolicyDialog;
	}

	public EditPolicyDialog getEditPolicyDialog() {
		return editPolicyDialog;
	}
}