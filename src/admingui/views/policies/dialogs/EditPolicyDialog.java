package admingui.views.policies.dialogs;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class EditPolicyDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private JButton ok;
	private JButton cancel;

	public EditPolicyDialog() {
		initComponents();
	}

	protected void initComponents() {
		initButtons();
		initMainPanel();
		initMainDialog();
	}

	protected void initButtons() {
		ok = new JButton("Ok");
		cancel = new JButton("Cancel");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1"));
		mainPanel.add(createControlButtonsPanel(), "alignx right");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Edit an Access Control Policy");
		mainDialog.setBounds(500, 300, 270, 200);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected JPanel createControlButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 2"));
		panel.add(ok);
		panel.add(cancel);
		return panel;
	}

	public void show() {
		mainDialog.setVisible(true);
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