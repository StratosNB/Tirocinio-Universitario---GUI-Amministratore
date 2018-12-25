package admingui.db.dao;

import java.util.List;

import admingui.model.Attribute;

public interface AttributeDao {

	public void createAttribute(Attribute attribute);
	
	public void createAttributes(List<Attribute> attributes);

	public List<Attribute> getAttributesById(int id);

	public List<Attribute> getAllAttributes();

	public void updateAttribute(Attribute attribute, String oldName);

	public void updateAttributesValuesById(List<Attribute> attributes, int id);

	public void deleteAttribute(String attributeName);

}
