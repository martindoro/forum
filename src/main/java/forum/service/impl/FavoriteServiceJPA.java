package forum.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import forum.entity.Favorite;
import forum.entity.ForumUser;
import forum.service.FavoriteService;
import forum.service.UserService;

@Transactional
public class FavoriteServiceJPA implements FavoriteService {

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see forum.service.FavoriteService#setFavorite(forum.entity.Favorite)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see forum.service.FavoriteService#getFavorites(java.lang.String)
	 */
	@Override
	public List<Favorite> getFavorites(String userName) {
		return entityManager.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName")
				.setParameter("userName", userName).getResultList();
	}

	/* (non-Javadoc)
	 * @see forum.service.FavoriteService#isFavorite(java.lang.String, int)
	 */
	@Override
	public boolean isFavorite(String user, int ident) {
		try {
			entityManager
					.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", user).setParameter("commentId", ident).getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see forum.service.FavoriteService#getFavorite(java.lang.String, int)
	 */
	@Override
	public Favorite getFavorite(String user, int ident) {
		try {
			return (Favorite) entityManager
					.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", user).setParameter("commentId", ident).getSingleResult();
		} catch (Exception e) {
			return new Favorite(user, ident, 0);
		}
	}

	/* (non-Javadoc)
	 * @see forum.service.FavoriteService#updateFavorite(java.lang.String, int, int)
	 */
	@Override
	public void updateFavorite(String user, int ident, int val) {
		Favorite fav;
		try {
			fav = (Favorite) entityManager.createQuery("SELECT f FROM Favorite f WHERE f.ident = :ident").setParameter("ident", ident).getSingleResult();
		} catch (Exception e) {
			fav = new Favorite(user, ident, val);
		}
		fav.setValue(fav.getValue() + val);
	}
}