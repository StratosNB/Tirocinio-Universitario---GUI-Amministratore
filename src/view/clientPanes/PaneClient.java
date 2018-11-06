package view.clientPanes;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import net.miginfocom.swing.MigLayout;
import view.attributePanes.AttributesView;
import view.userPanes.ClientAttributeSetManagement;

@SuppressWarnings("serial")
public class PaneClient extends JPanel {

	private JButton searchButton = new JButton("Search");
	static JButton createButton = new JButton("New...");
	private JButton updateButton = new JButton("Update");
	private JButton deleteButton = new JButton("Delete");

	private JButton firstButton = new JButton("First");
	private JButton prevButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JButton lastButton = new JButton("Last");

	private JPanel viewClientPane;

	private ClientView clientView = new ClientView();
	private AttributesView theAttribView = new AttributesView();

	private ClientAttributeSetManagement paneAttributeSet = new ClientAttributeSetManagement();
	public PaneRegisterClient paneRegisterClient = new PaneRegisterClient();

	public PaneClient() {

		setLayout(new CardLayout());
		initComponents();

	}

	protected void initComponents() {

		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		viewClientPane = new JPanel(layout);

		// put all the panels together
		viewClientPane.add(clientView, "flowx,alignx left,growy");
		viewClientPane.add(getCrudButtonsPanel(), "cell 0 0");
		viewClientPane.add(theAttribView, "cell 0 1");
		viewClientPane.add(getNavButtonsPanel(), "south");

		add(viewClientPane, "viewClientPane");
		add(paneAttributeSet, "pane Aset");
		add(paneRegisterClient, "pane Register client");

	}

	private JPanel getCrudButtonsPanel() {

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setLayout(new MigLayout("wrap 1", "[]"));

		panel.add(searchButton);
		panel.add(createButton);
		panel.add(updateButton);
		panel.add(deleteButton);

		return panel;
	}

	private JPanel getNavButtonsPanel() {

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setLayout(new MigLayout("center", "[] [] [] []"));

		panel.add(firstButton);
		panel.add(prevButton);
		panel.add(nextButton);
		panel.add(lastButton);

		return panel;
	}

	public void addCrudListener(ActionListener crudListener) {
		// TODO Auto-generated method stub
		searchButton.addActionListener(crudListener);
		createButton.addActionListener(crudListener);
		updateButton.addActionListener(crudListener);
		deleteButton.addActionListener(crudListener);

	}

	public void addNavListener(ActionListener navListener) {
		// TODO Auto-generated method stub
		firstButton.addActionListener(navListener);
		prevButton.addActionListener(navListener);
		nextButton.addActionListener(navListener);
		lastButton.addActionListener(navListener);

	}

	public ClientView getClientView() {
		return clientView;
	}

	public JButton getCreateButton() {
		return createButton;
	}

	public ClientAttributeSetManagement getPaneAttributeSet() {
		return this.paneAttributeSet;
	}

	public PaneRegisterClient getPaneRegisterClient() {
		return paneRegisterClient;
	}

	public AttributesView getAttribView() {
		// TODO Auto-generated method stub
		return this.theAttribView;
	}

}
