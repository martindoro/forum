package forum.server.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import forum.entity.ForumUser;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	ForumUser loggedPlayer = new ForumUser();
	UserController userController = new UserController();
	@Test
	public void testGetErrormsg() {
		userController.setErrormsg("test");
		assertEquals("test", userController.getErrormsg());
	}

	@Test
	public void testSetErrormsg() {
		userController.setErrormsg("test");
		assertEquals("test", userController.getErrormsg());
	}

	@Test
	public void testIsAdmin() {
		assertEquals(false, userController.isAdmin());
	}

	@Test
	public void testIsBan() {
		assertEquals(false, userController.isBan());
	}

	@Test
	public void testSetBan() {
		userController.setBan(true);
		assertEquals(true, userController.isBan());
	}

	@Test
	public void testIsLogged() {
		assertEquals(false, userController.isLogged());
	}

	@Test
	public void testGetLoginMsg() {
		userController.setLoginMsg("testMessage");
		assertEquals("testMessage", userController.getLoginMsg());
	}

	@Test
	public void testSetLoginMsg() {
		userController.setLoginMsg("testSet");
		assertEquals("testSet", userController.getLoginMsg());
	}

	@Test
	public void testGetLoggedPlayer() {
		assertEquals(null, userController.getLoggedPlayer());
	}

	@Test
	public void testSetLoggedPlayer() {
		userController.setLoggedPlayer(new ForumUser("login","password","email"));
		loggedPlayer = userController.getLoggedPlayer();
		assertEquals(loggedPlayer,userController.getLoggedPlayer());	
	}

	@Test
	public void testLoginForumUserModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister_sub() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoginModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testRhchange() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserSettingsChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserPassChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserBlock() {
		fail("Not yet implemented");
	}

}
