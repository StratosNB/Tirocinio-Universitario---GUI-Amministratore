package admingui.views.user.dialogs;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import admingui.models.User;
import admingui.views.user.UserForm;
import net.miginfocom.swing.MigLayout;

public class SearchUserDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private UserForm userForm;

	private JRadioButton and;
	private JRadioButton or;
	private ButtonGroup buttonGroup;

	private JButton search;
	private JButton all;
	private JButton cancel;
	private JButton edit;
	private JButton delete;

	private JScrollPane tableScrollPane;
	private JTable resultsTable;

	public SearchUserDialog() {
		initComponents();
	}

	protected void initComponents() {
		userForm = new UserForm("User Data");
		initTable();
		initButtons();
		initMainPanel();
		initMainDialog();
		setActionCommands();
	}

	protected void initTable() {
		resultsTable = new JTable();
		tableScrollPane = new JScrollPane(resultsTable);
		tableScrollPane.setBorder(null);
	}

	protected void initButtons() {
		initRadioButtons();
		search = new JButton("Search");
		all = new JButton("All");
		cancel = new JButton("Cancel");
		edit = new JButton("Edit...");
		delete = new JButton("Delete");
	}

	protected void initRadioButtons() {
		and = new JRadioButton("AND");
		or = new JRadioButton("OR");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(and);
		buttonGroup.add(or);
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Search an User");
		mainDialog.setBounds(400, 150, 830, 500);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[][]", "[][][]"));
		mainPanel.add(userForm.getMainPanel(), "grow");
		mainPanel.add(createResultsPanel(), "cell 1 0");
		mainPanel.add(createSearchButtonsPanel(), "cell 0 1,growx");
		mainPanel.add(createCancelButtonPanel(), "cell 1 1 2097051 1,alignx right,growy");
	}

	protected JPanel createResultsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[]", "[][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Results"));
		panel.add(tableScrollPane, "cell 0 0, wrap");
		panel.add(createCrudButtonsPanel(), "cell 0 1,alignx right");
		return panel;
	}

	protected JPanel createSearchButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[]push[][][]"));
		panel.add(all);
		panel.add(and);
		panel.add(or);
		panel.add(search);
		return panel;
	}

	protected JPanel createCrudButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]"));
		panel.add(edit);
		panel.add(delete);
		return panel;
	}

	protected JPanel createCancelButtonPanel() {
		JPanel panel = new JPanel(new MigLayout(""));
		panel.add(cancel, "cell 2 0,alignx right");
		return panel;
	}

	public void show() {
		mainDialog.setVisible(true);
	}

	public void dispose() {
		mainDialog.dispose();
	}

	public User createUser() {
		return userForm.createUser();
	}

	protected void setActionCommands() {
		all.setActionCommand("Show all users");
		search.setActionCommand("Search users");
		edit.setActionCommand("Edit user in search dialog");
		delete.setActionCommand("Delete user in search dialog");
		cancel.setActionCommand("Cancel search");
	}

	public JDialog getDialog() {
		return this.mainDialog;
	}

	public JPanel getSearchPanel() {
		return this.getSearchPanel();
	}

	public JTable getResultsTable() {
		return this.resultsTable;
	}

	public UserForm getUserForm() {
		return this.userForm;
	}

	public JButton getAll() {
		return all;
	}

	public JRadioButton getAnd() {
		return and;
	}

	public JRadioButton getOr() {
		return or;
	}

	public JButton getSearch() {
		return search;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JButton getEdit() {
		return edit;
	}

	public JButton getDelete() {
		return delete;
	}
}