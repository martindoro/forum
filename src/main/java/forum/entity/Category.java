package forum.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Category {
	@Id
	@GeneratedValue
	private int ident;
	private String content;
	
	public Category() {
	}
	
	public Category(String content) {
		this.content = content;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
