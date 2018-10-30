package viewPanels;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import views.AttributesView;
import views.ClientView;

public class PaneClient extends JPanel {

	private JButton searchButton = new JButton("Search");
	static JButton createButton = new JButton("New...");
	private JButton updateButton = new JButton("Update");
	private JButton deleteButton = new JButton("Delete");

	private JButton firstButton = new JButton("First");
	private JButton prevButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JButton lastButton = new JButton("Last");

	ClientView clientView;
	AttributesView theAttribView;

	public PaneClient(ClientView view, AttributesView attrView) {

		this.clientView = view;
		this.theAttribView = attrView;
		initComponents();

	}

	protected void initComponents() {

		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		setLayout(layout);

		// put all the panels together
		add(clientView, "flowx,alignx left,growy");
		add(getCrudButtonsPanel(), "cell 0 0");
		add(theAttribView, "cell 0 1");
		add(getNavButtonsPanel(), "south");

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
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

	public AttributesView getAttribView() {
		// TODO Auto-generated method stub
		return this.theAttribView;
	}

}
