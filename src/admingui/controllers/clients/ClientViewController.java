package admingui.controllers.clients;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import admingui.db.services.ClientAttributeService;
import admingui.db.services.ClientService;
import admingui.models.Attribute;
import admingui.models.Client;
import admingui.views.attributes.client_user.AttributesPanel;
import admingui.views.attributes.dialogs_panels.AdditionalAttributesPanel;
import admingui.views.client.ClientView;
import admingui.views.client.dialogs.AddClientDialog;
import admingui.views.client.dialogs.EditClientDialog;
import admingui.views.client.dialogs.SearchClientDialog;

public class ClientViewController {

	private ClientView clientView;

	private AddClientDialog dialogAddClient;
	private EditClientDialog dialogEditClient;
	private SearchClientDialog dialogSearchClient;

	private AdditionalAttributesPanel additionalAttributesPanel;

	private AttributesPanel attributesPanel;

	private ClientService clientService;
	private ClientAttributeService clientAttributeService;

	private List<Client> clients;
	private List<Attribute> clientsAttributes;

	private int index = 0;

	public ClientViewController(ClientView clientView, AttributesPanel attributesPanel) {
		this.clientView = clientView;
		this.dialogAddClient = clientView.getAddClient();
		this.dialogEditClient = clientView.getEditClient();
		this.dialogSearchClient = clientView.getSearchClient();
		this.additionalAttributesPanel = clientView.getAdditionalAttributesPanel();
		this.attributesPanel = attributesPanel;
		initialize();
	}

	protected void initialize() {
		initServices();
		initLists();
		addListeners();
		initViews();
	}

	protected void initServices() {
		this.clientService = new ClientService();
		this.clientAttributeService = new ClientAttributeService();
	}

	protected void initLists() {
		clients = clientService.getAllClients();
		updateClientsAttributesList(getClientFromList(index).getId());
	}

	protected void addListeners() {
		clientView.addButtonsListener(new CrudButtonsListener());
		clientView.addNavigationListener(new NavigationButtonsListener());
		clientView.addCancelListener(new DialogsCancelButtonsListener());
		clientView.addAddClientOkListener(new DialogsSubmmitButtonsListener(0));
		clientView.addEditClientOkListener(new DialogsSubmmitButtonsListener(1));
		attributesPanel.addTableChangesListener(new TableChangesListener());
	}

	protected void initViews() {
		additionalAttributesPanel.createAttributeViews(clientsAttributes);
		setDataInClientView(getClientFromList(index));
	}

	protected void setDataInClientView(Client client) {
		clientView.setFixedAttributesData(client);
		clientView.setClient(client);
		updateClientsAttributesList(getClientFromList(index).getId());
		additionalAttributesPanel.setValuesInAttributesViews(clientsAttributes);
	}

	protected void openEditClientDialog() {
		Client client = getClientFromList(index);
		dialogEditClient.setFixedAttributes(client);
		dialogEditClient.getClientForm()
				.createAdditionalAttributesComponents(clientAttributeService.getClientAttributesById(client.getId()));
		dialogEditClient.show();
	}

	protected void openAddClientDialog() {
		Client client = getClientFromList(index);
		dialogAddClient.getClientForm().createEmptyAdditionalAttributesComponents(
				clientAttributeService.getClientAttributesById(client.getId()));
		dialogAddClient.show();
	}

	protected void openSearchClientDialog() {
		Client client = getClientFromList(index);
		dialogSearchClient.getClientForm().createEmptyAdditionalAttributesComponents(
				clientAttributeService.getClientAttributesById(client.getId()));
		dialogSearchClient.show();
	}

	protected void deleteClient(int clientId, int option) {
		if (option == 0) {
			clientService.deleteClient(clientId);
			deleteClientFromList(clientId);
			JOptionPane.showMessageDialog(clientView.getMainPanel(),
					"User *** " + clientId + " *** deleted with success");
		}
	}

	protected void createNewClient(Client newClient) {
		clientService.createClient(newClient);
		clients.add(newClient);
		List<Attribute> attributes = clientAttributeService.getClientAttributesById(newClient.getId());
		clientAttributeService.updateAttributesValuesByClientId(
				dialogAddClient.getClientForm().createAttributes(attributes), newClient.getId());
		JOptionPane.showMessageDialog(clientView.getAddClient().getDialog(),
				"Client *** " + newClient.getId() + " *** created with success");
		dialogAddClient.clear();
	}

	protected void updateClient(Client oldClient, Client newClient) {
		clientService.updateClient(newClient, oldClient.getId());
		updateClientsList();
		updateClientAttributes(oldClient, newClient);
		setDataInClientView(newClient);
	}

	protected void updateClientAttributes(Client oldClient, Client newClient) {
		dialogEditClient.getClientForm().updateAttributesListValues();
		clientsAttributes = dialogEditClient.getClientForm().getAttributes();
		clientAttributeService.updateAttributesValuesByClientId(clientsAttributes, newClient.getId());
		additionalAttributesPanel.setValuesInAttributesViews(clientsAttributes);
		JOptionPane.showMessageDialog(dialogEditClient.getDialog(),
				"User *** " + oldClient.getId() + " *** updated successfully");
		dialogEditClient.dispose();
	}

	protected Client getClientFromList(int index) {
		return clients.get(index);
	}

	protected void updateClientsList() {
		clients = clientService.getAllClients();
	}

	protected void updateClientsAttributesList(int id) {
		clientsAttributes = clientAttributeService.getClientAttributesById(id);
	}

	protected void deleteClientFromList(int clientId) {
		Iterator<Client> iter = clients.iterator();
		while (iter.hasNext()) {
			Client client = iter.next();
			if (client.getId() == clientId) {
				iter.remove();
			}
		}
	}

	protected void updateClientView() {
		additionalAttributesPanel.getMainPanel().removeAll();
		updateClientsAttributesList(getClientFromList(index).getId());
		additionalAttributesPanel.createAttributeViews(clientsAttributes);
	}

	private class CrudButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {
			case "Search...":
				openSearchClientDialog();
				break;

			case "Edit...":
				openEditClientDialog();
				break;

			case "Add...":
				openAddClientDialog();
				break;

			case "Delete":
				int clientId = clientView.getClientID();
				int option = JOptionPane.showConfirmDialog(clientView.getMainPanel(),
						"Do you want to delete this Client?", "Delete Warning", JOptionPane.YES_NO_OPTION);
				deleteClient(clientId, option);
				break;
			}
		}
	}

	private class NavigationButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "First":
				index = 0;
				setDataInClientView(getClientFromList(index));
				break;

			case "Previous":
				index--;
				if (index < 0) {
					index = 0;
					JOptionPane.showMessageDialog(clientView.getMainPanel(), "You have reached the beginning");
					return;
				}
				setDataInClientView(getClientFromList(index));
				break;

			case "Next":
				index++;
				if (index == clients.size()) {
					index = clients.size() - 1;
					JOptionPane.showMessageDialog(clientView.getMainPanel(), "The are not more records to show");
					return;
				}
				setDataInClientView(getClientFromList(index));
				break;

			case "Last":
				index = clients.size() - 1;
				setDataInClientView(getClientFromList(index));
				break;
			}
		}
	}

	private class DialogsSubmmitButtonsListener implements ActionListener {

		private final int actionListenerNum;

		public DialogsSubmmitButtonsListener(int actionListenerNum) {
			this.actionListenerNum = actionListenerNum;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (actionListenerNum) {
			case 0:
				if (dialogAddClient.isEmptyFixedAttributes()) {
					JOptionPane.showMessageDialog(clientView.getAddClient().getDialog(), "     Some Data is Empty");
				} else {
					Client client = clientView.getAddClient().getNewClient();
					createNewClient(client);
				}
				break;
			case 1:

				if (dialogEditClient.isEmptyFixedAttributes()) {
					JOptionPane.showMessageDialog(clientView.getEditClient().getDialog(),
							"Fixed attributes can't be empty");
				} else {
					Client newClient = dialogEditClient.getNewClient();
					Client oldClient = clientView.getClient();
					updateClient(oldClient, newClient);
				}
				break;
			}
		}
	}

	private class DialogsCancelButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {
			case "Cancel":
				dialogAddClient.dispose();
				dialogEditClient.dispose();
				break;

			case "Quit":
				dialogSearchClient.dispose();
				break;
			}
		}
	}

	protected class TableChangesListener implements TableModelListener {
		@Override
		public void tableChanged(TableModelEvent e) {
			updateClientView();
		}
	}
}