package admingui.db.dao.services;

import java.util.List;

import javax.swing.JTable;

import admingui.db.dao.impl.UserDaoImpl;
import admingui.model.Attribute;
import admingui.model.User;

public class UserService {

	private final UserDaoImpl userDaoImpl = new UserDaoImpl();

	public void createUser(User user) {
		userDaoImpl.createUser(user);
	}

	public User getUserById(int userId) {
		return userDaoImpl.getUserById(userId);
	}

	public List<User> getAllUsers() {
		return userDaoImpl.getAllUsers();
	}

	public void updateUser(User user, int oldId) {
		userDaoImpl.updateUser(user, oldId);
	}

	public void deleteUser(int userId) {
		userDaoImpl.deleteUser(userId);
	}

	public void setAllUsersInTable(JTable table) {
		userDaoImpl.setAllUsersInTable(table);
	}

	public void setFoundUsersInTable(JTable table, User user, List<Attribute> attributes, String operator) {
		userDaoImpl.setFoundUsersInTable(table, user, attributes, operator);
	}
}
