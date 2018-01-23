package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import forum.entity.Favorite;

@Transactional
public class FavoriteServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	public void setFavorite(Favorite favorite) {
		try {
			entityManager.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", favorite.getUserName()).setParameter("commentId", favorite.getCommentId())
					.getSingleResult();
			entityManager.createQuery("DELETE FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", favorite.getUserName()).setParameter("commentId", favorite.getCommentId())
					.executeUpdate();
		} catch (NoResultException e) {
			entityManager.persist(favorite);
		}
	}

	public List<Favorite> getFavorite(String userName) {
		return entityManager.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName")
				.setParameter("username", userName).getResultList();
	}

	public boolean isFavorite(Favorite favorite) {
		try {
			entityManager.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", favorite.getUserName()).setParameter("commentId", favorite.getCommentId())
					.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}