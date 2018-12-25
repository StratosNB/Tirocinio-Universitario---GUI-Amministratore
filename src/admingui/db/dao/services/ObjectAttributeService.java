package admingui.db.dao.services;

import java.util.List;

import admingui.db.dao.impl.ObjectAttributeDaoImpl;
import admingui.model.Attribute;

public class ObjectAttributeService {

	private final ObjectAttributeDaoImpl objectAttributeDaoImpl = new ObjectAttributeDaoImpl();

	public void createAttribute(Attribute attribute) {
		objectAttributeDaoImpl.createAttribute(attribute);
	}

	public List<Attribute> getAllAttributes() {
		return objectAttributeDaoImpl.getAllAttributes();
	}

	public void updateAttribute(Attribute attribute, String oldName) {
		objectAttributeDaoImpl.updateAttribute(attribute, oldName);
	}

	public void deleteAttribute(String attributeName) {
		objectAttributeDaoImpl.deleteAttribute(attributeName);
	}
}