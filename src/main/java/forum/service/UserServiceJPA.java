package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import forum.entity.ForumUser;

@Transactional
@Repository

public class UserServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	public void register(ForumUser user) {
		if(isValid(user.getPassword()))
				entityManager.persist(user);
	}
	/**
	 * Database extension needed for password hashing
	 * extension will be set once as database table has been created
	 */
	public void addExtension() {
		entityManager.createNativeQuery("CREATE EXTENSION IF NOT EXISTS pgcrypto").executeUpdate();
	}
	
	
	/**
	 * Login Method for user login, method will select user and hashed password from database
	 * @param login - user login to be selected 
	 * @param password - user password to be selected
	 * @return Selected user name and password
	 */
	public ForumUser login(String login, String password) {
		try {
			return (ForumUser) entityManager.createQuery(
					"SELECT fu FROM ForumUser fu WHERE fu.login =:login AND fu.password = crypt(:password, fu.password)")
					.setParameter("login", login).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Method for selecting user profile picture from database
	 * @param login - user login to be used for selecting specific profile picture
	 * @return - profile picture for specific user
	 */
	public byte[] getImage(String login) {
		try {
			return (byte[]) entityManager.createQuery("SELECT fu.pic FROM ForumUser fu WHERE fu.login =:login")
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	/**
	 * isAdmin method to select only users with admin rights(needed for specific functions)
	 * @param login - user login to be used for selecting user rights from database
	 * @return - true or false if logged user have admin rights
	 */
	public boolean isAdmin(String login) {
		try {
			entityManager.createQuery("SELECT fu FROM ForumUser fu WHERE fu.admin =:admin AND fu.login =:login")
					.setParameter("admin", 1).setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

	/**
	 * isPlayer Method is to get login data for specific user from database
	 * @param login - selecting specific user by login
	 * @return - login of user name
	 */
	public boolean isPlayer(String login) {
		try {
			entityManager.createQuery("SELECT fu FROM ForumUser fu WHERE fu.login =:login").setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * isBan Method is selecting users with BAN activated 
	 * @param login - selecting users by login
	 * @return - users with ban activated
	 */
	public boolean isBan(String login) {
		try {
			entityManager.createQuery("SELECT fu FROM ForumUser fu WHERE fu.admin =:admin AND fu.login =:login")
					.setParameter("admin", -1).setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

	
	public long getUserCount() {
		return (long) entityManager.createQuery("SELECT COUNT(fu) FROM ForumUser fu").getSingleResult();
	}

	/**
	 * Method for changing user rights(Admin/user)
	 * @param login - to change/update specific user 
	 * @param value - to set specific value (admin/user)
	 */
	public void setRights(String login, int value) {
		entityManager.createQuery("UPDATE ForumUser fu SET fu.admin =:admin WHERE fu.login = :login")
				.setParameter("admin", value).setParameter("login", login).executeUpdate();

	}

	/**
	 * Method for updating/changing profile picture from user profile
	 * @param login - to change profile picture for specific login
	 * @param pic - picture to be uploaded
	 */
	public void updateImage(String login, byte[] pic) {
		entityManager.createQuery("UPDATE ForumUser fu SET fu.pic =:pic WHERE fu.login = :login")
				.setParameter("pic", pic).setParameter("login", login).executeUpdate();
	}

	/**
	 * Method for email change, email can be changed by admin from admin zone or by user from user profile
	 * @param login - username of wich user mail have to be edited
	 * @param email - new email address
	 */
	public void emailChange(String login, String email) {
		if (!email.isEmpty()) {
			entityManager.createQuery("UPDATE ForumUser fu SET fu.email =:email WHERE fu.login = :login")
					.setParameter("email", email).setParameter("login", login).executeUpdate();
		}
	}

	/**
	 * Method for password change, admin can change password for all users or users can update their password from user profile page.
	 * @param login - of which user password have to be changed
	 * @param password - new password
	 */
	public void passChange(String login, String password) {
		if (!password.isEmpty() && isValid(password)) {
			entityManager.createQuery(
					"UPDATE ForumUser fu SET fu.password =crypt(:password, fu.password) WHERE fu.login = :login")
					.setParameter("password", password).setParameter("login", login).executeUpdate();
		}
	}

	/**
	 * Method will return list of all registered users
	 * @return - list of all registered users
	 */
	public List<ForumUser> getForumUser() {
		return entityManager.createQuery("SELECT fu FROM ForumUser fu ").getResultList();
	}
	
	/**
	 * Method for password validation on back-end side. 
	 * At least one Number
	 * At least one Lower Case letter
	 * At least one Upper Case letter
	 * Minimal length 6 characters
	 * Maximal length 255 characters 
	 * @param password to be set
	 * @return true of false, if the password is matching with Regular Expression
	 */
	private boolean isValid(String password) {
		return password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,255})");
	}
}