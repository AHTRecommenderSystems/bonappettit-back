package com.aht.bonappettit.domain.node;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import com.aht.api.model.node.Item;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@SuppressWarnings("unchecked")
public class Characteristic implements com.aht.api.model.node.Characteristic {
	@GraphId
	private Long id;
	private String name;
	@Relationship(type = "HAS", direction = Relationship.UNDIRECTED)
	private List<Dish> dishes = new LinkedList<>();

	public Characteristic() { }

	public Characteristic(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Characteristic(Long id, String name, List<Dish> dishes) {
		this.id = id;
		this.name = name;
		this.dishes = dishes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
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

	public Object getModelId() {
		return id;
	}

	public List<Item> getModelItems() {
		return (List<Item>)(Object)dishes;
	}
}
