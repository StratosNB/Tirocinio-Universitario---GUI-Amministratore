package admingui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import admingui.model.AccessControlPolicy;
import admingui.view.dialogs.AddPolicyDialog;
import admingui.view.dialogs.EditPolicyDialog;
import admingui.view.dialogs.SearchPolicyDialog;
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
	private SearchPolicyDialog searchPolicyDialog;

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
		searchPolicyDialog = new SearchPolicyDialog();
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
		mainPanel = new JPanel(new MigLayout("fill", "[][]", "[][]push[]"));
		mainPanel.add(createPoliciesPanel(), "cell 0 0");
		mainPanel.add(createButtonsPanel(), "cell 1 0");
		mainPanel.add(createBorderedJPanel(), "cell 0 1 3 2,grow");
		mainPanel.add(navPanel.getMainPanel(), "cell 1 2, south");
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
		panel.add(exportPoliciesToRedis);
		panel.add(searchPolicy);
		panel.add(editPolicy);
		return panel;
	}

	protected JPanel createBorderedJPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
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
		searchPolicy.addActionListener(e);
		navPanel.getAddButton().addActionListener(e);
		navPanel.getDeleteButton().addActionListener(e);
	}

	public void addNavigationButtonsListener(ActionListener e) {
		navPanel.getFirstButton().addActionListener(e);
		navPanel.getPreviosuButton().addActionListener(e);
		navPanel.getNextButton().addActionListener(e);
		navPanel.getLastButton().addActionListener(e);
	}

	protected void setActionCommands() {
		navPanel.getAddButton().setActionCommand("OPEN ADD POLICY DIALOG");
		navPanel.getDeleteButton().setActionCommand("DELETE POLICY");
		searchPolicy.setActionCommand("OPEN SEARCH POLICY DIALOG");
		editPolicy.setActionCommand("OPEN EDIT POLICY DIALOG");
		navPanel.getDeleteButton().setActionCommand("DELETE POLICY");
		exportPoliciesToRedis.setActionCommand("EXPORT POLICIES TO REDIS");
	}

	public void setAcPolicyData(AccessControlPolicy policy) {
		policyId.setText(String.valueOf(policy.getId()));
		policyTopicFilterExpression.setText(policy.getTopicFilterExpression().trim());
		policyParametricPredicate.setText(policy.getParemetricPredicate().trim());
		policyPermission.setText(policy.getPermission().trim());
	}

	public int getId() {
		return Integer.valueOf(policyId.getText());
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

	public SearchPolicyDialog getSearchPolicyDialog() {
		return searchPolicyDialog;
	}
}