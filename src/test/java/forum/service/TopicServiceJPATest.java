package forum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.entity.Topic;
import forum.server.ForumServerForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class TopicServiceJPATest {

	@Autowired
    private EntityManager entityManager;
	
	@Autowired
	TopicService topicService;
	
	Topic topic = new Topic("ferko", 1, "Test content");
	Topic topic1 = new Topic("jurko", 2, "Test content");
	Topic topic2 = new Topic("katka", 1, "Test content");
	@Before
	public void setUp() {
		topicService.addTopic(topic);
		topicService.addTopic(topic1);
		topicService.addTopic(topic2);
	}
	@Test
	public void testAddTopic() {
		topicService.addTopic(topic);
		assertEquals("Test content", topicService.getTopicList(1).get(0).getContent());
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetTopicList() {
		
		assertEquals("ferko", topicService.getTopicList(1).get(0).getUserName());
		//assertEquals(2, topicService.getTopicCount());
		//fail("Not yet implemented");
	}

	@Test(expected = NoResultException.class)
	public void testRemoveTopic() {
		//fail("Not yet implemented");
		topicService.removeTopic(topic.getIdent());
		assertEquals(null, topicService.getContentById(topic.getIdent()));
	
	}

	@Test
	public void testGetTopicCountForCategory() {
		//fail("Not yet implemented");
		assertEquals(2, topicService.getTopicCountForCategory(1));
	}

	@Test
	public void testGetTopicCount() {
		//fail("Not yet implemented");
		assertEquals(3, topicService.getTopicCount());
	}

	@Test
	public void testGetContentById() {
		//fail("Not yet implemented");
		assertEquals("Test content", topicService.getContentById((topic.getIdent())));
	}

	@Test
	public void testGetTopicState() {
		//fail("Not yet implemented");
		topicService.setTopicState(topic.getIdent(), false);
		assertEquals(false, topicService.getTopicState(topic.getIdent()));
	}

	@Test
	public void testSetTopicState() {
		//fail("Not yet implemented");
		topicService.setTopicState(topic.getIdent(), true);
		assertEquals(true, topicService.getTopicState(topic.getIdent()));
	}

}
