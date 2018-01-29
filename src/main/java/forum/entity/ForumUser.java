package forum.entity;

import java.awt.Image;
import java.sql.Blob;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private int admin;
	
    private byte[] pic;
	
	
	
	public byte[] getPic(){
		return this.pic;
	}
	
	public void setPic(byte[] pic){
		this.pic = pic;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public ForumUser() {
	}
	
	public ForumUser(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	
	}

	@Override
	public String toString() {
		return "ForumUser [ident=" + ident + ", login=" + login + ", password=" + password + ", email=" + email
				+ ", admin=" + admin + ", pic=" + Arrays.toString(pic) + "]";
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
