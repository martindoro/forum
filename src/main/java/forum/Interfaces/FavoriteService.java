package forum.Interfaces;

import java.util.List;

import forum.entity.Favorite;

public interface FavoriteService {

	void setFavorite(Favorite favorite);

	List<Favorite> getFavorite(String username);

	boolean isFavorite(String userId, String commentId);
}