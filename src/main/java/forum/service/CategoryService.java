package forum.service;

import java.util.List;

import forum.entity.Category;

public interface CategoryService {

	void addCategory(Category category);

	/**
	 * List of all created categories in forum
	 * 
	 * @return list of categories names or null
	 */
	List<Category> getCategory();

	/**
	 * Deletion of existed category from database.
	 * 
	 * @param ident
	 *            ID of category for selection from database
	 */
	void removeCategory(int ident);

	/**
	 * Get name of particular category
	 * 
	 * @param ident
	 *            ID of category for selection from database
	 * @return category name
	 */

	String getContentById(int ident);

}