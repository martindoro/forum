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
		return entityManager.createQuery("SELECT t FROM Topic t WHERE t.categoryId = :ident ").setParameter("ident", ident).getResultList();
	}

	public void removeTopic(int ident) {
		entityManager.createQuery("DELETE FROM Topic t WHERE t.ident = :ident")
				.setParameter("ident", ident).executeUpdate();
	}
	
	public int getTopicCount(int ident) {
		return (int) entityManager.createQuery("SELECT COUNT(t) FROM Topic t WHERE t.category_id = :ident").setParameter("ident", ident).getSingleResult();
	}
}
