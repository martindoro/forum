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

	private Favorite f1 = new Favorite("aaa", 15, 1);
	private Favorite f2 = new Favorite("bbb", 16, 0);
	private Favorite f3 = new Favorite("ccc", 17, -1);
	private Favorite f4 = new Favorite("ddd", 18, 1);
	private Favorite f5 = new Favorite("aaa", 19, 1);
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Before
	public void setup() {
	favoriteService.setFavorite(f1);
	favoriteService.setFavorite(f2);
	favoriteService.setFavorite(f3);
	favoriteService.setFavorite(f4);
	favoriteService.setFavorite(f5);
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
		assertEquals(f2, favoriteService.getFavorite("bbb", 16));
		assertEquals(f3, favoriteService.getFavorite("ccc", 17));
		assertEquals(f4, favoriteService.getFavorite("ddd", 18));
	}

	@Test
	public void testUpdateFavorite() {
		assertEquals(1, favoriteService.getFavorite("aaa", 19).getValue());
		favoriteService.updateFavorite(favoriteService.getFavorite("aaa", 19).getIdent(), -1);
		assertEquals(0, favoriteService.getFavorite("aaa", 19).getValue());
	}

}
