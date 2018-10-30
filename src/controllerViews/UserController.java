package controllerViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.Beans.AttributeBean;
import controller.Beans.UserBean;
import models.Attribute;
import models.User;
import viewPanels.PaneUser;
import views.AttributesView;
import views.UserView;

public class UserController {

	private UserView theUserView;
	private PaneUser pUser;
	private AttributesView attribView;

	UserBean userBean = new UserBean();
	AttributeBean attribBean1;
	AttributeBean attribBean2 = new AttributeBean("attributes_values");

	public UserController(PaneUser paneUser) {

		this.pUser = paneUser;
		this.theUserView = paneUser.getUserView();
		this.attribView = paneUser.getAttribView();

		paneUser.addCrudListener(new CrudListener());
		paneUser.addNavListener(new NavListener());

	}

	public void initTextBoxs() {
		// TODO Auto-generated method stub
		if (userBean.moveFirst() != null) {
			theUserView.setFieldData(userBean.moveFirst());
		}
		// attribView.setFieldData()
	}

	class CrudListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			User user = theUserView.getFieldData();
			ArrayList<Attribute> attributes = attribView.getFieldData("user", user.getUserID());
			System.out.println("Attributes size: " + attributes.size());

			/*
			 * if (!(attribBean2.getRowCount() == 0)) { attributes =
			 * attribBean2.getAll(); }
			 */

			JButton createButton = pUser.getCreateButton();
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

				if (userBean.create(user) != null && attribBean2.create(attributes) != null)
					JOptionPane.showMessageDialog(null, "New user registered successfully.");

				createButton.setText("New...");

				break;
			case "New...":

				// open JDialog for the definition of attributes
				user.setUserID("");
				user.setName("");
				user.setSurname("");
				user.setPassword("");
				user.setClientID("");

				theUserView.setFieldData(user);

				createButton.setText("Register");

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
