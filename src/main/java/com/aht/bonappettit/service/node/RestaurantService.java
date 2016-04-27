package com.aht.bonappettit.service.node;

import java.util.LinkedList;

import com.aht.bonappettit.domain.node.Restaurant;

public interface RestaurantService {
	public void delete(long id);
	public Restaurant create(Restaurant restaurant);
	public Restaurant retrieve(long id);
	public void update(Restaurant restaurant);
	public void delete(Restaurant restaurant);
	public LinkedList<Restaurant> retrieveAll();
}
