package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@Column(name = "clientid")
	private String clientID;

	@Column(name = "clientname")
	private String clientName;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	//private Set<UserAttribute> Attributes = new HashSet();

	public Client() {

	}

	/* getters and setters */

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

/*	public Set<UserAttribute> getAttributes() {
		return Attributes;
	}

	public void setAttributes(Set<UserAttribute> attributes) {
		Attributes = attributes;
	}*/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
