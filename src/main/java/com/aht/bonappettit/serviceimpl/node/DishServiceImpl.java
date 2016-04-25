package com.aht.bonappettit.serviceimpl.node;

import java.util.LinkedList;
import org.neo4j.ogm.session.Session;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.service.node.DishService;
import com.aht.bonappettit.repository.node.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
	@Autowired Session session;
	@Autowired DishRepository repository;

	public Dish create(Dish dish) {
		return repository.save(dish);
	}

	public Dish retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(Dish dish) {
		repository.save(dish, dish.getId().intValue());
	}

	public void delete(Dish dish) {
		repository.delete(dish);
	}

	public void delete(long id) {
		repository.delete(id);
	}

	public LinkedList<Dish> retrieveAll() {
		return new LinkedList<Dish>(session.loadAll(Dish.class));
	}
}
