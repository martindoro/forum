package forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames= {"userId", "commentId"})})
public class Favorite {
	@Id
	@GeneratedValue
	private int ident;
	private String userName;
	private int commentId;
	
	public Favorite() {
	}
	
	public Favorite(String userName, int commentId) {
		this.userName = userName;
		this.commentId = commentId;
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
