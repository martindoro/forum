package forum.Interfaces;

import java.util.List;

import forum.entity.Comment;



public interface CommentService {
	void addComment(Comment comment);
	List<Comment> getComments(String game);
}
