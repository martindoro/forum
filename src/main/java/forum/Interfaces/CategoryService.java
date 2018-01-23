package forum.Interfaces;

import java.util.List;

import forum.entity.Category;


public interface CategoryService {
	void addCategory(Category category);
	List<Category> getCategories();
}
