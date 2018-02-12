package forum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.entity.Comment;
import forum.server.ForumServerForTest;
import forum.service.interfaces.CommentService;
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class CommentServiceJPATest {

	@Autowired
	private CommentService commentService;
	@Test
	public void testAddComment() {
		Comment comment = new Comment("testuser", 1,"test");
		commentService.addComment(comment);
		assertEquals(comment, commentService.getComment(1));
	}

	@Test
	public void testEditComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommentsUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommentsTopic() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommentCountForTopic() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommentCountForUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommentCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastCommented() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCommentValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTopCommentValue() {
		fail("Not yet implemented");
	}

}
