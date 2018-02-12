package forum.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import forum.entity.Category;
import forum.service.CategoryService;

@Transactional
public class CategoryServiceJPA implements CategoryService {
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see forum.service.CategoryService#addCategory(forum.entity.Category)
	 */
	@Override
	public void addCategory(Category category) {
		entityManager.persist(category);
	}

	/* (non-Javadoc)
	 * @see forum.service.CategoryService#getCategory()
	 */
	@Override
	public List<Category> getCategory() {
		try {
			return entityManager.createQuery("SELECT c FROM Category c ").getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see forum.service.CategoryService#removeCategory(int)
	 */
	@Override
	public void removeCategory(int ident) {
		entityManager.createQuery("DELETE FROM Category c WHERE c.ident = :ident").setParameter("ident", ident)
				.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see forum.service.CategoryService#getContentById(int)
	 */

	@Override
	public String getContentById(int ident) {
		return (String) entityManager.createQuery("SELECT c.content FROM Category c WHERE c.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}
}
