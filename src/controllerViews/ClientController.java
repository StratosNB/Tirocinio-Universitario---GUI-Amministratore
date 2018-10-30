package controllerViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.Beans.ClientBean;
import models.Client;
import viewPanels.PaneClient;
import views.ClientView;

public class ClientController {

	private ClientView theClientView;
	private PaneClient paneClient;

	ClientBean bean = new ClientBean();

	public ClientController(PaneClient paneClient) {

		this.paneClient = paneClient;
		this.theClientView = paneClient.getClientView();

		paneClient.addCrudListener(new CrudListener());
		paneClient.addNavListener(new NavListener());

	}

	public void initTextBoxs() {
		// TODO Auto-generated method stub
		theClientView.setFieldData(bean.moveFirst());

		System.out.println("Bean move first name " + bean.moveFirst().getClientName());
		// user.getSurname());
	}

	class CrudListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Client client = theClientView.getFieldData();

			JButton createButton = paneClient.getCreateButton();
			switch (e.getActionCommand()) {
			case "Search":
				// opens the searchUI

				break;
			case "Register":
				if (theClientView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot create an empty record");
					return;
				}
				// if userbean and attributebean not equal null
				//

				if (bean.create(client) != null)
					JOptionPane.showMessageDialog(null, "New user registered successfully.");

				createButton.setText("New...");

				break;
			case "New...":

				// open JDialog for the definition of attributes
				client.setClientID("");
				client.setClientName("");

				theClientView.setFieldData(client);

				createButton.setText("Register");

				break;
			case "Update":
				if (theClientView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot update an empty record");
					return;
				}
				if (bean.update(client) != null)
					JOptionPane.showMessageDialog(null,
							"User with ID:" + client.getClientID() + " is updated successfully");
				break;
			case "Delete":
				if (theClientView.isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot delete an empty record");
					return;
				}
				client = bean.getCurrent();
				bean.delete();
				JOptionPane.showMessageDialog(null, "User with ID:" + client.getClientID() + " is deleted successfully");
				break;

			}

		}
	}

	class NavListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "First":
				theClientView.setFieldData(bean.moveFirst());
				break;
			case "Previous":
				theClientView.setFieldData(bean.movePrevious());
				break;
			case "Next":
				theClientView.setFieldData(bean.moveNext());
				break;
			case "Last":
				theClientView.setFieldData(bean.moveLast());
				break;
			default:
				JOptionPane.showMessageDialog(null, "invalid command");
			}

		}

	}

	public void closeConnections(){
		try {
			bean.getRowSet().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
