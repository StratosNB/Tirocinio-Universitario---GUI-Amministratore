package controllers.clients;

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
import view.userPanes.ClientAttributeSetManagement;

public class PaneClientAttributeSetController {

	private AttributeBean beanClients = new AttributeBean("clients_attribute_set");

	private ClientAttributeSetManagement paneClientAttributeSet;

	private AttributesView clientAttribView;

	private PaneClient paneClient;

	public PaneClientAttributeSetController(PaneClient pClient) throws SQLException {

		this.paneClient = pClient;

		this.paneClientAttributeSet = pClient.getPaneAttributeSet();

		this.clientAttribView = pClient.getAttribView();

		paneClientAttributeSet.addCRUDListener(new CRUDListener());
		paneClientAttributeSet.addNavListener(new NavListener());

		initComponents();

	}

	class NavListener implements ActionListener {

		CardLayout cardLayout = (CardLayout) paneClient.getLayout();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Back":

				cardLayout.show(paneClient, "viewClientPane");
				break;

			case "Next":

				cardLayout.show(paneClient, "pane Register client");
				break;
			}
		}
	}

	class CRUDListener implements ActionListener {

		Attribute attribute = paneClientAttributeSet.getNewAttributeName();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Add":

				// saves attributeset in the db
				// updates the attribute views and attribute set view

				if (paneClientAttributeSet.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please insert the name");
					return;
				}

				if (beanClients.create(attribute) != null) {

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
					paneClientAttributeSet.revalidate();

					paneClientAttributeSet.getNewAttributeNameTextField().setText("");

				}
			case "Update":

			case "Remove":
			}
		}
	}

	public void initComponents() throws SQLException {

		int numOClientAttributes = beanClients.getRowCount();
		ArrayList<JLabel> clientAttribLbls = createClientLabels(numOClientAttributes);

		ArrayList<JTextField> clientAttribTxts = createTextFields(numOClientAttributes);

		JPanel clientAttribPane = this.clientAttribView.getAttribPanel();

		/*
		 * for (int i = 0; i < numOfUserAttributes; i++) {
		 * 
		 * registerUserPane.add(userAttribLbls.get(i), "right");
		 * registerUserPane.add(userAttribTxts.get(i));
		 * 
		 * }
		 */

		for (int i = 0; i < numOClientAttributes; i++) {

			clientAttribPane.add(clientAttribLbls.get(i), "right");
			clientAttribPane.add(clientAttribTxts.get(i));
		}

	}

	public ArrayList<JLabel> createClientLabels(int numOfAttributes) {

		ArrayList<JLabel> labels = new ArrayList<JLabel>();

		if (!(numOfAttributes == 0)) {
			ArrayList<Attribute> attributes = beanClients.getAllFromClients();

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

			beanClients.getRowSet().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
