package forum.service;

import static org.junit.Assert.*;

import java.sql.SQLException;

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

	// public static final String SELECT_COMMAND = "SELECT * from forumuser where
	// login = '%s'";
/*	public static final String URL = "jdbc:postgresql://localhost/forum";
	public static final String USER = "postgres";
	public static final String PASSWORD = "Postgres1234";*/

	@Test
	public void testRegister() {
		ForumUser forumUser = new ForumUser("makollar","Start123","makollar@makollar.sk");
		//userService.register(new ForumUser("makollar","Start123","makollar@makollar.sk"));
	
		userService.register(forumUser);
		
		//assertEquals("makollar", userService.isPlayer("makollar"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testAddExtension() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAdmin() {
/*		// fail("Not yet implemented");

		
		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement statement = connection.createStatement();
			String INSERT_COMMAND1 = "Select admin from forum_user where login = 'admin'";
			
			// assertEquals(1, userServiceJPA.isAdmin("admin"));}
			assertEquals(1, statement.executeUpdate(INSERT_COMMAND1));

			//assertEquals(1, statement.executeQuery(userServiceJPA.isAdmin("admin")));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}*/
		
		//assertEquals(1, userServiceJPA.isAdmin("admin"));
		
		
		//userService.isAdmin("admin")
		assertEquals(false, userService.isAdmin("admin"));
		
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
		fail("Not yet implemented");
	}

	@Test
	public void testSetRights() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmailChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testPassChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForumUser() {
		fail("Not yet implemented");
	}

}
