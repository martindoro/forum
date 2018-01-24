package forum.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.postgresql.util.PSQLException;

import forum.entity.Category;

@Transactional
public class CategoryServiceJPA {
	@PersistenceContext
	private EntityManager entityManager;

	public void addCategory(Category category) {
		entityManager.persist(category);
	}

	public List<Category> getCategory() {
		/* try {
		 setCategories();
		 } catch( SQLException e) {
		 return entityManager.createQuery("SELECT c FROM Category c").getResultList();
		 }*/
		return entityManager.createQuery("SELECT c FROM Category c ").getResultList();
	}

	private void removeCategory(int ident) {
		entityManager.createQuery("DELETE FROM Category c WHERE c.ident = :ident").setParameter("ident", ident)
				.executeUpdate();
	}

	public void setCategories() throws PSQLException {
		Category category = new Category();
		category.setContent("Hardware");
		addCategory(category);
		Category category1 = new Category();
		category1.setContent("Software");
		addCategory(category1);
		Category category2 = new Category();
		category2.setContent("Other");
		addCategory(category2);

	}

}
