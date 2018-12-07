package admingui.models;

public class ClientPolicy {

	int clientId;
	String topicFilterExpression;
	String parametricPredicate;
	boolean read;
	boolean write;

	public ClientPolicy(int clientId, String topicFilterExpression, String parametricPredicate, boolean read,
			boolean write) {
		super();
		this.clientId = clientId;
		this.topicFilterExpression = topicFilterExpression;
		this.parametricPredicate = parametricPredicate;
		this.read = read;
		this.write = write;
	}

	public ClientPolicy() {

	}
}
