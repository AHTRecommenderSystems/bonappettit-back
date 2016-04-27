package com.aht.bonappettit.domain.relationship;

import com.aht.api.model.node.Item;
import com.aht.api.model.relationship.Event;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.User;

@RelationshipEntity(type = "RATED")
public class Rate implements Event {
	private Long id;
	@StartNode
	private User user;
	@EndNode
	private Dish dish;
	private String on;
	private float value;
	
	public Rate() { }

	public Rate(User user, Dish dish, float value) {
		this.user = user;
		this.dish = dish;
		this.value = value;
		this.user.getRatings().add(this);
		this.dish.getRatings().add(this);
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}

	public Item getItem() {
		return dish;
	}

	public float getRate() {
		return value;
	}

	public void setRate(float value) {
		this.value = value;
	}
}
