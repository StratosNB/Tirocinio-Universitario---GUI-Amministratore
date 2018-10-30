package models;

import java.util.ArrayList;

public class User {

	private String UserID;
	private String Name;
	private String Surname;
	private String Password;
	private String clientID;
	
	private ArrayList<String> userData;
	
	
	public User() {

	}

	public User(String userID, String name, String surname, String codiceFiscale, String password, String clientID) {

		this.UserID = userID;
		this.Name = name;
		this.Surname = surname;
		this.Password = password;
		this.clientID = clientID;
		
		this.userData.add(userID);
		this.userData.add(name);
		this.userData.add(surname);
		this.userData.add(password);
		this.userData.add(clientID);
	}
	
	public User(ArrayList<String> data) {

		this.UserID = data.get(0);
		this.Name = data.get(1);
		this.Surname = data.get(2);
		this.Password = data.get(3);
		this.clientID = data.get(4);
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
	
	public ArrayList<String> getUserData(){	
		return this.userData;
		
	}


}
