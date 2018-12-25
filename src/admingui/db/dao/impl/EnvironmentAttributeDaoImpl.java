package admingui.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import admingui.db.ConnectToDatabase;
import admingui.db.dao.AttributeDao;
import admingui.model.Attribute;

public class EnvironmentAttributeDaoImpl implements AttributeDao {

	private final Connection conn = ConnectToDatabase.createConnection();
	private final String SQL_CREATE_ATTRIBUTE = "INSERT INTO enviroment_attributes (name, type) VALUES (?,?)";
	private final String SQL_GET_ALL_ATTRIBUTES = "SELECT * FROM enviroment_attributes ORDER BY id";
	private final String SQL_UPDATE_ATTRIBUTE = "UPDATE enviroment_attributes SET name=?, type=? WHERE name=?";
	private final String SQL_DELETE_ATTRIBUTE = "DELETE FROM enviroment_attributes WHERE name=?";

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

	}

	@Override
	public List<Attribute> getAttributesById(int id) {
		return null;
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
				attribute.setType(rs.getString(4).trim());
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