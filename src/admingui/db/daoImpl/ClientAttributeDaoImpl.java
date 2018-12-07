package admingui.db.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import admingui.db.ConnectToDatabase;
import admingui.db.dao.AttributeDao;
import admingui.models.Attribute;

public class ClientAttributeDaoImpl implements AttributeDao {

	private final Connection conn = ConnectToDatabase.createConnection();
	private final String SQL_CREATE_ATTRIBUTE = "INSERT INTO clients_attributes (name, type) VALUES (?,?)";
	private final String SQL_GET_ATTRIBUTES_BY_CLIENT_ID = "SELECT clients_attributes.id,name,type,value FROM clients_attributes "
			+ "INNER JOIN clients_attributes_values ON clients_attributes.id = attribute_id AND client_id=? ORDER BY clients_attributes.id";
	private final String SQL_GET_ALL_ATTRIBUTES = "SELECT * FROM clients_attributes ORDER BY id";
	private final String SQL_UPDATE_ATTRIBUTE = "UPDATE clients_attributes SET name=?, type=? WHERE name=?";
	private final String SQL_UPDATE_ATTRIBUTES_VALUES_BY_CLIENT_ID = "UPDATE clients_attributes_values SET value=? WHERE client_id=? AND attribute_id=?";
	private final String SQL_DELETE_ATTRIBUTE = "DELETE FROM clients_attributes WHERE name=?";

	@Override
	public void createAttribute(Attribute attribute) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_ATTRIBUTE, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, attribute.getName());
			pstmt.setString(2, attribute.getType());
			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					attribute.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void createAttributes(List<Attribute> attributes) {
		for (Attribute a : attributes) {
			try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_ATTRIBUTE,
					Statement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, a.getName());
				pstmt.setString(2, a.getType());
				pstmt.executeUpdate();
				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						a.setId(generatedKeys.getInt(1));
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Attribute> getAttributesById(int clientId) {
		List<Attribute> attributes = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ATTRIBUTES_BY_CLIENT_ID)) {
			pstmt.setInt(1, clientId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Attribute attribute = new Attribute();
					attribute.setId(rs.getInt(1));
					attribute.setName(rs.getString(2).trim());
					attribute.setType(rs.getString(3).trim());
					try {
						attribute.setValue(rs.getString(4).trim());
					} catch (NullPointerException e) {
						attribute.setValue("Null");
					}
					attributes.add(attribute);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return attributes;
	}

	@Override
	public List<Attribute> getAllAttributes() {
		List<Attribute> allAttributes = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_ATTRIBUTES);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Attribute attribute = new Attribute();
				attribute.setId(rs.getInt(1));
				attribute.setName(rs.getString(2).trim());
				attribute.setType(rs.getString(3).trim());
				allAttributes.add(attribute);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return allAttributes;
	}

	@Override
	public void updateAttribute(Attribute attribute, String oldName) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_ATTRIBUTE)) {
			pstmt.setString(1, attribute.getName());
			pstmt.setString(2, attribute.getType());
			pstmt.setString(3, oldName);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updateAttributesValuesById(List<Attribute> attributes, int id) {
		for (Attribute a : attributes) {
			try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_ATTRIBUTES_VALUES_BY_CLIENT_ID)) {
				pstmt.setString(1, a.getValue());
				pstmt.setInt(2, id);
				pstmt.setInt(3, a.getId());
				pstmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void deleteAttribute(String attributeName) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_ATTRIBUTE)) {
			pstmt.setString(1, attributeName);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
