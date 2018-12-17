package admingui.models;

public class AccessControlPolicy {

	int userId;
	String topicFilterExpression;
	boolean paremetricPredicate;
	String privileges;

	public AccessControlPolicy() {

	}

	public AccessControlPolicy(int userId, String topicFilterExpression, boolean paremetricPredicate, String privileges) {
		super();
		this.userId = userId;
		this.topicFilterExpression = topicFilterExpression;
		this.paremetricPredicate = paremetricPredicate;
		this.privileges = privileges;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTopicFilterExpression() {
		return topicFilterExpression;
	}

	public void setTopicFilterExpression(String topicFilterExpression) {
		this.topicFilterExpression = topicFilterExpression;
	}

	public boolean isParemetricPredicate() {
		return paremetricPredicate;
	}

	public void setParemetricPredicate(boolean paremetricPredicate) {
		this.paremetricPredicate = paremetricPredicate;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "UserPolicy [userId=" + userId + ", topicFilterExpression=" + topicFilterExpression
				+ ", paremetricPredicate=" + paremetricPredicate + ", privileges=" + privileges + "]";
	}
}