package com.aht.bonappettit.domain.relationship;

import com.aht.api.model.node.Item;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.StartNode;
import com.aht.api.model.relationship.Event;
import org.neo4j.ogm.annotation.RelationshipEntity;
import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.node.Dish;

@RelationshipEntity(type = "UPLOADED")
public class Upload implements Event {
	@GraphId
	private Long id;
	@StartNode
	private User user;
	@EndNode
	private Dish dish;
	private String on;
	
	public Upload() { }
	
	public Upload(User user, Dish dish, String on) {
		this.on = on;
		this.user = user;
		this.dish = dish;
		this.user.getUploads().add(this);
		this.dish.getUploads().add(this);
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
		return null;
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
