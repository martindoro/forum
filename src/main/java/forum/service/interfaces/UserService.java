package forum.service.interfaces;

import java.util.List;

import forum.entity.ForumUser;

public interface UserService {

	void register(ForumUser user);

	/**
	 * Database extension needed for password hashing
	 * extension will be set once as database table has been created
	 */
	void addExtension();

	/**
	 * Login Method for user login, method will select user and hashed password from database
	 * @param login - user login to be selected 
	 * @param password - user password to be selected
	 * @return Selected user name and password
	 */
	ForumUser login(String login, String password);

	/**
	 * Method for selecting user profile picture from database
	 * @param login - user login to be used for selecting specific profile picture
	 * @return - profile picture for specific user
	 */
	byte[] getImage(String login);

	/**
	 * isAdmin method to select only users with admin rights(needed for specific functions)
	 * @param login - user login to be used for selecting user rights from database
	 * @return - true or false if logged user have admin rights
	 */
	boolean isAdmin(String login);

	/**
	 * isPlayer Method is to get login data for specific user from database
	 * @param login - selecting specific user by login
	 * @return - login of user name
	 */
	boolean isPlayer(String login);

	/**
	 * isBan Method is selecting users with BAN activated 
	 * @param login - selecting users by login
	 * @return - users with ban activated
	 */
	boolean isBan(String login);

	long getUserCount();

	/**
	 * Method for changing user rights(Admin/user)
	 * @param login - to change/update specific user 
	 * @param value - to set specific value (admin/user)
	 */
	void setRights(String login, int value);

	/**
	 * Method for updating/changing profile picture from user profile
	 * @param login - to change profile picture for specific login
	 * @param pic - picture to be uploaded
	 */
	void updateImage(String login, byte[] pic);

	/**
	 * Method for email change, email can be changed by admin from admin zone or by user from user profile
	 * @param login - username of wich user mail have to be edited
	 * @param email - new email address
	 */
	void emailChange(String login, String email);

	/**
	 * Method for password change, admin can change password for all users or users can update their password from user profile page.
	 * @param login - of which user password have to be changed
	 * @param password - new password
	 */
	void passChange(String login, String password);

	/**
	 * Method will return list of all registered users
	 * @return - list of all registered users
	 */
	List<ForumUser> getForumUser();

}