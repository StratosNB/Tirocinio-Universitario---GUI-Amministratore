package admingui.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import admingui.db.ConnectToDatabase;
import admingui.db.dao.UserDao;
import admingui.model.Attribute;
import admingui.model.User;
import net.proteanit.sql.DbUtils;

public class UserDaoImpl implements UserDao {

	private final Connection conn = ConnectToDatabase.createConnection();
	private final String SQL_CREATE_USER = "INSERT INTO users (id, name, surname, password, admin) VALUES (?, ?, ?, ?, ?)";
	private final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE Id=?";
	private final String SQL_GET_ALL_USERS = "SELECT * FROM users ORDER BY id";
	private final String SQL_GET_ALL_USERS_NO_PSWD = "SELECT id,name,surname,admin FROM users ORDER BY id";
	private String SQL_GET_ALL_USERS_BY_PARAMETERS_AND = "SELECT u.id,u.name,u.surname,u.admin FROM users u ";
	private final String SQL_GET_ALL_USERS_BY_PARAMETERS_OR = "SELECT u.id,u.name,u.surname,u.admin,users_attributes.name,"
			+ "users_attributes_values.value,users_attributes.type FROM users u INNER JOIN users_attributes_values "
			+ "ON users_attributes_values.user_id=u.id INNER JOIN users_attributes ON users_attributes.id=users_attributes_values.attribute_id"
			+ " WHERE u.id=? OR u.name=? OR u.surname=? OR u.admin=?";
	private final String SQL_UPDATE_USER = "UPDATE users SET id=?, name=?, surname=?, password=?, admin=? WHERE Id=?";
	private final String SQL_DELETE_USER = "DELETE FROM users WHERE Id=?";

	@Override
	public void createUser(User user) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getSurname());
			pstmt.setString(4, user.getPassword());
			pstmt.setBoolean(5, user.getAdmin());
			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public User getUserById(int userId) {
		User user = new User();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_USER_BY_ID)) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					user.setId(rs.getInt(1));
					user.setName(rs.getString(2));
					user.setSurname(rs.getString(3));
					user.setPassword(rs.getString(4));
					user.setAdmin(rs.getBoolean(5));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_USERS); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2).trim());
				user.setSurname(rs.getString(3).trim());
				user.setPassword(rs.getString(4).trim());
				user.setAdmin(rs.getBoolean(5));
				allUsers.add(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return allUsers;
	}

	@Override
	public void updateUser(User user, int oldId) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_USER)) {
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getSurname());
			pstmt.setString(4, user.getPassword());
			pstmt.setBoolean(5, user.getAdmin());
			pstmt.setInt(6, oldId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_USER)) {
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void setAllUsersInTable(JTable table) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_USERS_NO_PSWD);
				ResultSet rs = pstmt.executeQuery()) {
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public PreparedStatement createPreparedStatement(User user, List<Attribute> attributes) {
		SQL_GET_ALL_USERS_BY_PARAMETERS_AND = "SELECT u.id,u.name,u.surname,u.admin FROM users u ";
		concatStatementsToQuery(user, attributes);
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SQL_GET_ALL_USERS_BY_PARAMETERS_AND);

			int index = 1;

			if (!attributes.isEmpty()) {
				for (Attribute a : attributes) {
					pstmt.setString(index++, a.getValue());
					pstmt.setString(index++, a.getName());
				}
			}

			if (user.getId() != 0) {
				pstmt.setInt(index++, user.getId());
			}
			if (!user.getName().isEmpty()) {
				pstmt.setString(index++, user.getName());
			}
			if (!user.getSurname().isEmpty()) {
				pstmt.setString(index++, user.getSurname());
			}

			if (!user.getRoleRaw().isEmpty()) {
				pstmt.setBoolean(index++, user.getAdmin());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public void concatStatementsToQuery(User user, List<Attribute> attributes) {

		int index = 1;
		int num = 0;
		while (num < attributes.size()) {
			SQL_GET_ALL_USERS_BY_PARAMETERS_AND += "JOIN users_attributes_values uav" + index + " ON u.id=uav" + index
					+ ".user_id AND uav" + index + ".value=? " + "JOIN users_attributes ua" + index + " ON uav" + index
					+ ".attribute_id = ua" + index + ".id AND ua" + index + ".name=?";
			index++;
			num++;
		}

		if (user.getId() != 0) {
			if (SQL_GET_ALL_USERS_BY_PARAMETERS_AND.contains("WHERE")) {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += " AND u.id=?";
			} else {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += "WHERE u.id=?";
			}
		}

		if (!user.getName().isEmpty()) {
			if (SQL_GET_ALL_USERS_BY_PARAMETERS_AND.contains("WHERE")) {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += " AND u.name=?";
			} else {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += "WHERE u.name=?";
			}
		}

		if (!user.getSurname().isEmpty()) {
			if (SQL_GET_ALL_USERS_BY_PARAMETERS_AND.contains("WHERE")) {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += " AND u.surname=?";
			} else {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += "WHERE u.surname=?";
			}
		}

		if (!user.getRoleRaw().isEmpty()) {
			if (SQL_GET_ALL_USERS_BY_PARAMETERS_AND.contains("WHERE")) {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += " AND u.admin=?";
			} else {
				SQL_GET_ALL_USERS_BY_PARAMETERS_AND += "WHERE u.admin=?";
			}
		}
	}

	@Override
	public void setFoundUsersInTable(JTable table, User user, List<Attribute> attributes, String operator) {
		switch (operator) {
		case "AND":
			try (PreparedStatement pstmt = createPreparedStatement(user, attributes)) {
				ResultSet rs = pstmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			break;

		case "OR":
			try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_USERS_BY_PARAMETERS_OR)) {
				pstmt.setInt(1, user.getId());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getSurname());
				pstmt.setBoolean(4, user.getAdmin());
				ResultSet rs = pstmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			break;
		}
	}
}