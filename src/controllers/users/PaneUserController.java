package controllers.users;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import db.beans.AttributeBean;
import db.beans.ClientBean;
import db.beans.UserBean;
import db.utils.UserClientsRecords;
import models.Attribute;
import models.User;
import view.attributePanes.AttributesView;
import view.userPanes.PaneUser;
import view.userPanes.UserView;

public class PaneUserController {

	private PaneUser pUser;
	private UserView theUserView;
	private AttributesView attribView;

	UserBean userBean = new UserBean();
	AttributeBean attribBean1;
	AttributeBean attribBean2 = new AttributeBean("attributes_values");
	ClientBean clientBean = new ClientBean();

	UserClientsRecords UserClientRecords = new UserClientsRecords();

	public PaneUserController(PaneUser paneUser) {

		this.pUser = paneUser;
		this.theUserView = paneUser.getUserView();
		this.attribView = paneUser.getAttribView();

		initTextBoxs();

		paneUser.addCrudListener(new CrudListener());
		paneUser.addNavListener(new NavListener());

	}

	public void initTextBoxs() {

		if (userBean.moveFirst() != null) {

			User user = userBean.moveFirst();
			theUserView.setFieldData(user);
			ArrayList<String> clientsID = new ArrayList<String>();

			clientsID.addAll(UserClientsRecords.getUserClientsIDs(user.getUserID()));

			theUserView.initBoxUserClients(clientsID);

		}
		// attribView.setFieldData()
	}

	class CrudListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			User user = theUserView.getFieldData();
			ArrayList<Attribute> attributes = attribView.getFieldData("user", user.getUserID());

			ArrayList<String> avaibleClientsID = new ArrayList<String>();
			avaibleClientsID.addAll(UserClientsRecords.getAvaibleUserClientsIDs());

			JButton createButton = pUser.getCreateButton();

			// int numOfUserAttributes = userBean.getRowCount();

			switch (e.getActionCommand()) {
			case "Search":
				// opens the searchUI

				break;
			case "Register":
				if (theUserView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Some fields are empty");
					return;
				}

				if (attribView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Some fields are empty");
					return;
				}
				// if userbean and attributebean not equal null
				//

				if (userBean.create(user) != null && attribBean2.create(attributes) != null) {

					UserClientRecords.updateUserID(user.getUserID());

					JOptionPane.showMessageDialog(null, "New user registered successfully.");

					createButton.setText("New...");

				}
				break;
			case "New...":

				CardLayout cardLayout = (CardLayout) pUser.getLayout();
				cardLayout.show(pUser, "Pane attributeSet");

				break;
			case "Update":

				if (theUserView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot update an empty record");
					return;
				}
				if (userBean.update(user) != null)
					JOptionPane.showMessageDialog(null,
							"User with ID:" + user.getUserID() + " is updated successfully");
				break;
			case "Delete":
				if (theUserView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot delete an empty record");
					return;
				}
				user = userBean.getCurrent();
				userBean.delete();
				JOptionPane.showMessageDialog(null, "User with ID:" + user.getUserID() + " is deleted successfully");
				break;

			}

		}
	}

	class NavListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "First":
				theUserView.setFieldData(userBean.moveFirst());
				break;
			case "Previous":
				theUserView.setFieldData(userBean.movePrevious());
				break;
			case "Next":
				theUserView.setFieldData(userBean.moveNext());
				break;
			case "Last":
				theUserView.setFieldData(userBean.moveLast());
				break;
			default:
				JOptionPane.showMessageDialog(null, "invalid command");
			}

		}

	}

	public void closeConnections() {

		try {
			userBean.getRowSet().close();
			attribBean1.getRowSet().close();
			attribBean2.getRowSet().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
