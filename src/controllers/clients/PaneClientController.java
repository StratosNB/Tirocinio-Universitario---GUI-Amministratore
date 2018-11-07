package controllers.clients;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import models.Client;
import view.clientPanes.ClientView;
import view.clientPanes.PaneClient;

public class PaneClientController {

	private ClientView theClientView;
	private PaneClient paneClient;

	public PaneClientController(PaneClient paneClient) {

		this.paneClient = paneClient;
		this.theClientView = paneClient.getClientView();

		initTextBoxs();
		paneClient.addCrudListener(new CrudListener());
		paneClient.addNavListener(new NavListener());

	}

	public void initTextBoxs() {

	}

	class CrudListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Client client = theClientView.getFieldData();
			switch (e.getActionCommand()) {
			case "Search":
				// opens the searchUI

				break;
			case "Register":
				/*
				 * if (theClientView.isEmptyFieldData()) {
				 * JOptionPane.showMessageDialog(null,
				 * "Cannot create an empty record"); return; } // if userbean
				 * and attributebean not equal null //
				 * 
				 * if (bean.create(client) != null) {
				 * JOptionPane.showMessageDialog(null,
				 * "New client registered successfully.");
				 * 
				 * createButton.setText("New..."); }
				 * 
				 * break;
				 */
			case "New...":

				CardLayout cardLayout = (CardLayout) paneClient.getLayout();
				cardLayout.show(paneClient, "pane Aset");

				break;
			case "Update":
				if (theClientView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot update an empty record");
					return;
				}

				break;
			case "Delete":
				if (theClientView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot delete an empty record");
					return;
				}

				break;

			}

		}
	}

	class NavListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "First":

				break;
			case "Previous":

				break;
			case "Next":

				break;
			case "Last":

				break;
			default:
				JOptionPane.showMessageDialog(null, "invalid command");
			}

		}

	}

}
