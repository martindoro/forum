package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import forum.entity.Comment;
import forum.service.impl.CommentService;

@Transactional
public class CommentServiceJPA implements CommentService {

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see forum.service.CommentService#addComment(forum.entity.Comment)
	 */
	@Override
	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#editComment(int, java.lang.String)
	 */
	@Override
	public void editComment(int ident, String content) {
		entityManager.createQuery("UPDATE Comment c SET c.content = :content WHERE c.ident = :ident")
				.setParameter("content", content).setParameter("ident", ident).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#getComment(int)
	 */
	@Override
	public Comment getComment(int ident) {
		return (Comment) entityManager.createQuery("SELECT c FROM Comment c WHERE c.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#getCommentsUser(java.lang.String)
	 */

	@Override
	public List<Comment> getCommentsUser(String userName) {
		return entityManager
				.createQuery("SELECT c FROM Comment c WHERE c.userName = :user_name ORDER BY c.createdOn DESC")
				.setParameter("user_name", userName).getResultList();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#getCommentsTopic(int)
	 */

	@Override
	public List<Comment> getCommentsTopic(int topicId) {
		return entityManager.createQuery("SELECT c FROM Comment c WHERE c.topicId = :topic_id ORDER BY c.createdOn ASC")
				.setParameter("topic_id", topicId).getResultList();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#getCommentCountForTopic(int)
	 */

	@Override
	public long getCommentCountForTopic(int topicId) {
		return (long) entityManager.createQuery("SELECT COUNT(c.ident)  FROM Comment c WHERE c.topicId = :topicId")
				.setParameter("topicId", topicId).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#getCommentCountForUser(java.lang.String)
	 */
	@Override
	public long getCommentCountForUser(String username) {
		return (long) entityManager.createQuery("SELECT COUNT(c.ident)  FROM Comment c WHERE c.userName = :username")
				.setParameter("username", username).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#getCommentCount()
	 */

	@Override
	public long getCommentCount() {
		return (long) entityManager.createQuery("SELECT COUNT(c.ident)  FROM Comment c").getSingleResult();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#lastCommented(int)
	 */

	@Override
	public Comment lastCommented(int topicId) {
		try {
			return (Comment) entityManager
					.createQuery("SELECT c FROM Comment c WHERE c.topicId = :topicId ORDER BY c.createdOn DESC")
					.setParameter("topicId", topicId).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#setCommentValue(int, int)
	 */

	@Override
	public void setCommentValue(int ident, int value) {
		entityManager.createQuery("UPDATE Comment c SET c.value = c.value + " + value + " WHERE c.ident = :ident")
				.setParameter("ident", ident).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see forum.service.CommentService#getTopCommentValue(int)
	 */

	@Override
	public int getTopCommentValue(int ident) {
		try {
			return (int) entityManager.createQuery("SELECT MAX(c.value) FROM Comment c WHERE c.topicId = :ident")
					.setParameter("ident", ident).getSingleResult();
		} catch (Exception e) {
			return 0;
		}
	}
}
