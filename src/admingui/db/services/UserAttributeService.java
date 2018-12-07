package admingui.db.services;

import java.util.List;

import admingui.db.daoImpl.UserAttributeDaoImpl;
import admingui.models.Attribute;

public class UserAttributeService {

	private final UserAttributeDaoImpl userAttributeDaoImpl = new UserAttributeDaoImpl();

	public void createUserAttribute(Attribute attribute) {
		userAttributeDaoImpl.createAttribute(attribute);
	}

	public List<Attribute> getUserAttributesById(int userId) {
		return userAttributeDaoImpl.getAttributesById(userId);
	}

	public List<Attribute> getAllUserAttributes() {
		return userAttributeDaoImpl.getAllAttributes();
	}

	public void updateUserAttribute(Attribute attribute, String oldName) {
		userAttributeDaoImpl.updateAttribute(attribute, oldName);
	}

	public void updateAttributesValuesByUserId(List<Attribute> attributes, int userId) {
		userAttributeDaoImpl.updateAttributesValuesById(attributes, userId);
	}

	public void deleteUserAttribute(String attributeName) {
		userAttributeDaoImpl.deleteAttribute(attributeName);
	}
}
