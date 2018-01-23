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
				.createQuery("SELECT c FROM Comment c WHERE c.user_name = :user_name ORDER BY c.created_on DESC")
				.setParameter("user_name", userName).getResultList();
	}

	public List<Comment> getCommentsTopic(int topicId) {
		return entityManager.createQuery("SELECT c FROM Comment c WHERE c.topic_id = :topic_id ORDER BY c.created_on ASC")
				.setParameter("topic_id", topicId).getResultList();
	}
}
