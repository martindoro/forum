package forum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import forum.entity.Topic;

public class TopicServiceJPA {
	@PersistenceContext
	private EntityManager entityManager;
	

	public void addTopic(Topic topic) {		
		entityManager.persist(topic);
	}

	public List<Topic> getTopic(int ident) {		
		return entityManager.createQuery("SELECT t FROM Topic t WHERE t.categoryId = :ident ").getResultList();
	}

	private void removeTopic(int ident) {
		entityManager.createQuery("DELETE FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).executeUpdate();
	}
}
