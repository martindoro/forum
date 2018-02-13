package forum.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.entity.Comment;
import forum.server.ForumServerForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class CommentServiceJPATest {

	@Autowired
	private CommentService commentService;
	Comment comment = new Comment("testuser", 1, "test");

	@Before
	public void setup() {
		commentService.addComment(comment);
	}

	@Test
	public void testAddComment() {
		commentService.addComment(comment);
		assertEquals(comment, commentService.getComment(comment.getIdent()));
	}

	@Test
	public void testEditComment() {
		commentService.editComment(comment.getIdent(), "edit");
		assertEquals("edit", commentService.getComment(comment.getIdent()).getContent());
	}

	@Test
	public void testGetCommentsUser() {
		assertEquals(comment, commentService.getCommentsUser("testuser").get(0));
	}

	@Test
	public void testGetCommentsTopic() {
		assertEquals("test", commentService.getCommentsTopic(1).get(0).getContent());

	}

	@Test
	public void testGetCommentCountForTopic() {
		assertEquals(1, commentService.getCommentCountForTopic(1));
	}

	@Test
	public void testGetCommentCountForUser() {
		assertEquals(1, commentService.getCommentCountForUser("testuser"));
	}

	@Test
	public void testGetCommentCount() {
		assertEquals(1, commentService.getCommentCount());
	}

	@Test
	public void testLastCommented() {
		assertEquals("test", commentService.lastCommented(1).getContent());
	}

	@Test
	public void testSetCommentValue() {
		commentService.setCommentValue(comment.getIdent(), 20);
		assertEquals(20, comment.getValue());

	}

	@Test
	public void testGetTopCommentValue() {
		commentService.setCommentValue(comment.getIdent(), 25);
		System.err.println(comment.getIdent());
		assertEquals(25, commentService.getTopCommentValue(1));
	}

}
