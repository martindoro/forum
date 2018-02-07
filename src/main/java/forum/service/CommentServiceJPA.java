package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import forum.entity.Comment;

@Transactional
public class CommentServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}

	/**
	 * Content of existed commented can be edited.
	 * 
	 * @param ident
	 *            ID of particular comment for database selection
	 * @param content
	 *            particular comment which will be edited
	 */
	public void editComment(int ident, String content) {
		entityManager.createQuery("UPDATE Comment c SET c.content = :content WHERE c.ident = :ident")
				.setParameter("content", content).setParameter("ident", ident).executeUpdate();
	}

	/**
	 * Get particular comment based on ID
	 * 
	 * @param ident
	 *            ID of this comment for selection from database
	 * @return content of particular single comment
	 */
	public Comment getComment(int ident) {
		return (Comment) entityManager.createQuery("SELECT c FROM Comment c WHERE c.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}

	/**
	 * Get list of comments for particular user ordered by descending date of
	 * creation
	 * 
	 * @param userName
	 *            name of logged user
	 * @return list of users comments
	 */

	public List<Comment> getCommentsUser(String userName) {
		return entityManager
				.createQuery("SELECT c FROM Comment c WHERE c.userName = :user_name ORDER BY c.createdOn DESC")
				.setParameter("user_name", userName).getResultList();
	}

	/**
	 * Get list of all comments for topic
	 * 
	 * @param topicId
	 *            topic ID for database selection
	 * @return all comments for topic in ascending order
	 */

	public List<Comment> getCommentsTopic(int topicId) {
		return entityManager.createQuery("SELECT c FROM Comment c WHERE c.topicId = :topic_id ORDER BY c.createdOn ASC")
				.setParameter("topic_id", topicId).getResultList();
	}

	/**
	 * Count total comments in topic
	 * 
	 * @param topicId
	 *            ID of topic for database selection
	 * @return number of total comments in topic
	 */

	public long getCommentCountForTopic(int topicId) {
		return (long) entityManager.createQuery("SELECT COUNT(c.ident)  FROM Comment c WHERE c.topicId = :topicId")
				.setParameter("topicId", topicId).getSingleResult();
	}

	/**
	 * Count total comments for user
	 * 
	 * @param username
	 *            username for database selection
	 * @return number of total comments for user
	 */
	public long getCommentCountForUser(String username) {
		return (long) entityManager.createQuery("SELECT COUNT(c.ident)  FROM Comment c WHERE c.userName = :username")
				.setParameter("username", username).getSingleResult();
	}

	/**
	 * Count total comments in forum
	 * 
	 * @return number of total comments
	 */

	public long getCommentCount() {
		return (long) entityManager.createQuery("SELECT COUNT(c.ident)  FROM Comment c").getSingleResult();
	}

	/**
	 * Return information about last comment in every topic
	 * 
	 * @param topicId
	 *            ID of topic for database selection
	 * @return user and time and date for last comment in every topic
	 */

	public Comment lastCommented(int topicId) {
		try {
			return (Comment) entityManager
					.createQuery("SELECT c FROM Comment c WHERE c.topicId = :topicId ORDER BY c.createdOn DESC")
					.setParameter("topicId", topicId).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Update value of comment
	 * 
	 * @param ident
	 *            ID of comment for database selection
	 * @param value
	 *            new value set by user
	 */

	public void setCommentValue(int ident, int value) {
		entityManager.createQuery("UPDATE Comment c SET c.value = c.value + " + value + " WHERE c.ident = :ident")
				.setParameter("ident", ident).executeUpdate();
	}

	/**
	 * Get most popular comment which has most likes/highest value
	 * 
	 * @param ident
	 *            ID of topic for selection from database
	 * @return comment with highest value
	 */

	public int getTopCommentValue(int ident) {
		try {
			return (int) entityManager.createQuery("SELECT MAX(c.value) FROM Comment c WHERE c.topicId = :ident")
					.setParameter("ident", ident).getSingleResult();
		} catch (Exception e) {
			return 0;
		}
	}
}
