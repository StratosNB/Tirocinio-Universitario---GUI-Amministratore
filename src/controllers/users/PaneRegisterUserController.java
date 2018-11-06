package controllers.users;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import db.beans.UserBean;
import models.User;
import view.userPanes.PaneRegisterUser;
import view.userPanes.PaneUser;
import view.userPanes.UserView;

public class PaneRegisterUserController {

	private PaneUser paneUser;

	private PaneRegisterUser paneRegisterUser;

	private UserBean userBean = new UserBean();

	public PaneRegisterUserController(PaneUser pUser) {

		this.paneUser = pUser;
		
		this.paneRegisterUser = pUser.getPaneRegisterUser();

		paneRegisterUser.addNavListener(new NavListener());
		paneRegisterUser.addRegListener(new RegListener());

		initComponents();
	}

	class NavListener implements ActionListener {

		CardLayout cardLayout = (CardLayout) paneUser.getLayout();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Back":

				cardLayout.show(paneUser, "Pane attributeSet");
				break;

			case "Finish":

				cardLayout.show(paneUser, "viewUserPane");
				break;
			}
		}
	}

	class RegListener implements ActionListener {

		User user = paneRegisterUser.getFieldData();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Register":

				if (paneRegisterUser.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Some fields are empty");
					return;
				}

				if (userBean.create(user) != null) {

					JOptionPane.showMessageDialog(null, "New user registered successfully.");

				}
				break;

			}
		}
	}

	private void initComponents() {

	}

}
