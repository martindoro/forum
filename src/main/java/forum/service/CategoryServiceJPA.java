package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import forum.entity.Category;

@Transactional
public class CategoryServiceJPA {
	@PersistenceContext
	private EntityManager entityManager;

	public void addCategory(Category category) {
		entityManager.persist(category);
	}

	public List<Category> getCategory() {
//		  try {
//		   setCategories();
//		    } catch( SQLException e) { return
//		  entityManager.createQuery("SELECT c FROM Category c").getResultList(); }
//		 
		return entityManager.createQuery("SELECT c FROM Category c ").getResultList();
	}

	public void removeCategory(int ident) {
		entityManager.createQuery("DELETE FROM Category c WHERE c.ident = :ident").setParameter("ident", ident)
				.executeUpdate();
	}
	
	public String getContentById(int ident) {
		return (String) entityManager.createQuery("SELECT c.content FROM Category c WHERE c.ident = :ident").setParameter("ident", ident).getSingleResult();
	}
}
