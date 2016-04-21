package com.aht.bonappettit.domain.node;

import java.util.Set;
import java.util.HashSet;

import com.aht.api.model.node.Characteristic;
import com.aht.api.model.node.Item;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Category implements Characteristic {
	@GraphId
	private Long id;
	private String name;
	@Relationship(type = "HAS", direction = Relationship.UNDIRECTED)
	private Set<Dish> dishes = new HashSet<Dish>();
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Item> getItems() {
		return (Set<Item>)(Object)dishes;
	}

	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public void addDish(Dish dish) {
		if(!dishes.contains(dish))
			dishes.add(dish);
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name;
	}
}
