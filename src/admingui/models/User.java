package admingui.models;

public class User {

	private int id;
	private String name;
	private String surname;
	private String password;
	private boolean admin;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isAdmin() {
		if (admin == false) {
			return false;
		}
		return true;
	}

	public String getRole() {
		if (isAdmin() == false) {
			return "User";
		}
		return "Admin";
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setAdmin(String s) {
		if (s.equals("Admin")) {
			admin = true;
			return;
		}
		admin = false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", password=" + password + ",Admin="
				+ admin + "]";
	}
}
