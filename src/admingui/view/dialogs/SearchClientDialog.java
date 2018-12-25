package admingui.view.dialogs;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import admingui.view.ClientForm;
import net.miginfocom.swing.MigLayout;

public class SearchClientDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;
	private ClientForm clientForm;
	private JRadioButton and;
	private JRadioButton or;
	private ButtonGroup buttonGroup;
	private JButton search;
	private JButton cancel;
	private JButton edit;
	private JButton delete;
	private JScrollPane tableScrollPane;
	private JTable resultsTable;

	public SearchClientDialog() {
		initComponents();
	}

	protected void initComponents() {
		clientForm = new ClientForm("Client Data");
		initTable();
		initButtons();
		initMainPanel();
		initMainDialog();
	}

	protected void initTable() {
		resultsTable = new JTable();
		tableScrollPane = new JScrollPane(resultsTable);
		tableScrollPane.setBorder(null);
	}

	protected void initButtons() {
		initRadioButtons();
		search = new JButton("Search");
		cancel = new JButton("Close");
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
		mainDialog.setTitle("Search a Client");
		mainDialog.setBounds(400, 150, 700, 500);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[][]", "[][][]"));
		mainPanel.add(clientForm.getMainPanel(), "grow");
		mainPanel.add(createResultsPanel(), "cell 1 0");
		mainPanel.add(createSearchButtonsPanel(), "cell 0 1,alignx right");
		mainPanel.add(createQuitPanel(), "cell 1 1,alignx right");
	}

	protected JPanel createResultsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[]", "[][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Results"));
		panel.add(tableScrollPane, "cell 0 0,grow,wrap");
		panel.add(createResultsButtonsPanel(), "cell 0 1,alignx right");
		return panel;
	}

	protected JPanel createSearchButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout(""));
		panel.add(and);
		panel.add(or);
		panel.add(search);
		return panel;
	}

	protected JPanel createResultsButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]"));
		panel.add(edit);
		panel.add(delete);
		return panel;
	}

	protected JPanel createQuitPanel() {
		JPanel panel = new JPanel(new MigLayout(""));
		panel.add(cancel);
		return panel;
	}

	public void addListener(ActionListener a) {
		cancel.addActionListener(a);
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

	public ClientForm getClientForm() {
		return this.clientForm;
	}

	public void show() {
		mainDialog.setVisible(true);
	}

	public void dispose() {
		mainDialog.dispose();
	}
}