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
				.createQuery("SELECT c FROM Comment c WHERE c.userName = :userName ORDER BY c.createdOn DESC")
				.setParameter("userName", userName).getResultList();
	}

	public List<Comment> getCommentsTopic(int topicID) {
		return entityManager.createQuery("SELECT c FROM Comment c WHERE c.topicID = :topicID ORDER BY c.createdOn ASC")
				.setParameter("topicID", topicID).getResultList();
	}
}
