package forum.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Topic {
	@Id
	@GeneratedValue
	private int ident;
	private String userName;
	private int categoryId;
	private String content;
	private Date createdOn;
	private boolean lock;

	public Topic() {
	}

	/**
	 * Constructor for Topic object with all necessary variables and auto timestamp on creation
	 * and auto topic unlocked
	 * @param userName user login - author of topic
	 * @param categoryId category ident where topic was created
	 * @param content topic content
	 */
	public Topic(String userName, int categoryId, String content) {
		this.userName = userName;
		this.categoryId = categoryId;
		this.content = content;
		this.createdOn = new java.sql.Timestamp(new Date().getTime());
		this.lock = false;
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

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}
}