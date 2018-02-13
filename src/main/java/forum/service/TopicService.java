package forum.service;

import java.util.List;

import forum.entity.Topic;

public interface TopicService {

	/**
	 * Adds new entry into topic table
	 * @param topic Topic object with all necessary variables set
	 */
	void addTopic(Topic topic);

	/**
	 * Returns List of topics under defined category
	 * @param ident Category ident for database selection
	 * @return List of Topics for categoty
	 */
	List<Topic> getTopicList(int ident);

	/**
	 * Removes topic by ident from database
	 * @param ident ident of topic to be removed
	 */
	void removeTopic(int ident);

	/**
	 * Returns an integer of how many topics are there in current category
	 * @param ident category ident for database selection
	 * @return long number of topic count
	 */
	long getTopicCountForCategory(int ident);

	/**
	 * Returns an integer of how many topics are there in whole database
	 * @return complete topic count
	 */
	long getTopicCount();

	/**
	 * Returns topic content for required topic
	 * @param ident topics ident for database selection
	 * @return topic content of selected topic
	 */
	String getContentById(int ident);

	/**
	 * Returns topic state - false on topic is open to comments and true if topic has been closed for further commenting
	 * @param ident topic ident for database selection
	 * @return true if topic is live and false when topic was closed by administrator
	 */
	boolean getTopicState(int ident);

	/**
	 * Sets topic state to true, when administrator decided to close topic for commenting
	 * and to false if administrator re-opens topic
	 * @param ident topic ident for database selection
	 * @param lock {boolean} true for topic lock and false for topic unlock
	 */
	void setTopicState(int ident, boolean lock);

}