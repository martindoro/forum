package forum.service.interfaces;

import java.util.List;

import forum.entity.Comment;

public interface CommentService {

	void addComment(Comment comment);

	/**
	 * Content of existed commented can be edited.
	 * 
	 * @param ident
	 *            ID of particular comment for database selection
	 * @param content
	 *            particular comment which will be edited
	 */
	void editComment(int ident, String content);

	/**
	 * Get particular comment based on ID
	 * 
	 * @param ident
	 *            ID of this comment for selection from database
	 * @return content of particular single comment
	 */
	Comment getComment(int ident);

	/**
	 * Get list of comments for particular user ordered by descending date of
	 * creation
	 * 
	 * @param userName
	 *            name of logged user
	 * @return list of users comments
	 */

	List<Comment> getCommentsUser(String userName);

	/**
	 * Get list of all comments for topic
	 * 
	 * @param topicId
	 *            topic ID for database selection
	 * @return all comments for topic in ascending order
	 */

	List<Comment> getCommentsTopic(int topicId);

	/**
	 * Count total comments in topic
	 * 
	 * @param topicId
	 *            ID of topic for database selection
	 * @return number of total comments in topic
	 */

	long getCommentCountForTopic(int topicId);

	/**
	 * Count total comments for user
	 * 
	 * @param username
	 *            username for database selection
	 * @return number of total comments for user
	 */
	long getCommentCountForUser(String username);

	/**
	 * Count total comments in forum
	 * 
	 * @return number of total comments
	 */

	long getCommentCount();

	/**
	 * Return information about last comment in every topic
	 * 
	 * @param topicId
	 *            ID of topic for database selection
	 * @return user and time and date for last comment in every topic
	 */

	Comment lastCommented(int topicId);

	/**
	 * Update value of comment
	 * 
	 * @param ident
	 *            ID of comment for database selection
	 * @param value
	 *            new value set by user
	 */

	void setCommentValue(int ident, int value);

	/**
	 * Get most popular comment which has most likes/highest value
	 * 
	 * @param ident
	 *            ID of topic for selection from database
	 * @return comment with highest value
	 */

	int getTopCommentValue(int ident);

}