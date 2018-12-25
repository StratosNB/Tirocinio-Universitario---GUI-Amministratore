package admingui.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import admingui.model.Attribute;
import admingui.model.Client;
import admingui.view.dialogs.AddClientDialog;
import admingui.view.dialogs.EditClientDialog;
import admingui.view.dialogs.SearchClientDialog;
import net.miginfocom.swing.MigLayout;

public class ClientView {

	private Client client;
	@SuppressWarnings("unused")
	private List<Attribute> attributes;

	private JPanel mainPanel;
	private AdditionalAttributesPanel attributesPanel;
	private NavigationButtonsPanel navPanel;
	private JLabel id;
	private JLabel name;
	private JLabel ownerId;
	private JLabel clientId;
	private JLabel clientName;
	private JLabel clientUserId;
	private JButton search;
	private JButton edit;
	private AddClientDialog addClient;
	private EditClientDialog editClient;
	private SearchClientDialog searchClient;

	public ClientView() {
		initComponents();
	}

	protected void initComponents() {
		initPanels();
		initButtons();
		initLabels();
		initDialogs();
		initMainPanel();
	}

	protected void initPanels() {
		attributesPanel = new AdditionalAttributesPanel();
		navPanel = new NavigationButtonsPanel();
	}

	protected void initButtons() {
		search = new JButton("Search...");
		edit = new JButton("Edit...");
	}

	protected void initLabels() {
		id = new JLabel("ID:");
		ownerId = new JLabel("Owner ID:");
		name = new JLabel("Name:");
		setBold();

		clientId = new JLabel();
		clientUserId = new JLabel();
		clientName = new JLabel();
		// setTexts();
	}

	protected void initDialogs() {
		addClient = new AddClientDialog();
		editClient = new EditClientDialog();
		searchClient = new SearchClientDialog();
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[][]", "[][]push[]"));
		mainPanel.add(createFixedAttributePanel());
		mainPanel.add(createButtonsPanel(), "wrap,top");
		mainPanel.add(attributesPanel.getScrollPane(), "span");
		mainPanel.add(navPanel.getMainPanel(), "dock south");
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	protected JPanel createFixedAttributePanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 4", "[][]15[][]", "[][][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Fixed Attributes"));
		panel.add(id, "right");
		panel.add(clientId);
		panel.add(ownerId, "right");
		panel.add(clientUserId);
		panel.add(name, "right");
		panel.add(clientName);
		return panel;
	}

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 1"));
		panel.add(search);
		panel.add(edit);
		return panel;
	}

	protected void setBold() {
		id.setFont(new Font("Tahoma", Font.BOLD, 11));
		name.setFont(new Font("Tahoma", Font.BOLD, 11));
		ownerId.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	public Client getFieldData() {
		Client client = new Client();
		client.setId(Integer.valueOf(clientId.getText()));
		client.setName(clientName.getText());
		client.setUserID(Integer.valueOf(clientUserId.getText()));
		return client;
	}

	public void setFixedAttributesData(Client client) {
		clientId.setText(String.valueOf(client.getId()));
		clientName.setText(client.getName());
		clientUserId.setText(String.valueOf(client.getUserId()));
	}

	public void addButtonsListener(ActionListener e) {
		search.addActionListener(e);
		edit.addActionListener(e);
		navPanel.getDeleteButton().addActionListener(e);
		navPanel.getAddButton().addActionListener(e);
	}

	public void addNavigationListener(ActionListener e) {
		navPanel.getFirstButton().addActionListener(e);
		navPanel.getPreviosuButton().addActionListener(e);
		navPanel.getNextButton().addActionListener(e);
		navPanel.getLastButton().addActionListener(e);
	}

	public void addCancelListener(ActionListener e) {
		addClient.addListener(e);
		editClient.addListener(e);
		searchClient.addListener(e);
	}

	public void addAddClientOkListener(ActionListener e) {
		addClient.getOk().addActionListener(e);
	}

	public void addEditClientOkListener(ActionListener e) {
		editClient.getOk().addActionListener(e);
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public AdditionalAttributesPanel getAdditionalAttributesPanel() {
		return attributesPanel;
	}

	public int getClientID() {
		return Integer.valueOf(clientId.getText());
	}

	public AddClientDialog getAddClient() {
		return addClient;
	}

	public EditClientDialog getEditClient() {
		return editClient;
	}

	public SearchClientDialog getSearchClient() {
		return searchClient;
	}

	public void show() {
		this.mainPanel.setVisible(true);
	}
}