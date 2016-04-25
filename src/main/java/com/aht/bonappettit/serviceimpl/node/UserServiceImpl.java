package com.aht.bonappettit.serviceimpl.node;

import java.util.LinkedList;
import org.neo4j.ogm.session.Session;
import org.neo4j.helpers.collection.MapUtil;
import org.springframework.stereotype.Service;

import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.repository.node.UserRepository;
import com.aht.bonappettit.service.node.UserService;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {
	@Autowired Session session;
	@Autowired UserRepository repository;

	public User create(User user) {
		return repository.save(user);
	}

	public User retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(User user) {
		repository.save(user, user.getId().intValue());
	}

	public void delete(User user) {
		repository.delete(user);
	}

	public void delete(long id) {
		repository.delete(id);
	}

	public LinkedList<User> retrieveAll() {
		return new LinkedList<User>(session.loadAll(User.class));
	}

	public User findByEmail(String email) {
		return (User) session.queryForObject(User.class, "match (user:User {email: {email}}) return user", MapUtil.map("email", email));		
	}

	public User findByUsername(String username) {
		return (User) session.queryForObject(User.class, "match (user:User {username: {username}}) return user", MapUtil.map("username", username));
	}
}
