package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "userid")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy="user")
	private Set<Client> clients = new HashSet();
	
	//private Set<ClientAttribute> Attributes = new HashSet();

	public User() {

	}

	/* getters and setters*/

	public String getUserID() {
		return this.id;
	}

	public void setUserID(String userID) {
		this.id = userID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

/*	public Set<ClientAttribute> getAttributes() {
		return Attributes;
	}

	public void setAttributes(Set<ClientAttribute> attributes) {
		Attributes = attributes;
	}*/

}
