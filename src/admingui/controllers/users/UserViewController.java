package admingui.controllers.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import admingui.db.services.UserAttributeService;
import admingui.db.services.UserService;
import admingui.models.Attribute;
import admingui.models.User;
import admingui.views.attributes.client_user.AttributesPanel;
import admingui.views.attributes.dialogs_panels.AdditionalAttributesPanel;
import admingui.views.user.UserView;
import admingui.views.user.dialogs.AddUserDialog;
import admingui.views.user.dialogs.AssignClientsDialog;
import admingui.views.user.dialogs.AssignedClientsDialog;
import admingui.views.user.dialogs.EditUserDialog;
import admingui.views.user.dialogs.SearchUserDialog;

public class UserViewController {

	private UserView userView;

	private AddUserDialog dialogAddUser;
	private EditUserDialog dialogEditUser;
	private SearchUserDialog dialogSearchUser;
	private AssignedClientsDialog dialogAssignedClients;
	private AssignClientsDialog dialogAssignClients;

	private JTable searchResultsTable;

	private AdditionalAttributesPanel additionalAttributesPanel;

	private AttributesPanel attributesPanel;

	private UserService userService;
	private UserAttributeService userAttributeService;

	private List<User> users;
	private List<Attribute> userAttributes;

	int selectedRow;
	int selectedUserId;

	private int index = 0;

	public UserViewController(UserView userView, AttributesPanel attributesPanel) {
		this.userView = userView;
		this.dialogAddUser = userView.getDialogAddUser();
		this.dialogEditUser = userView.getDialogEditUser();
		this.dialogSearchUser = userView.getDialogSearchUser();
		this.dialogAssignedClients = userView.getDialogAssignedClients();
		this.dialogAssignClients = userView.getDialogAssignClients();
		this.searchResultsTable = dialogSearchUser.getResultsTable();
		this.additionalAttributesPanel = userView.getAdditionalAttributesPanel();
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
		userService = new UserService();
		userAttributeService = new UserAttributeService();
	}

	protected void initLists() {
		users = userService.getAllUsers();
		updateUserAttributesList(getUserFromList(index).getId());
	}

	protected void addListeners() {
		userView.addUserViewButtonsListener(new CrudButtonsListener());
		userView.addNavigationListener(new NavigationButtonsListener());
		userView.addCancelListener(new DialogsCancelButtonsListener());
		userView.addDialogsListener(new DialogsSubmitButtonsListener());
		attributesPanel.addTableChangesListener(new TableChangesListener());
	}

	protected void initViews() {
		additionalAttributesPanel.createAttributeViews(userAttributes);
		setDataInUserView(getUserFromList(index));
	}

	protected void setDataInUserView(User user) {
		userView.setFixedAttributesData(user);
		userView.setUser(user);
		updateUserAttributesList(getUserFromList(index).getId());
		additionalAttributesPanel.setValuesInAttributesViews(userAttributes);
	}

	protected void openEditUserDialog(User user) {
		dialogEditUser.setFixedAttributes(user);
		dialogEditUser.getUserForm()
				.createAdditionalAttributesComponents(userAttributeService.getUserAttributesById(user.getId()));
		dialogEditUser.show();
	}

	protected void openAddUserDialog() {
		User user = getUserFromList(index);
		dialogAddUser.getUserForm()
				.createEmptyAdditionalAttributesComponents(userAttributeService.getUserAttributesById(user.getId()));
		dialogAddUser.show();
	}

	protected void openSearchUserDialog() {
		User user = getUserFromList(index);
		dialogSearchUser.getUserForm()
				.createEmptyAdditionalAttributesComponents(userAttributeService.getUserAttributesById(user.getId()));
		dialogSearchUser.show();
	}

	protected void openUserClientsDialog() {

	}

	protected boolean deleteUser(int userId, int option) {
		if (option == 0) {
			userService.deleteUser(userId);
			deleteUserFromList(userId);
			return true;
		}
		return false;
	}

	protected void createNewUser(User newUser) {
		userService.createUser(newUser);
		users.add(newUser);
		List<Attribute> attributes = userAttributeService.getUserAttributesById(newUser.getId());
		userAttributeService.updateAttributesValuesByUserId(dialogAddUser.getUserForm().createAttributes(attributes),
				newUser.getId());
		JOptionPane.showMessageDialog(dialogAddUser.getDialog(),
				"User *** " + newUser.getId() + " *** created with success");
		dialogAddUser.clear();
	}

	protected void updateUser(User oldUser, User newUser) {
		newUser.setPassword(oldUser.getPassword());
		userService.updateUser(newUser, oldUser.getId());
		updateUsersList();
		updateUserAttributes(oldUser, newUser);
		setDataInUserView(newUser);
	}

	protected void updateUserAttributes(User oldUser, User newUser) {
		dialogEditUser.getUserForm().updateAttributesListValues();
		userAttributes = dialogEditUser.getUserForm().getAttributes();
		userAttributeService.updateAttributesValuesByUserId(userAttributes, newUser.getId());
		additionalAttributesPanel.setValuesInAttributesViews(userAttributes);
		JOptionPane.showMessageDialog(dialogEditUser.getDialog(),
				"User *** " + newUser.getId() + " *** updated successfully");
		dialogEditUser.dispose();

	}

	protected User getUserFromList(int index) {
		return users.get(index);
	}

	protected void updateUsersList() {
		users = userService.getAllUsers();
	}

	protected void updateUserAttributesList(int userId) {
		userAttributes = userAttributeService.getUserAttributesById(userId);
	}

	protected void deleteUserFromList(int userId) {
		Iterator<User> iter = users.iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			if (user.getId() == userId) {
				iter.remove();
			}
		}
	}

	protected void disposeOfDialogs() {
		dialogAddUser.dispose();
		dialogEditUser.dispose();
		dialogAssignedClients.dispose();
		dialogAssignClients.dispose();
	}

	protected void updateUserView() {
		additionalAttributesPanel.getMainPanel().removeAll();
		updateUserAttributesList(getUserFromList(index).getId());
		additionalAttributesPanel.createAttributeViews(userAttributes);
	}

	protected class CrudButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int currentUserId;

			switch (e.getActionCommand()) {
			case "Edit...":
				User user = getUserFromList(index);
				openEditUserDialog(user);
				break;

			case "Add...":
				openAddUserDialog();
				break;

			case "Search...":
				openSearchUserDialog();
				break;

			case "Clients":

				currentUserId = getUserFromList(index).getId();
				dialogAssignedClients.getAssigendClientsTableModel().removeAllRows();
				dialogAssignedClients.getAssigendClientsTableModel().addData(currentUserId);
				dialogAssignedClients.setLabelText(currentUserId);
				dialogAssignedClients.show();
				break;

			case "Assign Clients...":

				dialogAssignClients.getAvaibleClientTableModel().removeAllRows();
				dialogAssignClients.getAvaibleClientTableModel().addData();
				dialogAssignClients.show();

				break;

			case "Delete":
				int userId = userView.getUserId();
				int option = JOptionPane.showConfirmDialog(userView.getMainPanel(), "Do you want to delete this User?",
						"Delete Warning", JOptionPane.YES_NO_OPTION);
				if (deleteUser(userId, option) == true) {
					JOptionPane.showMessageDialog(userView.getMainPanel(),
							"User *** " + userId + " *** deleted with success");
				}
				break;
			}
		}
	}

	protected class NavigationButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {

			case "First":
				index = 0;
				setDataInUserView(getUserFromList(index));
				break;

			case "Previous":
				index--;
				if (index < 0) {
					index = 0;
					JOptionPane.showMessageDialog(userView.getMainPanel(), "You have reached the beginning");
					return;
				}
				setDataInUserView(getUserFromList(index));
				break;

			case "Next":
				index++;
				if (index >= users.size()) {
					index = users.size() - 1;
					JOptionPane.showMessageDialog(userView.getMainPanel(), "The are not more records to show");
					return;
				}
				setDataInUserView(getUserFromList(index));
				break;

			case "Last":
				index = users.size() - 1;
				setDataInUserView(getUserFromList(index));
				break;
			}
		}
	}

	protected class DialogsSubmitButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			selectedRow = searchResultsTable.getSelectedRow();

			switch (e.getActionCommand()) {
			case "Add user":
				if (dialogAddUser.isEmptyFixedAttributes()) {
					JOptionPane.showMessageDialog(dialogAddUser.getDialog(), "Fixed Attributes can't be empty");
					return;
				}
				User user = dialogAddUser.getNewUser();
				user.setPassword("123");
				createNewUser(user);
				break;

			case "Edit user":
				if (userView.getDialogEditUser().isEmptyFixedAttributes()) {
					JOptionPane.showMessageDialog(dialogEditUser.getDialog(), "Fixed Attributes can't be empty");
					return;
				}
				User newUser = dialogEditUser.getNewUser();
				User oldUser = new User();
				if (dialogSearchUser.getDialog().isShowing()) {
					oldUser = userService.getUserById(selectedUserId);
				} else {
					oldUser = userView.getUser();
				}
				updateUser(oldUser, newUser);
				break;

			case "Show all users":

				userService.setAllUsersInTable(searchResultsTable);
				break;

			case "Search users":

				User inputUser = dialogSearchUser.createUser();
				String operator = "";

				if (inputUser.getId() == 0) {
					JOptionPane.showMessageDialog(dialogSearchUser.getDialog(), "    User ID can't be empty");
					return;
				}
				if (dialogSearchUser.getAnd().isSelected()) {
					operator = "AND";
				}
				if (dialogSearchUser.getOr().isSelected()) {
					operator = "OR";
				}
				if (operator.equals("")) {
					JOptionPane.showMessageDialog(dialogSearchUser.getDialog(), "  Please select a logic operator");
					return;
				}
				userService.setFoundUsersInTable(searchResultsTable, inputUser, operator);
				if (searchResultsTable.getModel().getRowCount() == 0) {
					JOptionPane.showMessageDialog(dialogSearchUser.getDialog(), "        No records found");
				}
				break;

			case "Filter users table":
				System.out.println("Filter!!");
				break;

			case "Edit user in search dialog":
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(dialogSearchUser.getDialog(), "         Select a row first");
					return;
				}
				selectedUserId = (int) searchResultsTable.getModel().getValueAt(selectedRow, 0);
				openEditUserDialog(userService.getUserById(selectedUserId));
				userService.setAllUsersInTable(searchResultsTable);
				break;

			case "Delete user in search dialog":
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(dialogSearchUser.getDialog(), "         Select a row first");
					return;
				}
				selectedUserId = (int) searchResultsTable.getModel().getValueAt(selectedRow, 0);
				int option = JOptionPane.showConfirmDialog(dialogSearchUser.getDialog(),
						"Do you want to delete this User?", "Delete Warning", JOptionPane.YES_NO_OPTION);

				if (deleteUser(selectedUserId, option) == true) {
					JOptionPane.showMessageDialog(dialogSearchUser.getDialog(),
							"User *** " + selectedUserId + " *** deleted with success");
				}

				break;

			case "Cancel search":
				dialogSearchUser.dispose();

				break;
			}
		}
	}

	protected class DialogsCancelButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			disposeOfDialogs();
		}
	}

	protected class TableChangesListener implements TableModelListener {
		@Override
		public void tableChanged(TableModelEvent e) {
			updateUserView();
		}
	}
}