package forum.Interfaces;


import forum.entity.User;

public interface UserService {
	void register(User user);
	User login(String login, String password);
	/*TODO pridat user admin*/
}
