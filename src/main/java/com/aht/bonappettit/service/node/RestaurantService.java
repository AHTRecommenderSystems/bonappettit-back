package com.aht.bonappettit.service.node;

import java.util.LinkedList;

import com.aht.bonappettit.domain.node.Restaurant;

public interface RestaurantService {
	public Restaurant create(Restaurant restaurant);
	public Restaurant retrieve(long id);
	public void update(Restaurant restaurant);
	public void delete(Restaurant restaurant);
	public void delete(long id);
	public LinkedList<Restaurant> retrieveAll();
}
