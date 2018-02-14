package forum.service;

import java.util.List;

import forum.entity.Favorite;

public interface FavoriteService {

	/**
	 * Adds new row(if not exists) to favorite table with corresponding parameters
	 * comment ident, user ident and favorite value +1 for thumbs up 0 for neutral
	 * -1 for thumbs down
	 * 
	 * @param favorite
	 *            Favorite object containing all variables
	 */
	void setFavorite(Favorite favorite);

	/**
	 * Returns complete list of favorited comments for current forum user with
	 * comment ident, user ident and a value
	 * 
	 * @param userName
	 *            forum user`s login for favorites database selection
	 * @return List of Favorites for current user
	 */
	List<Favorite> getFavorites(String userName);

	/**
	 * Returns a boolean if a comment has been favorited by user
	 * 
	 * @param user
	 *            user`s login for favorites database selection
	 * @param ident
	 *            comment ident for favorites database selection
	 * @return true if user has ever favorited this comment, false when not
	 */
	boolean isFavorite(String user, int ident);

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
	Favorite getFavorite(String user, int ident);

	/**
	 * After user clicking like or dislike on page for a comment, this method
	 * changes database entry with current value
	 * 
	 * @param ident
	 *            comment ident for Favorite entry selection
	 * @param value
	 *            new Favorite value to be set
	 */
	void updateFavorite(String user, int ident, int value);

}