package forum.service;

import static org.junit.Assert.assertEquals;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.entity.Category;
import forum.server.ForumServerForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class CategoryServiceJPATest {

	private Category category = new Category("other");
	private Category category2 = new Category("software");

	@Autowired
	private CategoryService categoryService;

	@Before
	public void items() {
		categoryService.addCategory(category);
		categoryService.addCategory(category2);

	}

	@Test
	public void testAddCategory() {
		assertEquals("other", categoryService.getContentById(1));
	}

	@Test
	public void testGetCategory() {
	assertEquals("other",categoryService.getCategory().get(0).getContent());
	assertEquals("software",categoryService.getCategory().get(1).getContent());

	}

	@Test(expected = NoResultException.class)
	public void testRemoveCategory() {
		categoryService.removeCategory(category.getIdent());
		assertEquals("", categoryService.getContentById(category.getIdent()));
	}

	@Test
	public void testGetContentById() {
		categoryService.addCategory(category);
		assertEquals("other", categoryService.getContentById(category.getIdent()));
		assertEquals("software", categoryService.getContentById(category2.getIdent()));
		
	}

}
