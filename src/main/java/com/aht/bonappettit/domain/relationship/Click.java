package com.aht.bonappettit.domain.relationship;

import com.aht.api.model.node.Item;
import com.aht.api.model.relationship.Event;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.User;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;

@RelationshipEntity(type = "CLICKED")
public class Click implements Event {
	@GraphId
	private Long id;
	@StartNode
	private User user;
	@EndNode
	private Dish dish;
	private String on;

	public Click() { }
	
	public Click(User user, Dish dish, String on) {
		this.on = on;
		this.user = user;
		this.dish = dish;
		this.user.getClicks().add(this);
		this.dish.getClicks().add(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Item getModelItem() {
		return dish;
	}

	public com.aht.api.model.node.User getModelUser() {
		return user;
	}

	public double getModelValue() {
		return 0;
	}
}
