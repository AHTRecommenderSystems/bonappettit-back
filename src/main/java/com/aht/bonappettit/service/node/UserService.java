package com.aht.bonappettit.service.node;

import java.util.LinkedList;
import com.aht.bonappettit.domain.node.User;

public interface UserService {
	public User create(User user);
	public User retrieve(long id);
	public void update(User user);
	public void delete(User user);
	public void delete(long id);
	public LinkedList<User> retrieveAll();
	public User findByEmail(String email);
}
