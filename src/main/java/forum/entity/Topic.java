package forum.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Topic {
	@Id
	@GeneratedValue
	private int ident;
	private int userId;
	private int categoryId;
	private String content;
	private Date createdOn;
	
	public Topic() {
	}
	
	public Topic(int userId, int categoryId, String content) {
		this.userId = userId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
