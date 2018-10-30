package models;

import java.util.List;

public class Client {

	private String clientID;
	private String clientName;

	public Client(String clientID, String clientName) {

		this.clientID = clientID;
		this.clientName = clientName;
	}
	
	public Client(List<String> data) {

		this.clientID = clientID;
		this.clientName = clientName;
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
