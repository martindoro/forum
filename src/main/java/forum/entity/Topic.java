package forum.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
@Entity
public class Topic {
	@Id
	@GeneratedValue
	private int ident;
	private String userName;
	private int categoryId;
	private String content;
	private Date createdOn;

	
	
	public Topic() {
	}
	
	public Topic(String userName, int categoryId, String content) {
		this.userName = userName;
		this.categoryId = categoryId;
		this.content = content;
		this.createdOn = new java.sql.Timestamp(new Date().getTime());
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
}
