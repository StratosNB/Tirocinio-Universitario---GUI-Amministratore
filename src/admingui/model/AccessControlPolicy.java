package admingui.model;

public class AccessControlPolicy {

	int id;
	String topicFilterExpression;
	String paremetricPredicate;
	String permission;

	public AccessControlPolicy() {

	}

	public AccessControlPolicy(int id, String topicFilterExpression, String paremetricPredicate, String permission) {
		super();
		this.id = id;
		this.topicFilterExpression = topicFilterExpression;
		this.paremetricPredicate = paremetricPredicate;
		this.permission = permission;
	}

	public int getId() {
		return id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public String getTopicFilterExpression() {
		return topicFilterExpression;
	}

	public void setTopicFilterExpression(String topicFilterExpression) {
		this.topicFilterExpression = topicFilterExpression;
	}

	public String getParemetricPredicate() {
		return paremetricPredicate;
	}

	public void setParemetricPredicate(String paremetricPredicate) {
		this.paremetricPredicate = paremetricPredicate;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String privileges) {
		this.permission = privileges;
	}

	@Override
	public String toString() {
		return "Policy [Id=" + id + ", topicFilterExpression=" + topicFilterExpression + ", paremetricPredicate="
				+ paremetricPredicate + ", permission=" + permission + "]";
	}
}