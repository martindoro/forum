package forum.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

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
		File file = new File("");

		byte[] picInBytes = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(picInBytes);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPic(picInBytes);
		entityManager.persist(user);
	}

	public ForumUser login(String login, String password) {
		try {
			return (ForumUser) entityManager
					.createQuery("SELECT fu FROM ForumUser fu WHERE fu.login =:login AND fu.password =:password")
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

	public long getUserCount() {
		return (long) entityManager.createQuery("SELECT COUNT(fu) FROM ForumUser fu").getSingleResult();
	}
}
