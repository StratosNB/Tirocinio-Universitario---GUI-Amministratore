package admingui.db.dao;

import java.util.List;

import javax.swing.JTable;

import admingui.models.Attribute;
import admingui.models.User;

public interface UserDao {

	public void createUser(User user);

	public User getUserById(int userId);

	public List<User> getAllUsers();

	public void updateUser(User user, int oldId);

	public void deleteUser(int userId);

	public void setAllUsersInTable(JTable table);

	public void setFoundUsersInTable(JTable table, User user, String operator);

	public void getCompletedQuery(List<Attribute> attributes, String operator);
}
