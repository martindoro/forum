package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import forum.entity.Favorite;
import forum.server.controller.UserController;

@Transactional
public class FavoriteServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	private UserController userController;

	public void setFavorite(Favorite favorite) {
		try {
			entityManager
					.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", favorite.getUserName()).setParameter("commentId", favorite.getCommentId())
					.getSingleResult();
			entityManager
					.createQuery("DELETE FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", favorite.getUserName()).setParameter("commentId", favorite.getCommentId())
					.executeUpdate();
		} catch (NoResultException e) {
			entityManager.persist(favorite);
		}
	}

	public List<Favorite> getFavorites(String userName) {
		return entityManager.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName")
				.setParameter("username", userName).getResultList();
	}

	public boolean isFavorite(String user, int ident) {
		try {
			entityManager
					.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", user).setParameter("commentId", ident)
					.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	public Favorite getFavorite(String user, int ident) {
		try {
			return (Favorite) entityManager
					.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", user).setParameter("commentId", ident)
					.getSingleResult();
		} catch (Exception e) {
			return new Favorite(user, ident, 0);
		}
	}

	public void updateFavorite(String login, int ident, int value) {
		entityManager
				.createQuery("UPDATE Favorite f SET f.value = f.value + " + value
						+ " WHERE f.ident = :ident AND f.userName = :userName")
				.setParameter("ident", ident).setParameter("userName", login)
				.executeUpdate();
	}
}