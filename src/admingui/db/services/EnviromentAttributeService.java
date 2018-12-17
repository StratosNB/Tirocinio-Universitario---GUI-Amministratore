package admingui.db.services;

import java.util.List;

import admingui.db.daoImpl.EnvironmentAttributeDaoImpl;
import admingui.models.Attribute;

public class EnviromentAttributeService {
	private final EnvironmentAttributeDaoImpl enviromentAttributeDaoImpl = new EnvironmentAttributeDaoImpl();

	public void createAttribute(Attribute attribute) {
		enviromentAttributeDaoImpl.createAttribute(attribute);
	}

	public List<Attribute> getAllAttributes() {
		return enviromentAttributeDaoImpl.getAllAttributes();
	}

	public void updateAttribute(Attribute attribute, String oldName) {
		enviromentAttributeDaoImpl.updateAttribute(attribute, oldName);
	}

	public void deleteAttribute(String attributeName) {
		enviromentAttributeDaoImpl.deleteAttribute(attributeName);
	}
}
