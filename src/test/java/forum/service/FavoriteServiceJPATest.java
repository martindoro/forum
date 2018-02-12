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

import forum.entity.Favorite;
import forum.server.ForumServer;
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
	
	@Test
	public void testSetFavorite() {
		Favorite fav = new Favorite("forumUser", 15);
		favoriteService.setFavorite(fav);
		assertEquals(fav, favoriteService.getFavorite("forumUser", 15));
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
