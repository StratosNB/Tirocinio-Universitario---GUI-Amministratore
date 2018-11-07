package view.userPanes;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import view.attributePanes.AttributesView;

import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class PaneUser extends JPanel {

	private JButton searchButton = new JButton("Search");
	static JButton createButton = new JButton("New...");
	private JButton updateButton = new JButton("Update");
	private JButton deleteButton = new JButton("Delete");

	private JButton firstButton = new JButton("First");
	private JButton prevButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JButton lastButton = new JButton("Last");

	private JPanel viewUserPane;

	private UserView theUserView = new UserView();
	private AttributesView theAttribView = new AttributesView();

	private ClientAttributeSetManagement paneAttributeSet = new ClientAttributeSetManagement();
	private PaneRegisterUser paneRegisterUser = new PaneRegisterUser();

	public PaneUser() {

		setLayout(new CardLayout());
		initComponents();

	}

	protected void initComponents() {

		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		viewUserPane = new JPanel(layout);
		theUserView.setBorder(null);

		// put all the panels together
		viewUserPane.add(theUserView, "flowx,alignx left,growy");
		viewUserPane.add(getCrudButtonsPanel(), "cell 0 0");
		theAttribView.getAttribPanel().setBorder(null);
		viewUserPane.add(theAttribView, "cell 0 1");
		viewUserPane.add(getNavButtonsPanel(), "south");

		add(viewUserPane, "viewUserPane");
		add(paneAttributeSet, "Pane attributeSet");
		add(paneRegisterUser, "Pane registerUser");

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

	public UserView getUserView() {
		return this.theUserView;
	}

	public AttributesView getAttribView() {
		return this.theAttribView;
	}

	public ClientAttributeSetManagement getPaneAttributeSet() {
		return this.paneAttributeSet;
	}

	public PaneRegisterUser getPaneRegisterUser() {
		return this.paneRegisterUser;
	}

	public JButton getCreateButton() {
		return createButton;
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
}
