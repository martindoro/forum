package forum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
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

	@Before
	public void setUp() {
		userService.register(forumUser);
		userService.register(forumUser1);
		userService.register(forumUser2);
	}
	
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
	public void testLogin() {
		userService.register(forumUser);
		assertEquals(true, userService.isPlayer("ferko"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetImage() {
		
		//userService.getImage(forumUser.getLogin());
		//System.err.println(userService.getImage(forumUser.getLogin()));
		assertEquals(null, userService.getImage(forumUser.getLogin()));
		//fail("Not yet implemented");
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
		
		userService.isPlayer(forumUser.getLogin());
		assertEquals("ferko", forumUser.getLogin());
		//fail("Not yet implemented");
	}

	@Test
	public void testIsBan() {
		userService.setRights(forumUser.getLogin(), -1);
		assertEquals(true, userService.isBan(forumUser.getLogin()));
		//fail("Not yet implemented");
	}

	@Test
	public void testGetUserCount() {
	
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

	//@Test(expected = NoResultException.class)
	@Test
	public void testUpdateImage() throws IOException{
		//URL url = getClass().getResource("/Users/matuskollar/Desktop/white-cogwheel.png");
		//File file = new File(url.getPath());
		//System.out.println(url);
	//	System.err.println(file.getAbsolutePath());
		
		
		//System.err.println(file.exists());
		
		//System.err.println(file.isFile());
		//File file = new File(url.getPath());
		
		
	/*	BufferedImage img = null;
		File imgPath = new File("/Users/matuskollar/Desktop/white-cogwheel.png");
		//BufferedImage bufferedImage = null;
		 BufferedImage bufferedImage = ImageIO.read(imgPath);
		//img = ImageIO.read(new File("/Users/matuskollar/Desktop/white-cogwheel.png"));
   // BufferedImage bufferedImage = ImageIO.read(img);
		//BufferedImage bufferedImage =
		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage .getRaster();
		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
	
		System.err.println(data.getData());*/
		
		//userService.updateImage(forumUser.getLogin(), pic);
		
		File fnew=new File("/Users/matuskollar/Desktop/white-cogwheel.png");
		BufferedImage originalImage=ImageIO.read(fnew);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "png", baos );
		byte[] imageInByte=baos.toByteArray();
		System.err.println(imageInByte.length);
		System.out.println(imageInByte);
		userService.updateImage(forumUser.getLogin(), imageInByte);
		//userService.updateImage(forumUser.getLogin(), imageInByte);
		//userService.updateImage(forumUser.getLogin(), imageInByte);
		
		//assertEquals(0, userService.getImage(forumUser.getLogin()));
		
	//	System.err.println(img.getHeight());
		//userService.updateImage(forumUser.getLogin(), img);
		
		//	fail("Not yet implemented");
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
		
		assertEquals("katka", forumUser2.getLogin());
		//fail("Not yet implemented");
	}

}
