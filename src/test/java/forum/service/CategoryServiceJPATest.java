package forum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.entity.Category;
import forum.server.ForumServerForTest;
import forum.service.interfaces.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class CategoryServiceJPATest {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void testAddCategory() {
		Category category = new Category("other");
		categoryService.addCategory(category);
		category.setIdent(1);
		assertEquals("other", categoryService.getContentById(1));
	}

	@Test
	public void testGetCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetContentById() {
		fail("Not yet implemented");
	}

}
