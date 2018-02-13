package forum.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.ColumnTransformer;

@Entity
public class ForumUser {
	@Id
	@GeneratedValue
	private int ident;
	@Column(unique = true)
	public String login;
	//Please, comment line below in case of testing, H2 temporary database dont support password crypt
	@ColumnTransformer(write = "crypt(?, gen_salt('bf', 8))")
	private String password;
	private String email;
	private int admin;

	public int getAdmin() {
		return admin;
	}

	private byte[] pic;

	public byte[] getPic() {
		return this.pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public int getRights() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public ForumUser() {
	}

	/**
	 * Constructor for new forum user with all variables(login, password and email) except user picture,
	 * which is added to table later by update
	 * @param login new user login on registration
	 * @param password new user password on registration(gets hashed when writing to database)
	 * @param email new user email on registration
	 */
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