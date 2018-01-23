package forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class ForumUser {
//boolean userIsAdmin
	@Id
	@GeneratedValue
	private int ident;
	@Column(unique=true)
	public String login;
	@Column(unique=true)
	private String password;
	private String email;

	public ForumUser() {
	}
	
	public ForumUser(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}