package forum.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import forum.entity.Topic;

@Transactional
public class TopicServiceJPA {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Adds new entry into topic table
	 * @param topic Topic object with all necessary variables set
	 */
	public void addTopic(Topic topic) {
		entityManager.persist(topic);
	}

	/**
	 * Returns List of topics under defined category
	 * @param ident Category ident for database selection
	 * @return List of Topics for categoty
	 */
	public List<Topic> getTopicList(int ident) {
		return entityManager.createQuery("SELECT t FROM Topic t WHERE t.categoryId = :ident ")
				.setParameter("ident", ident).getResultList();
	}

	/**
	 * Removes topic by ident from database
	 * @param ident ident of topic to be removed
	 */
	public void removeTopic(int ident) {
		entityManager.createQuery("DELETE FROM Topic t WHERE t.ident = :ident").setParameter("ident", ident)
				.executeUpdate();
	}

	/**
	 * Returns an integer of how many topics are there in current category
	 * @param ident category ident for database selection
	 * @return long number of topic count
	 */
	public long getTopicCountForCategory(int ident) {
		return (long) entityManager.createQuery("SELECT COUNT(t) FROM Topic t WHERE t.category_id = :ident")
				.setParameter("ident", ident).getSingleResult();
	}

	/**
	 * Returns an integer of how many topics are there in whole database
	 * @return complete topic count
	 */
	public long getTopicCount() {
		return (long) entityManager.createQuery("SELECT COUNT(t) FROM Topic t").getSingleResult();
	}

	/**
	 * Returns topic content for required topic
	 * @param ident topics ident for database selection
	 * @return topic content of selected topic
	 */
	public String getContentById(int ident) {
		return (String) entityManager.createQuery("SELECT t.content FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}

	/**
	 * Returns topic state - false on topic is open to comments and true if topic has been closed for further commenting
	 * @param ident topic ident for database selection
	 * @return true if topic is live and false when topic was closed by administrator
	 */
	public boolean getTopicState(int ident) {
		return (boolean) entityManager.createQuery("SELECT t.lock FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}
	
	/**
	 * Sets topic state to true, when administrator decided to close topic for commenting
	 * and to false if administrator re-opens topic
	 * @param ident topic ident for database selection
	 * @param lock {boolean} true for topic lock and false for topic unlock
	 */
	public void setTopicState(int ident, boolean lock) {
					entityManager.createQuery(
							"UPDATE Topic t SET t.lock =:lock WHERE t.ident = :ident")
					.setParameter("ident", ident).setParameter("lock", lock).executeUpdate();
		}
}