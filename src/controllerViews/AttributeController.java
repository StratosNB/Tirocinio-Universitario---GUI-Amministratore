package controllerViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Beans.AttributeBean;
import models.Attribute;
import viewPanels.PaneAttributes;
import viewPanels.PaneClient;
import viewPanels.PaneUser;
import views.AttributesView;

public class AttributeController {

	private AttributeBean beanUsers = new AttributeBean("users_attribute_set");
	private AttributeBean beanClients = new AttributeBean("clients_attribute_set");

	private PaneAttributes paneAttributes;
	private AttributesView userAttribView;
	private AttributesView clientAttribView;

	public AttributeController(PaneAttributes paneAttri, PaneUser pUser, PaneClient pClient) {

		this.paneAttributes = paneAttri;
		this.userAttribView = pUser.getAttribView();
		this.clientAttribView = pClient.getAttribView();

		paneAttri.addAddListener(new AddListener());
	}

	class AddListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Attribute attribute = paneAttributes.getFieldData();

			switch (e.getActionCommand()) {
			case "Add":

				if (paneAttributes.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Please insert some data");
					return;
				}

				// updates de jpanel with the current components

				if (paneAttributes.getSelectedEntity().equals(("Users")) && beanUsers.create(attribute) != null) {

					JOptionPane.showMessageDialog(null, "New user attribute created successfully");
					// Adds Components to the attributesView for the user
					userAttribView.getAttribPanel().add(new JLabel(attribute.getAttributeName()), "right");
					userAttribView.getAttribPanel().add(new JTextField(15));

					paneAttributes.getNameTxt().setText("");

					return;
				}

				if (paneAttributes.getSelectedEntity().equals(("Clients")) && beanClients.create(attribute) != null) {

					JOptionPane.showMessageDialog(null, "New client attribute created successfully");
					// Add components to the attributesView for the client

					clientAttribView.getAttribPanel().add(new JLabel(attribute.getAttributeName()), "right");
					clientAttribView.getAttribPanel().add(new JTextField(15));

					paneAttributes.getNameTxt().setText("");

					return;

				}

				break;

			}

		}
	}

	public void initComponents() throws SQLException {

		int numOfUserAttributes = beanUsers.getRowCount();
		int numOClientAttributes = beanClients.getRowCount();

		ArrayList<JLabel> userAttribLbls = createUserLabels(numOfUserAttributes);
		ArrayList<JLabel> clientAttribLbls = createClientLabels(numOClientAttributes);

		ArrayList<JTextField> userAttribTxts = createTextFields(numOfUserAttributes);
		ArrayList<JTextField> clientAttribTxts = createTextFields(numOClientAttributes);

		JPanel userAttribpane = this.userAttribView.getAttribPanel();
		JPanel clientAttribPane = this.clientAttribView.getAttribPanel();

		for (int i = 0; i < numOfUserAttributes; i++) {

			userAttribpane.add(userAttribLbls.get(i), "right");
			userAttribpane.add(userAttribTxts.get(i));
		}

		for (int i = 0; i < numOClientAttributes; i++) {

			clientAttribPane.add(clientAttribLbls.get(i), "right");
			clientAttribPane.add(clientAttribTxts.get(i));
		}

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
			beanUsers.getRowSet().close();
			beanClients.getRowSet().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
