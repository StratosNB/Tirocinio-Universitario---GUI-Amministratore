package models;

public class Attribute {

	private String userID;
	private String clientID;
	private String attributeName;
	private String attributeValue;

	private String attributeType;

	public Attribute(String userID, String attributeName, String attributeValue) {

		this.userID = userID;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	public Attribute(String userID, String clientID, String attributeName, String attributeValue,
			String attributeType) {

		this.userID = userID;
		this.clientID = clientID;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.attributeType = attributeType;
	}

	public Attribute(String attributeName, String attributeValue) {

		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	public Attribute(String attributeName) {

		this.attributeName = attributeName;

	}

	public Attribute() {
		// TODO Auto-generated constructor stub
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
}
