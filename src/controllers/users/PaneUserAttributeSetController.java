package controllers.users;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import view.attributePanes.AttributesView;
import view.userPanes.PaneUser;
import view.userPanes.ClientAttributeSetManagement;

public class PaneUserAttributeSetController {

	private AttributesView userAttribView;
	private AttributesView clientAttribView;

	private PaneUser paneUser;
	private ClientAttributeSetManagement paneUserAttributeSet;

	public PaneUserAttributeSetController(PaneUser pUser) throws SQLException {

		this.paneUser = pUser;

		this.paneUserAttributeSet = pUser.getPaneAttributeSet();

		this.userAttribView = pUser.getAttribView();

		paneUserAttributeSet.addCRUDListener(new CRUDListener());
		paneUserAttributeSet.addNavListener(new NavListener());

		initComponents();

	}

	class NavListener implements ActionListener {

		CardLayout cardLayout = (CardLayout) paneUser.getLayout();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Back":

				cardLayout.show(paneUser, "viewUserPane");
				break;

			case "Next":

				cardLayout.show(paneUser, "Pane registerUser");
				break;
			}
		}
	}

	class CRUDListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Add":

			case "Update":

			case "Remove":
			}
		}
	}

	public void initComponents() throws SQLException {

		/*
		 * int numOfUserAttributes = beanUsers.getRowCount();
		 * 
		 * ArrayList<JLabel> userAttribLbls =
		 * createUserLabels(numOfUserAttributes);
		 */

		/*
		 * ArrayList<JTextField> userAttribTxts =
		 * createTextFields(numOfUserAttributes);
		 * 
		 * JPanel userAttribpane = this.userAttribView.getAttribPanel();
		 * 
		 * for (int i = 0; i < numOfUserAttributes; i++) {
		 * 
		 * userAttribpane.add(userAttribLbls.get(i), "right");
		 * userAttribpane.add(userAttribTxts.get(i));
		 * 
		 * }
		 */
		/*
		 * for (int i = 0; i < numOfUserAttributes; i++) {
		 * 
		 * registerUserPane.add(userAttribLbls.get(i), "right");
		 * registerUserPane.add(userAttribTxts.get(i));
		 * 
		 * }
		 */

	}

	/*
	 * public ArrayList<JLabel> createUserLabels(int numOfAttributes) {
	 * 
	 * ArrayList<JLabel> labels = new ArrayList<JLabel>();
	 * 
	 * if (!(numOfAttributes == 0)) { ArrayList<Attribute> attributes =
	 * beanUsers.getAllFromUsers();
	 * 
	 * for (int i = 0; i < numOfAttributes; i++) { labels.add(new
	 * JLabel(attributes.get(i).getAttributeName().trim())); }
	 * 
	 * } return labels; }
	 * 
	 * public ArrayList<JTextField> createTextFields(int numOfAttributes) {
	 * 
	 * ArrayList<JTextField> txtFs = new ArrayList<JTextField>();
	 * 
	 * if (!(numOfAttributes == 0)) { for (int i = 0; i < numOfAttributes; i++)
	 * { txtFs.add(new JTextField(15)); } }
	 * 
	 * return txtFs;
	 * 
	 * }
	 * 
	 * public void closeConnection() {
	 * 
	 * try { beanUsers.getRowSet().close();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

}
