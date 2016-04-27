package com.aht.bonappettit.service.node;

import java.util.LinkedList;
import com.aht.bonappettit.domain.node.Dish;

public interface DishService {
	public Dish create(Dish dish);
	public Dish retrieve(long id);
	public void update(Dish dish);
	public void delete(Dish dish);
	public void delete(long id);
	public LinkedList<Dish> retrieveAll();
}
