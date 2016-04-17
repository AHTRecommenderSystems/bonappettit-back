package com.aht.bonappettit.domain.node;

import java.util.Set;
import java.util.HashSet;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Category implements com.aht.neo4j.model.node.Category {
	@GraphId
	private Long id;
	private String name;
	@Relationship(type = "HAS", direction = Relationship.INCOMING)
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
