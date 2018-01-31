package forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames= {"userName", "commentId"})})
public class Favorite {
	@Id
	@GeneratedValue
	private int ident;
	private String userName;
	private int commentId;
	private int value;
	
	public Favorite() {
	}
	
	public Favorite(String userName, int commentId) {
		this.userName = userName;
		this.commentId = commentId;
	}
	
	public Favorite(String userName, int commentId, int value) {
		this.userName = userName;
		this.commentId = commentId;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
}
