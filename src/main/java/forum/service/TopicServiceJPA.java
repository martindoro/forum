package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import forum.entity.Topic;

@Transactional
public class TopicServiceJPA {
	@PersistenceContext
	private EntityManager entityManager;

	public void addTopic(Topic topic) {
		entityManager.persist(topic);
	}

	public List<Topic> getTopicList(int ident) {
		return entityManager.createQuery("SELECT t FROM Topic t WHERE t.categoryId = :ident ")
				.setParameter("ident", ident).getResultList();
	}

	public void removeTopic(int ident) {
		entityManager.createQuery("DELETE FROM Topic t WHERE t.ident = :ident").setParameter("ident", ident)
				.executeUpdate();
	}

	public long getTopicCountForCategory(int ident) {
		return (long) entityManager.createQuery("SELECT COUNT(t) FROM Topic t WHERE t.category_id = :ident")
				.setParameter("ident", ident).getSingleResult();
	}

	public long getTopicCount() {
		return (long) entityManager.createQuery("SELECT COUNT(t) FROM Topic t").getSingleResult();
	}

	public String getContentById(int ident) {
		return (String) entityManager.createQuery("SELECT t.content FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}
	
	/*
	*/
	public boolean getTopicState(int ident) {
		return (boolean) entityManager.createQuery("SELECT t.lock FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).getSingleResult();
	}
	
	public void setTopicState(int ident, boolean lock) {
					entityManager.createQuery(
							"UPDATE Topic t SET t.lock =:lock WHERE t.ident = :ident")
					.setParameter("ident", ident).setParameter("lock", lock).executeUpdate();
		}

/*	public void setTopicState(int ident, String lock) {
		// TODO Auto-generated method stub
		entityManager.createQuery(
				"UPDATE Topic t SET t.lock =:lock WHERE t.ident = :ident")
		.setParameter("ident", ident).setParameter("lock", lock).executeUpdate();
		
	}*/
	
	
}
