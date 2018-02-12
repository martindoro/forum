package forum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.entity.Favorite;
import forum.server.ForumServerForTest;
import forum.service.interfaces.FavoriteService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class FavoriteServiceJPATest {

	@Autowired 
	private EntityManager entityManager;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Before
	public void setup() {
	Favorite f1 = new Favorite("aaa", 15, 1);
	Favorite f2 = new Favorite("bbb", 16, 0);
	Favorite f3 = new Favorite("ccc", 17, -1);
	Favorite f4 = new Favorite("ddd", 18, 1);
	Favorite f5 = new Favorite("eee", 19, 0);
	favoriteService.setFavorite(f1);
	favoriteService.setFavorite(f2);
	favoriteService.setFavorite(f3);
	favoriteService.setFavorite(f4);
	favoriteService.setFavorite(f5);
	}
	
	@Test
	public void testSetFavorite() {
		assertEquals(15, favoriteService.getFavorite("forumUser", 15).getCommentId());
	}

	@Test
	public void testGetFavorites() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsFavorite() {
		assertEquals(false, favoriteService.isFavorite("pako", 123));
	}

	@Test
	public void testGetFavorite() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateFavorite() {
		fail("Not yet implemented");
	}

}
