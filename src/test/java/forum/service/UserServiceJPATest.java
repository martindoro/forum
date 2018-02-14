package forum.service;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.entity.ForumUser;
import forum.server.ForumServerForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class UserServiceJPATest {

	/*
	 * @PersistenceContext private EntityManager entityManager;
	 */

	@Autowired
	private EntityManager entityManager;

	@Autowired
	UserService userService;

	ForumUser forumUser = new ForumUser("ferko", "Start123", "ferko@forum.sk");
	ForumUser forumUser1 = new ForumUser("janko", "Start123", "janko@forum.sk");
	ForumUser forumUser2 = new ForumUser("katka", "Start123", "katka@forum.sk");

	@Before
	public void setUp() {
		userService.register(forumUser);
		userService.register(forumUser1);
		userService.register(forumUser2);
	}

	@Test
	public void testRegister() {
		userService.register(forumUser);
		assertEquals(true, userService.isPlayer("ferko"));
	}

	@Test
	public void testLogin() {
		userService.register(forumUser);
		assertEquals(true, userService.isPlayer("ferko"));
	}

	@Test
	public void testGetImage() {
		assertEquals(null, userService.getImage(forumUser.getLogin()));

	}

	@Test
	public void testIsAdmin() {
		userService.register(forumUser);
		userService.setRights("ferko", 1);
		assertEquals(true, userService.isAdmin("ferko"));

	}

	@Test
	public void testIsPlayer() {

		userService.isPlayer(forumUser.getLogin());
		assertEquals("ferko", forumUser.getLogin());
	}

	@Test
	public void testIsBan() {
		userService.setRights(forumUser.getLogin(), -1);
		assertEquals(true, userService.isBan(forumUser.getLogin()));
	}

	@Test
	public void testGetUserCount() {
		assertEquals(3, userService.getUserCount());

	}

	@Test
	public void testSetRights() {
		userService.register(forumUser);
		userService.setRights("ferko", 1);
		assertEquals(true, userService.isAdmin("ferko"));

	}

	@Test
	public void testEmailChange() {
		userService.register(forumUser);

		userService.emailChange("ferko", "ferkomrkvicka@forum.sk");

		assertEquals("ferkomrkvicka@forum.sk", userService.login("ferko", "Start123").getEmail());

	}

	@Test
	// expect: old password is used for log in, log in method will return null in
	// case that login and password don't match
	public void testPassChange() {

		userService.register(forumUser2);
		String pass = "Start321678";

		userService.passChange("katka", pass);

		assertEquals(null, userService.login(forumUser2.getLogin(), forumUser2.getPassword()));

	}

	@Test
	public void testGetForumUser() {
		assertEquals("katka", forumUser2.getLogin());

	}

}
