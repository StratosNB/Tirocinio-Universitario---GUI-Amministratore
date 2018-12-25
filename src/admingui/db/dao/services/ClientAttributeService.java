package admingui.db.dao.services;

import java.util.List;

import admingui.db.dao.impl.ClientAttributeDaoImpl;
import admingui.model.Attribute;

public class ClientAttributeService {

	private final ClientAttributeDaoImpl clientAttributeDaoImpl = new ClientAttributeDaoImpl();

	public void createClientAttribute(Attribute attribute) {
		clientAttributeDaoImpl.createAttribute(attribute);
	}

	public List<Attribute> getClientAttributesById(int clientId) {
		return clientAttributeDaoImpl.getAttributesById(clientId);
	}

	public List<Attribute> getAllClientAttributes() {
		return clientAttributeDaoImpl.getAllAttributes();
	}

	public void updateClientAttribute(Attribute attribute, String oldName) {
		clientAttributeDaoImpl.updateAttribute(attribute, oldName);
	}
	
	public void updateAttributesValuesByClientId(List<Attribute> attributes, int userId) {
		clientAttributeDaoImpl.updateAttributesValuesById(attributes, userId);
	}

	public void deleteClientAttribute(String attributeName) {
		clientAttributeDaoImpl.deleteAttribute(attributeName);
	}
}