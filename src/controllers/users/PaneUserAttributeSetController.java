package controllers.users;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.beans.AttributeBean;
import models.Attribute;
import view.attributePanes.AttributesView;
import view.clientPanes.PaneClient;
import view.userPanes.PaneUser;
import view.userPanes.ClientAttributeSetManagement;

public class PaneUserAttributeSetController {

	private AttributeBean beanUsers = new AttributeBean("users_attribute_set");
	private ClientAttributeSetManagement paneUserAttributeSet;

	private AttributesView userAttribView;
	private AttributesView clientAttribView;
	
	private PaneUser paneUser;

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

		Attribute attribute = paneUserAttributeSet.getNewAttributeName();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Add":

				// saves attributeset in the db
				// updates the attribute views and attribute set view

				if (paneUserAttributeSet.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please insert the name");
					return;
				}

				if (beanUsers.create(attribute) != null) {

					JOptionPane.showMessageDialog(null, "New user attribute created successfully");
					// Adds Components to the attributesView for the user
					/*
					 * attributeSetView.getAttribPanel().add(new
					 * JLabel(attribute.getAttributeName()), "right");
					 * attributeSetView.getAttribPanel().add(new
					 * JTextField(15)); attribSetPanel.add(new
					 * JButton("Update")); attribSetPanel.add(new
					 * JButton("Remove"));
					 */

					System.out.println("new attribute name : " + attribute.getAttributeName());
					paneUserAttributeSet.revalidate();

					paneUserAttributeSet.getNewAttributeNameTextField().setText("");

				}
			case "Update":

			case "Remove":
			}
		}
	}

	public void initComponents() throws SQLException {

		int numOfUserAttributes = beanUsers.getRowCount();

		ArrayList<JLabel> userAttribLbls = createUserLabels(numOfUserAttributes);
	
		ArrayList<JTextField> userAttribTxts = createTextFields(numOfUserAttributes);

		JPanel userAttribpane = this.userAttribView.getAttribPanel();
	
		for (int i = 0; i < numOfUserAttributes; i++) {

			userAttribpane.add(userAttribLbls.get(i), "right");
			userAttribpane.add(userAttribTxts.get(i));

		}

		/*
		 * for (int i = 0; i < numOfUserAttributes; i++) {
		 * 
		 * registerUserPane.add(userAttribLbls.get(i), "right");
		 * registerUserPane.add(userAttribTxts.get(i));
		 * 
		 * }
		 */


	}

	public ArrayList<JLabel> createUserLabels(int numOfAttributes) {

		ArrayList<JLabel> labels = new ArrayList<JLabel>();

		if (!(numOfAttributes == 0)) {
			ArrayList<Attribute> attributes = beanUsers.getAllFromUsers();

			for (int i = 0; i < numOfAttributes; i++) {
				labels.add(new JLabel(attributes.get(i).getAttributeName().trim()));
			}

		}
		return labels;
	}

	public ArrayList<JTextField> createTextFields(int numOfAttributes) {

		ArrayList<JTextField> txtFs = new ArrayList<JTextField>();

		if (!(numOfAttributes == 0)) {
			for (int i = 0; i < numOfAttributes; i++) {
				txtFs.add(new JTextField(15));
			}
		}

		return txtFs;

	}

	public void closeConnection() {

		try {
			beanUsers.getRowSet().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
