package admingui.db.dao.services;

import java.util.List;

import javax.swing.JTable;

import admingui.db.dao.impl.UserAcPolicyDaoImpl;
import admingui.model.AccessControlPolicy;

public class UserAcPolicyService {
	private final UserAcPolicyDaoImpl dao = new UserAcPolicyDaoImpl();

	public void createAcPolicy(AccessControlPolicy policy) {
		dao.create(policy);
	}

	public List<AccessControlPolicy> getAll() {
		return dao.getAll();
	}

	public void setPoliciesInTable(JTable table, int userId){
		dao.setPoliciesInTable(table, userId);
	}

	public void updateBySubjectId(int newId, int oldId, AccessControlPolicy policy) {
		dao.updateBySubjectId(newId, oldId, policy);
	}

	public void deleteBySubjectId(int id) {
		dao.deleteBySubjectId(id);
	}
}
