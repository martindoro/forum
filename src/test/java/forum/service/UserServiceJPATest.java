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

import forum.entity.ForumUser;
import forum.server.ForumServerForTest;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class UserServiceJPATest {

/*	@PersistenceContext
	private EntityManager entityManager;*/
	
    @Autowired
    private EntityManager entityManager;
	
	@Autowired
	UserService userService;
	
	

	
		
	ForumUser forumUser = new ForumUser("ferko","Start123","ferko@forum.sk");
	ForumUser forumUser1 = new ForumUser("janko","Start123","janko@forum.sk");
	ForumUser forumUser2 = new ForumUser("katka","Start123","katka@forum.sk");

	// public static final String SELECT_COMMAND = "SELECT * from forumuser where
	// login = '%s'";
/*	public static final String URL = "jdbc:postgresql://localhost/forum";
	public static final String USER = "postgres";
	public static final String PASSWORD = "Postgres1234";*/

	@Test
	public void testRegister() {
	
	
		userService.register(forumUser);
		
		assertEquals(true, userService.isPlayer("ferko"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testAddExtension() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		userService.register(forumUser);
		assertEquals(true, userService.isPlayer("ferko"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAdmin() {
		// fail("Not yet implemented");

		
		userService.register(forumUser);
		userService.setRights("ferko", 1);
		
		
		//userService.isAdmin("admin")
		assertEquals(true, userService.isAdmin("ferko"));
		
	}

	@Test
	public void testIsPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsBan() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserCount() {
		userService.register(forumUser);
		userService.register(forumUser1);
		userService.register(forumUser2);
		assertEquals(3, userService.getUserCount());
		
		//fail("Not yet implemented");
	}

	@Test
	public void testSetRights() {
		userService.register(forumUser);
		
		userService.setRights("ferko", 1);
	
		assertEquals(true, userService.isAdmin("ferko"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmailChange() {
		userService.register(forumUser);
		
		userService.emailChange("ferko", "ferkomrkvicka@forum.sk");

		assertEquals("ferkomrkvicka@forum.sk", userService.login("ferko", "Start123").getEmail());
		
		
		//fail("Not yet implemented");
	}

	
	@Test
	//expect: old password is used for log in, log in method will return null in case that login and password don't match
	public void testPassChange() {
				
		userService.register(forumUser2);
		String pass = "Start321678";
			
		userService.passChange("katka", pass);
			
		assertEquals(null, userService.login(forumUser2.getLogin(), forumUser2.getPassword()));
		
	}

	@Test
	public void testGetForumUser() {
		fail("Not yet implemented");
	}

}
