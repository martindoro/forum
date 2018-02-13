package forum.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CategoryTest {

	Category category = new Category();

	@Test
	public void testSetIdent() {
		category.setIdent(1);
		assertEquals(1, category.getIdent());
	}

	@Test
	public void testGetIdent() {
		category.setIdent(1);
		assertEquals(1, category.getIdent());
	}

	@Test
	public void testGetContent() {
		category.setContent("other");
		assertEquals("other", category.getContent());
	}

	@Test
	public void testSetContent() {
		category.setContent("other");
		assertEquals("other", category.getContent());

	}

}
