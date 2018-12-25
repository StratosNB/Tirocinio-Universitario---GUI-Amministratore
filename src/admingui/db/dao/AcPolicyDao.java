package admingui.db.dao;

import java.util.List;

import javax.swing.JTable;

import admingui.model.AccessControlPolicy;

public interface AcPolicyDao {

	public void create(AccessControlPolicy policy);

	public List<AccessControlPolicy> getAll();

	public AccessControlPolicy getPolicyByName(String name);

	public void setPoliciesInTable(JTable table, int id);

	public void updateBySubjectId(int newId, int oldId, AccessControlPolicy policy);

	public void deleteBySubjectId(int id);

}
