package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import forum.entity.Favorite;

public class FavoriteServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	public void setFavorite(Favorite favorite) {
		try {
			entityManager.createQuery("SELECT f FROM Favorite f WHERE f.user_name = :user_name AND f.comment_id = :comment_id")
					.setParameter("user_name", favorite.getUserName()).setParameter("comment_id", favorite.getCommentId())
					.getSingleResult();
			entityManager.createQuery("DELETE FROM Favorite f WHERE f.user_name = :user_name AND f.comment_id = :comment_id")
					.setParameter("user_name", favorite.getUserName()).setParameter("comment_id", favorite.getCommentId())
					.executeUpdate();
		} catch (NoResultException e) {
			entityManager.persist(favorite);
		}
	}

	public List<Favorite> getFavorite(String userName) {
		return entityManager.createQuery("SELECT f FROM Favorite f WHERE f.user_name = :user_name")
				.setParameter("username", userName).getResultList();
	}

	public boolean isFavorite(Favorite favorite) {
		try {
			entityManager.createQuery("SELECT f FROM Favorite f WHERE f.user_name = :user_name AND f.comment_id = :comment_id")
					.setParameter("user_name", favorite.getUserName()).setParameter("comment_id", favorite.getCommentId())
					.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}