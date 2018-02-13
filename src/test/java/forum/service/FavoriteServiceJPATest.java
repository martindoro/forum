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

import forum.entity.Favorite;
import forum.server.ForumServerForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ImportAutoConfiguration
@ContextConfiguration(classes = ForumServerForTest.class)
public class FavoriteServiceJPATest {
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Before
	public void setup() {
	favoriteService.setFavorite(new Favorite("aaa", 15, 1));
	favoriteService.setFavorite(new Favorite("bbb", 16, 0));
	favoriteService.setFavorite(new Favorite("ccc", 17, -1));
	favoriteService.setFavorite(new Favorite("ddd", 18, 1));
	favoriteService.setFavorite(new Favorite("aaa", 19, 1));
	}
	
	@Test
	public void testSetFavorite() {
		assertEquals(0, favoriteService.getFavorite("bbb", 16).getValue());
		assertEquals(1, favoriteService.getFavorite("aaa", 19).getValue());
		assertEquals(1, favoriteService.getFavorite("aaa", 15).getValue());
	}

	@Test
	public void testGetFavorites() {
		assertEquals(2, favoriteService.getFavorites("aaa").size());
		assertEquals(1, favoriteService.getFavorites("ccc").size());
		assertEquals(0, favoriteService.getFavorites("hkl").size());
	}

	@Test
	public void testIsFavorite() {
		assertEquals(false, favoriteService.isFavorite("pako", 123));
		assertEquals(true, favoriteService.isFavorite("ddd", 18));
	}

	@Test
	public void testGetFavorite() {
		assertEquals(16, favoriteService.getFavorite("bbb", 16).getCommentId());
		assertEquals("bbb", favoriteService.getFavorite("bbb", 16).getUserName());
		assertEquals(17, favoriteService.getFavorite("ccc", 17).getCommentId());
		assertEquals("ccc", favoriteService.getFavorite("ccc", 17).getUserName());
		assertEquals(18, favoriteService.getFavorite("ddd", 18).getCommentId());
		assertEquals("ddd", favoriteService.getFavorite("ddd", 18).getUserName());
	}

	@Test
	public void testUpdateFavorite() {
		assertEquals(1, favoriteService.getFavorite("aaa", 19).getValue());
		favoriteService.updateFavorite(favoriteService.getFavorite("aaa", 19).getIdent(), -1);
		assertEquals(0, favoriteService.getFavorite("aaa", 19).getValue());
	}

}
