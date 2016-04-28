package com.aht.bonappettit.serviceimpl.node;

import java.util.LinkedList;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.aht.bonappettit.repository.node.RestaurantRepository;
import com.aht.bonappettit.service.node.RestaurantService;
import com.aht.bonappettit.domain.node.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	@Autowired Session session;
	@Autowired RestaurantRepository repository;

	public Restaurant create(Restaurant restaurant) {
		return repository.save(restaurant);
	}

	public Restaurant retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(Restaurant restaurant) {
		repository.save(restaurant, restaurant.getId().intValue());
	}

	public void delete(Restaurant restaurant) {
		repository.delete(restaurant);
	}

	public void delete(long id) {
		repository.delete(id);
	}

	public LinkedList<Restaurant> retrieveAll() {
		return new LinkedList<Restaurant>(session.loadAll(Restaurant.class));
	}
}
