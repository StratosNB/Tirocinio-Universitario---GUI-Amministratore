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
import admingui.db.dao.AcPolicyDao;
import admingui.model.AccessControlPolicy;
import net.proteanit.sql.DbUtils;

public class ClientAcPolicyDaoImpl implements AcPolicyDao {

	private final Connection conn = ConnectToDatabase.createConnection();
	private final String SQL_CREATE_AC_POLICY = "INSERT INTO clients_ac_policies(client_id,topic_filter_expression,parametric_predicate,permission) VALUES (?,?,?,?)";
	private final String SQL_GET_ALL_AC_POLICIES = "SELECT p.client_id,p.topic_filter_expression,p.parametric_predicate,p.permission FROM clients_ac_policies p ORDER BY id";
	private final String SQL_GET_AC_POLICY_BY_SUBJECT_ID = "SELECT client_id,topic_filter_expression,parametric_predicate,permission FROM clients_ac_policies WHERE client_id=? ORDER BY id";
	private final String SQL_UPDATE_AC_POLICY_BY_SUBJECT_ID = "UPDATE clients_ac_policies SET client_id=?, topic_filter_expression=?, parametric_predicate=?, permission=? WHERE client_id=?";
	private final String SQL_DELETE_AC_POLICY_BY_SUBJECT_ID = "DELETE FROM clients_ac_policies WHERE client_id=?";

	@Override
	public void create(AccessControlPolicy policy) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_AC_POLICY, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, policy.getId());
			pstmt.setString(2, policy.getTopicFilterExpression());
			pstmt.setString(3, policy.getParemetricPredicate());
			pstmt.setString(4, policy.getPermission());
			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					policy.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<AccessControlPolicy> getAll() {
		List<AccessControlPolicy> allPolicies = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_AC_POLICIES);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				AccessControlPolicy policy = new AccessControlPolicy();
				policy.setId(rs.getInt(1));
				policy.setTopicFilterExpression(rs.getString(2).trim());
				policy.setParemetricPredicate(rs.getString(3).trim());
				policy.setPermission(rs.getString(4).trim());
				allPolicies.add(policy);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return allPolicies;
	}

	@Override
	public AccessControlPolicy getPolicyByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBySubjectId(int newId, int oldId, AccessControlPolicy policy) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_AC_POLICY_BY_SUBJECT_ID)) {
			pstmt.setInt(1, newId);
			pstmt.setString(2, policy.getTopicFilterExpression());
			pstmt.setString(3, policy.getParemetricPredicate());
			pstmt.setString(4, policy.getPermission());
			pstmt.setInt(5, oldId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteBySubjectId(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_AC_POLICY_BY_SUBJECT_ID)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void setPoliciesInTable(JTable table, int clientId) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_AC_POLICY_BY_SUBJECT_ID)) {
			pstmt.setInt(1, clientId);
			ResultSet rs = pstmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}