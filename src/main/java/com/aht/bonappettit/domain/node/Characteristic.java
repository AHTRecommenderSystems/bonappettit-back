package com.aht.bonappettit.domain.node;

import java.util.List;
import java.util.LinkedList;
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
	private String type;
	@Relationship(type = "HAS", direction = Relationship.INCOMING)
	private List<Dish> dishes = new LinkedList<>();
		
	public Characteristic() { }

	public Characteristic(String name, String type) {
		this.name = name; 
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		return "id: " + id + " name: " + name + " type: " + type;
	}

	public Object getModelId() {
		return id;
	}

	public List<Item> getModelItems() {
		return (List<Item>)(Object)dishes;
	}
}
