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
	public long getCommentCount(int topicId) {
		return (long) entityManager.createQuery("SELECT COUNT(c)  FROM Comment c WHERE c.topicId = :topicId").setParameter("topicId", topicId).getSingleResult();
	}
}
