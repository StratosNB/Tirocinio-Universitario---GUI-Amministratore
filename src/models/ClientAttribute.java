package models;

public class ClientAttribute {

	private String AttributeID;
	private String attributeName;
	private String attributeValue;
	private Client client; // the owner of this attribute

	public ClientAttribute() {
		// TODO Auto-generated constructor stub
	}

	/* getters and setters */

	public String getAttributeID() {
		return AttributeID;
	}

	public void setAttributeID(String attributeID) {
		AttributeID = attributeID;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Client getClient() {
		return client;
	}

	public void setUser(Client client) {
		this.client = client;
	}
}
