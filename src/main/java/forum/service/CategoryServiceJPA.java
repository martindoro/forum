package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import forum.entity.Category;

@Transactional
public class CategoryServiceJPA {
	@PersistenceContext
	private EntityManager entityManager;

	public void addCategory(Category category) {
		entityManager.persist(category);
	}

	/**
	 * List of all created categories in forum
	 * 
	 * @return list of categories names or null
	 */
	public List<Category> getCategory() {
		try {
			return entityManager.createQuery("SELECT c FROM Category c ").getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Deletion of existed category from database.
	 * 
	 * @param ident
	 *            ID of category for selection from database
	 */
	public void removeCategory(int ident) {
		entityManager.createQuery("DELETE FROM Category c WHERE c.ident = :ident").setParameter("ident", ident)
				.executeUpdate();
	}

	/**
	 * Get name of particular category
	 * 
	 * @param ident
	 *            ID of category for selection from database
	 * @return category name
	 */

	public String getContentById(int ident) {
		return (String) entityManager.createQuery("SELECT c.content FROM Category c WHERE c.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}
}
