package forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "content" }) })
public class Category {

	@Id
	@GeneratedValue
	private int ident;

	private String content;
/**
 * Constructor
 */
	public Category() {
	}

	public Category(String content) {
		this.content = content;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public int getIdent() {
		return ident;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "" + content;
	}

}
