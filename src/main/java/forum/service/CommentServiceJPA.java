package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import forum.entity.Comment;

@Transactional
public class CommentServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}
	

	public List<Comment> getCommentsUser(String userName) {
		return entityManager
				.createQuery("SELECT c FROM Comment c WHERE c.userName = :user_name ORDER BY c.createdOn DESC")
				.setParameter("user_name", userName).getResultList();
	}

	public List<Comment> getCommentsTopic(int topicId) {
		return entityManager.createQuery("SELECT c FROM Comment c WHERE c.topicId = :topic_id ORDER BY c.createdOn ASC")
				.setParameter("topic_id", topicId).getResultList();
	}
	public int getCommentCountForTopic(int topicId) {
		return (int) entityManager.createQuery("SELECT COUNT(c)  FROM Comment c WHERE c.topicId = :topicId").setParameter("topicId", topicId).getSingleResult();
	}
	
	public int getCommentCount() {
		return (int) entityManager.createQuery("SELECT COUNT(c)  FROM Comment c").getSingleResult();
	}
	
	public Comment lastCommented(int topicId) {
		return (Comment) entityManager.createQuery("SELECT c FROM Comment c WHERE c.topicId = :topicId ORDER BY c.createdOn DESC LIMIT 1").getSingleResult();
	}
}
