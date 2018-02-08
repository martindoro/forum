package forum.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

public class TopicTest {

	Topic topic = new Topic();

	@Test
	public void testGetIdent() {
		topic.setIdent(1);
		assertEquals(1, topic.getIdent());
	}

	@Test
	public void testSetIdent() {
		topic.setIdent(1);
		assertEquals(1, topic.getIdent());
	}

	@Test
	public void testGetUserName() {
		topic.setUserName("testUsername");
		assertEquals("testUsername", topic.getUserName());
	}

	@Test
	public void testSetUserName() {
		topic.setUserName("testUsername");
		assertEquals("testUsername", topic.getUserName());
	}

	@Test
	public void testGetCategoryId() {
		topic.setCategoryId(1);
		assertEquals(1, topic.getCategoryId());
	}

	@Test
	public void testSetCategoryId() {
		topic.setCategoryId(1);
		assertEquals(1, topic.getCategoryId());
	}

	@Test
	public void testGetContent() {
		topic.setContent("testContent");
		assertEquals("testContent", topic.getContent());
	}

	@Test
	public void testSetContent() {
		topic.setContent("testContent");
		assertEquals("testContent", topic.getContent());
	}

	@Test
	public void testGetCreatedOn() {
		Date date = new Date();
		date.setTime(0);
		topic.setCreatedOn(date);
		assertEquals(new Date(0), topic.getCreatedOn());
	}

	@Test
	public void testSetCreatedOn() {
		Date date = new Date();
		date.setTime(0);
		topic.setCreatedOn(date);
		assertEquals(new Date(0), topic.getCreatedOn());
	}

	@Test
	public void testIsLock() {
		assertEquals(false, topic.isLock());
	}

	@Test
	public void testSetLock() {
		topic.setLock(true);
		assertEquals(true, topic.isLock());
	}

}
