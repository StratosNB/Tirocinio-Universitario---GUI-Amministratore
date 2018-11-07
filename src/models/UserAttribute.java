package models;

public class UserAttribute {

	private String AttributeID;
	private String attributeName;
	private String attributeValue;
	private User user; // the owner of this attribute

	public UserAttribute() {
		// TODO Auto-generated constructor stub
	}
	
	/* getters and setters*/

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
