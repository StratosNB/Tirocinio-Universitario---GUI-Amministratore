package controllers.clients;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Client;
import view.clientPanes.PaneClient;
import view.clientPanes.PaneRegisterClient;

public class PaneRegisterClientController {

	private PaneClient paneClient;
	private PaneRegisterClient paneRegisterClient;

	public PaneRegisterClientController(PaneClient pClient) {

		this.paneClient = pClient;
		this.paneRegisterClient = pClient.getPaneRegisterClient();

		initComponents();

		paneRegisterClient.addNavListener(new NavListener());
		paneRegisterClient.addRegListener(new RegListener());
	}

	private void initComponents() {
		// TODO Auto-generated method stub

	}

	class NavListener implements ActionListener {

		CardLayout cardLayout = (CardLayout) paneClient.getLayout();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Back":

				cardLayout.show(paneClient, "pane Aset");
				break;

			case "Finish":

				cardLayout.show(paneClient, "viewClientPane");
				break;
			}
		}
	}

	class RegListener implements ActionListener {

		Client client = paneRegisterClient.getFieldData();

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "Register":

				break;

			}
		}
	}
}
