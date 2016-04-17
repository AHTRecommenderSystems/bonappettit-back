package com.aht.bonappettit.service.node;

import java.util.List;
import com.aht.bonappettit.domain.node.Dish;

public interface DishService {
	public void create(Dish dish);
	public Dish retrieve(long id);
	public void update(Dish dish);
	public void delete(Dish dish);
	public List<Dish> retrieveAll();
}
