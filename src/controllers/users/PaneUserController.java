package controllers.users;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import models.UserAttribute;
import models.User;
import view.attributePanes.AttributesView;
import view.userPanes.PaneUser;
import view.userPanes.UserView;

public class PaneUserController {

	private PaneUser pUser;
	private UserView theUserView;
	private AttributesView attribView;

	public PaneUserController(PaneUser paneUser) {

		this.pUser = paneUser;
		this.theUserView = paneUser.getUserView();
		this.attribView = paneUser.getAttribView();

		initTextBoxs();

		paneUser.addCrudListener(new CrudListener());
		paneUser.addNavListener(new NavListener());

	}

	public void initTextBoxs() {

	}

	class CrudListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			User user = theUserView.getFieldData();
			ArrayList<UserAttribute> attributes = attribView.getFieldData("user", user.getUserID());

			ArrayList<String> avaibleClientsID = new ArrayList<String>();

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

				break;
			case "New...":

				CardLayout cardLayout = (CardLayout) pUser.getLayout();
				cardLayout.show(pUser, "Pane attributeSet");

				break;
			case "Update":

				break;
			case "Delete":

				break;

			}

		}
	}

	class NavListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "First":
				// theUserView.setFieldData(userBean.moveFirst());
				break;
			case "Previous":
				// theUserView.setFieldData(userBean.movePrevious());
				break;
			case "Next":
				// theUserView.setFieldData(userBean.moveNext());
				break;
			case "Last":
				// theUserView.setFieldData(userBean.moveLast());
				break;
			default:
				JOptionPane.showMessageDialog(null, "invalid command");
			}

		}

	}
}
