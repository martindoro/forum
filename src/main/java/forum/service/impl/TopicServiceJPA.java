package forum.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import forum.entity.Topic;
import forum.service.TopicService;

@Transactional
public class TopicServiceJPA implements TopicService {
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#addTopic(forum.entity.Topic)
	 */
	@Override
	public void addTopic(Topic topic) {
		entityManager.persist(topic);
	}

	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#getTopicList(int)
	 */
	@Override
	public List<Topic> getTopicList(int ident) {
		return entityManager.createQuery("SELECT t FROM Topic t WHERE t.categoryId = :ident ")
				.setParameter("ident", ident).getResultList();
	}

	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#removeTopic(int)
	 */
	@Override
	public void removeTopic(int ident) {
		entityManager.createQuery("DELETE FROM Topic t WHERE t.ident = :ident").setParameter("ident", ident)
				.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#getTopicCountForCategory(int)
	 */
	@Override
	public long getTopicCountForCategory(int ident) {
		return (long) entityManager.createQuery("SELECT COUNT(t) FROM Topic t WHERE t.category_id = :ident")
				.setParameter("ident", ident).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#getTopicCount()
	 */
	@Override
	public long getTopicCount() {
		return (long) entityManager.createQuery("SELECT COUNT(t) FROM Topic t").getSingleResult();
	}

	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#getContentById(int)
	 */
	@Override
	public String getContentById(int ident) {
		return (String) entityManager.createQuery("SELECT t.content FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#getTopicState(int)
	 */
	@Override
	public boolean getTopicState(int ident) {
		return (boolean) entityManager.createQuery("SELECT t.lock FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}
	
	/* (non-Javadoc)
	 * @see forum.service.impl.TopicService#setTopicState(int, boolean)
	 */
	@Override
	public void setTopicState(int ident, boolean lock) {
					entityManager.createQuery(
							"UPDATE Topic t SET t.lock =:lock WHERE t.ident = :ident")
					.setParameter("ident", ident).setParameter("lock", lock).executeUpdate();
		}
}