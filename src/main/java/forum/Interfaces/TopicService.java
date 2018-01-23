package forum.Interfaces;

import java.util.List;
import forum.entity.Topic;

public interface TopicService {
	void addTopic(Topic topic);
	List<Topic> getTopics(String category);
}
