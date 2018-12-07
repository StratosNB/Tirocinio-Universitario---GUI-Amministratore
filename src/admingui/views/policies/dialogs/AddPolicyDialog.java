package admingui.views.policies.dialogs;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import admingui.utils.AdminGuiStrings;
import admingui.views.policies.PolicyForm;
import net.miginfocom.swing.MigLayout;

public class AddPolicyDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private JLabel entity;
	private JComboBox entities;

	private PolicyForm policyForm;

	private JButton ok;
	private JButton cancel;

	public AddPolicyDialog() {
		initComponents();
	}

	protected void initComponents() {
		entity = new JLabel("For:");
		entities = new JComboBox(AdminGuiStrings.entities);
		initButtons();
		initMainPanel();
	}

	protected void initButtons() {
		ok = new JButton("Ok");
		cancel = new JButton("Cancel");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1"));
		mainPanel.add(createEntitySelectionPanel());
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Edit an User");
		mainDialog.setBounds(500, 200, 270, 430);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.add(mainPanel);
	}

	protected JPanel createEntitySelectionPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 2"));
		panel.add(entity);
		panel.add(entities);
		return panel;
	}

	public JDialog getMainDialog() {
		return mainDialog;
	}

	public JButton getOk() {
		return ok;
	}

	public JButton getCancel() {
		return cancel;
	}
}
