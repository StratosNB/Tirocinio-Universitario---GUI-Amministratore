package models;

import java.util.ArrayList;

public class User {

	private String UserID;
	private String Name;
	private String Surname;
	private String Password;
	private ArrayList<Client> clientsOwned;
	private String[] userClientsID;

	private String clientID;

	private ArrayList<String> userData;

	public User() {

	}

	public String getUserID() {
		return this.UserID;
	}

	public void setUserID(String userID) {
		this.UserID = userID;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getSurname() {
		return this.Surname;
	}

	public void setSurname(String surname) {
		this.Surname = surname;
	}

	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String getClientID() {
		return this.clientID;
	}

	public void setClientID(String client) {
		// TODO Auto-generated method stub
		this.clientID = client;
	}

	public ArrayList<String> getUserData() {
		return this.userData;

	}

	public ArrayList<Client> getClientsOwned() {
		return clientsOwned;
	}

	public void setClientsOwned(ArrayList<Client> clientsID) {
		this.clientsOwned.addAll(clientsID);
	}

	/*public String[] getUserClientsID() {
		String[] clientsID = new String[clientsOwned.size()];

		for (int i = 0; i < clientsOwned.size(); i++) {
			clientsID[i] = clientsOwned.get(i).getClientID();

			System.out.println("Method getUserClientsID, ID:" + clientsID[i]);
		}

		return clientsID;
	}*/

	public void setUserClientsID(String[] userClientsID) {
		this.userClientsID = userClientsID;
	}

}
