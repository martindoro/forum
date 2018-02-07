package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import forum.entity.Favorite;

@Transactional
public class FavoriteServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Adds new row(if not exists) to favorite table with corresponding parameters
	 * comment ident, user ident and favorite value +1 for thumbs up 0 for neutral
	 * -1 for thumbs down
	 * 
	 * @param favorite
	 *            Favorite object containing all variables
	 */
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

	/**
	 * Returns complete list of favorited comments for current forum user with
	 * comment ident, user ident and a value
	 * 
	 * @param userName
	 *            forum user`s login for favorites database selection
	 * @return List of Favorites for current user
	 */
	public List<Favorite> getFavorites(String userName) {
		return entityManager.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName")
				.setParameter("username", userName).getResultList();
	}

	/**
	 * Returns a boolean if a comment has been favorited by user
	 * 
	 * @param user
	 *            user`s login for favorites database selection
	 * @param ident
	 *            comment ident for favorites database selection
	 * @return true if user has ever favorited this comment, false when not
	 */
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

	/**
	 * Returns a Favorite object for current user and comment
	 * 
	 * @param user
	 *            current user`s login for database selection
	 * @param ident
	 *            comment ident for database selection
	 * @return Favorite object when there is database entry, new neutral Favorite
	 *         object when not
	 */
	public Favorite getFavorite(String user, int ident) {
		try {
			return (Favorite) entityManager
					.createQuery("SELECT f FROM Favorite f WHERE f.userName = :userName AND f.commentId = :commentId")
					.setParameter("userName", user).setParameter("commentId", ident).getSingleResult();
		} catch (Exception e) {
			return new Favorite(user, ident, 0);
		}
	}

	/**
	 * After user clicking like or dislike on page for a comment, this method
	 * changes database entry with current value
	 * 
	 * @param login
	 *            user`s login for Favorite entry selection
	 * @param ident
	 *            comment ident for Favorite entry selection
	 * @param value
	 *            new Favorite value to be set
	 */
	public void updateFavorite(String login, int ident, int value) {
		entityManager
				.createQuery("UPDATE Favorite f SET f.value = f.value + " + value
						+ " WHERE f.ident = :ident AND f.userName = :userName")
				.setParameter("ident", ident).setParameter("userName", login).executeUpdate();
	}
}