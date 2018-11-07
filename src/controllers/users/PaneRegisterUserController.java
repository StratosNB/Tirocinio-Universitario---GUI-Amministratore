package controllers.users;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import db.hibernate.service.UserService;
import models.User;
import view.userPanes.PaneRegisterUser;
import view.userPanes.PaneUser;

public class PaneRegisterUserController {

	private PaneUser paneUser;

	private PaneRegisterUser paneRegisterUser;

	private UserService userService = new UserService();

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
				userService.persist(user);
				break;

			}
		}
	}

	private void initComponents() {

	}

}
