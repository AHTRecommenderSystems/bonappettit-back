package com.aht.bonappettit.service.node;

import java.util.List;
import com.aht.bonappettit.domain.node.User;

public interface UserService {
	public void create(User user);
	public User retrieve(long id);
	public void update(User user);
	public void delete(User user);
	public List<User> retrieveAll();
	public User findByEmail(String email);
	public User findByUsername(String username);
}
