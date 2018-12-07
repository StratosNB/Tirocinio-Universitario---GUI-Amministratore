package admingui.models;

public class UserPolicy {

	int userId;
	String topicFilterExpression;
	String parametricPredicate;
	boolean read;
	boolean write;

	public UserPolicy(int userId, String topicFilterExpression, String parametricPredicate, boolean read,
			boolean write) {
		super();
		this.userId = userId;
		this.topicFilterExpression = topicFilterExpression;
		this.parametricPredicate = parametricPredicate;
		this.read = read;
		this.write = write;
	}

	public UserPolicy () {

	}
}
