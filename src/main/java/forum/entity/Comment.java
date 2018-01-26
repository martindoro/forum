package forum.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private int ident;
	private String userName;
	private int topicId;
	private String content;
	private Date createdOn;
	private int replyto;

	public Comment() {
	}

	public Comment(String userName, int topicId, String content) {
		this.userName = userName;
		this.topicId = topicId;
		this.content = content;
		this.createdOn = new java.sql.Timestamp(new Date().getTime());
	}

	public Comment(String userName, int topicId, String content, int replyto) {
		this.userName = userName;
		this.topicId = topicId;
		this.content = content;
		this.replyto = replyto;
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

	public void setUserId(String userName) {
		this.userName = userName;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
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

	public int getReplyto() {
		return replyto;
	}

	public void setReplyto(int replyto) {
		this.replyto = replyto;
	}

	@Override
	public String toString() {
		return userName + " on " + createdOn;
	}

}
