package admingui.view.dialogs;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import admingui.model.Client;
import admingui.model.User;
import admingui.utils.RowFilterUtil;
import net.miginfocom.swing.MigLayout;

public class SearchPolicyDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;
	private JPanel resultsTablePanel;

	private JLabel filterLabel;
	private JComboBox<String> userIdsCbox;
	private JComboBox<String> clientIdsCbox;
	private JTextField filterInputTextField;

	private JScrollPane tableSp;
	private JTable policiesResultTable;
	private TableRowSorter<TableModel> sorter;

	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private ButtonGroup buttonGroup;
	private JButton searchPolicyButton;
	private JButton closeButton;

	public SearchPolicyDialog() {
		initComponents();
	}

	protected void initComponents() {
		initButtons();
		initLabels();
		initComboBoxs();
		initTable();
		initMainPanel();
		initMainDialog();
	}

	protected void initLabels() {
		filterLabel = new JLabel("Filter by Topic Filter Expression:");
	}

	protected void initButtons() {
		searchPolicyButton = new JButton("Search");
		closeButton = new JButton("Close");
		radioButton1 = new JRadioButton("Users");
		radioButton2 = new JRadioButton("Clients");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		setActionCommands();
	}

	protected void setActionCommands() {
		searchPolicyButton.setActionCommand("SEARCH POLICY");
		closeButton.setActionCommand("CLOSE DIALOG");
		radioButton1.setActionCommand("RADIO BUTTON 1 SELECTED");
		radioButton2.setActionCommand("RADIO BUTTON 2 SELECTED");
	}

	protected void initComboBoxs() {
		userIdsCbox = new JComboBox<String>();
		clientIdsCbox = new JComboBox<String>();
		userIdsCbox.setEnabled(false);
		clientIdsCbox.setEnabled(false);
	}

	protected void initTable() {
		policiesResultTable = new JTable();
		tableSp = new JScrollPane(policiesResultTable);
		filterInputTextField = new JTextField();
		policiesResultTable.setFillsViewportHeight(true);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("fill", "[][]", "[][]"));
		mainPanel.add(createIdSelectionPanel(), "cell 0 0,growx,aligny top");
		mainPanel.add(createResultsTablePanel(), "cell 1 0,grow");
		mainPanel.add(closeButton, "cell 1 1,right");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Search Access Control Policy");
		mainDialog.setBounds(400, 150, 800, 400);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected JPanel createIdSelectionPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]", "[][]30[]"));
		panel.setBorder(BorderFactory.createTitledBorder("Select Id:"));
		panel.add(radioButton1, "cell 0 0");
		panel.add(userIdsCbox, "cell 1 0");
		panel.add(radioButton2, "cell 0 1");
		panel.add(clientIdsCbox, "cell 1 1");
		panel.add(searchPolicyButton, "cell 1 2");
		return panel;
	}

	protected JPanel createResultsTablePanel() {
		resultsTablePanel = new JPanel(new MigLayout("fill", "[]", "[][]"));
		resultsTablePanel.setBorder(BorderFactory.createTitledBorder("Results"));
		resultsTablePanel.add(filterLabel, "cell 0 0");
		resultsTablePanel.add(filterInputTextField, "cell 0 0,growx");
		resultsTablePanel.add(tableSp, "cell 0 1,grow");
		return resultsTablePanel;
	}

	public void initFilterTextField() {
		resultsTablePanel.remove(filterInputTextField);
		filterInputTextField = RowFilterUtil.createRowFilter(policiesResultTable);
		resultsTablePanel.add(filterInputTextField, "cell 0 0,growx");
		resultsTablePanel.repaint();
		resultsTablePanel.revalidate();
	}

	public void setComboBoxItems(List<User> users, List<Client> clients) {
		userIdsCbox.addItem("");
		clientIdsCbox.addItem("");
		users.forEach(user -> userIdsCbox.addItem(String.valueOf(user.getId())));
		clients.forEach(client -> clientIdsCbox.addItem(String.valueOf(client.getId())));
	}

	public boolean isIdSelected() {
		if (radioButton1.isSelected() && !userIdsCbox.getSelectedItem().equals("")) {
			return true;
		}
		if (radioButton2.isSelected() && !clientIdsCbox.getSelectedItem().equals("")) {
			return true;
		}
		return false;
	}

	public String getSelectedRadioButtonText() {
		if (radioButton1.isSelected()) {
			return radioButton1.getText();
		}
		return radioButton2.getText();
	}

	public int getSelectedId() {
		if (userIdsCbox.isEnabled()) {
			return Integer.valueOf(userIdsCbox.getSelectedItem().toString());
		}
		return Integer.valueOf(clientIdsCbox.getSelectedItem().toString());
	}

	public void cleanComboBoxs() {
		userIdsCbox.removeAllItems();
		clientIdsCbox.removeAllItems();
	}

	public void newFilter() {
		RowFilter<TableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			rf = RowFilter.regexFilter(filterInputTextField.getText(), 0);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}

	public void addButtonsListener(ActionListener e) {
		radioButton1.addActionListener(e);
		radioButton2.addActionListener(e);
		searchPolicyButton.addActionListener(e);
		closeButton.addActionListener(e);
	}

	public void show() {
		this.mainDialog.setVisible(true);
	}

	public JDialog getMainDialog() {
		return this.mainDialog;
	}

	public JRadioButton getRadioButton1() {
		return radioButton1;
	}

	public JRadioButton getRadioButton2() {
		return radioButton2;
	}

	public JComboBox<String> getUserIdsCbox() {
		return userIdsCbox;
	}

	public void setUserIdsCbox(JComboBox<String> userIdsCbox) {
		this.userIdsCbox = userIdsCbox;
	}

	public JComboBox<String> getClientIdsCbox() {
		return clientIdsCbox;
	}

	public void setClientIdsCbox(JComboBox<String> clientIdsCbox) {
		this.clientIdsCbox = clientIdsCbox;
	}

	public JTextField getFilterInputTextField() {
		return filterInputTextField;
	}

	public void setFilterInputTextField(JTextField filterInputTextField) {
		this.filterInputTextField = filterInputTextField;
	}

	public JTable getPoliciesResultTable() {
		return policiesResultTable;
	}

	public void setPoliciesResultTable(JTable policiesResultTable) {
		this.policiesResultTable = policiesResultTable;
	}

	public JButton getSearchPolicyButton() {
		return searchPolicyButton;
	}
}