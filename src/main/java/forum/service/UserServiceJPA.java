package forum.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import forum.entity.ForumUser;

@Transactional
public class UserServiceJPA {

	@PersistenceContext
	private EntityManager entityManager;

	public void register(ForumUser user) {
		entityManager.persist(user);
	}

	public ForumUser login(String login, String password) {
		try {
			return (ForumUser) entityManager
					.createQuery("SELECT fu FROM ForumUser fu WHERE fu.login =:login AND fu.password = crypt(:password, fu.password)")
					.setParameter("login", login).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public byte[] getImage(String login) {
		try {
			return (byte[]) entityManager.createQuery("SELECT fu.pic FROM ForumUser fu WHERE fu.login =:login")
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean isAdmin(String login) {
		try {
			entityManager.createQuery("SELECT fu FROM ForumUser fu WHERE fu.admin =:admin AND fu.login =:login")
					.setParameter("admin", 1).setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}
	
	public boolean isPlayer(String login) {
		try {
			entityManager.createQuery("SELECT fu FROM ForumUser fu WHERE fu.login =:login")
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

	public long getUserCount() {
		return (long) entityManager.createQuery("SELECT COUNT(fu) FROM ForumUser fu").getSingleResult();
	}
	
	public void setAdmin(String login) {
		entityManager
				.createQuery("UPDATE ForumUser fu SET fu.admin =:admin WHERE fu.login = :login")
				.setParameter("admin", 1).setParameter("login", login)
				.executeUpdate();
	
	}
	
	public void setUser(String login) {
		entityManager
				.createQuery("UPDATE ForumUser fu SET fu.admin =:admin WHERE fu.login = :login")
				.setParameter("admin", 0).setParameter("login", login)
				.executeUpdate();	
	}
	public void updateImage(String login, byte[] pic) {
		entityManager
				.createQuery("UPDATE ForumUser fu SET fu.pic =:pic WHERE fu.login = :login")
				.setParameter("pic", pic).setParameter("login", login)
				.executeUpdate();	
	}
	
	public void emailChange(String login, String email) {
		if (!email.isEmpty()) {
		entityManager
				.createQuery("UPDATE ForumUser fu SET fu.email =:email WHERE fu.login = :login")
				.setParameter("email", email).setParameter("login", login)
				.executeUpdate();
		}
	}
	
	public void passChange(String login, String password) {
		if (!password.isEmpty()) {
		entityManager
				.createQuery("UPDATE ForumUser fu SET fu.password =crypt(:password, fu.password) WHERE fu.login = :login")
				.setParameter("password", password).setParameter("login", login)
				.executeUpdate();
		}
	}
}
