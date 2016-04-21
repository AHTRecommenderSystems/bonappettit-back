package com.aht.bonappettit.domain.relationship;

import com.aht.api.model.node.Item;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.aht.bonappettit.domain.node.Dish;

@RelationshipEntity(type = "AFFINITY")
public class Affinity implements com.aht.api.model.relationship.Affinity {
	@GraphId
	private Long id;
	@StartNode
	private Dish first;
	@EndNode
	private Dish second;
	private float similitude;

	public Long getId() {
		return id;
	}

	public float getSimilitude() {
		return similitude;
	}

	public void setSimilitude(float similitude) {
		this.similitude = similitude;
	}

	public Item getFirstItem() {
		return first;
	}

	public Item getSecondItem() {
		return second;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dish getFirst() {
		return first;
	}

	public void setFirst(Dish first) {
		this.first = first;
	}

	public Dish getSecond() {
		return second;
	}

	public void setSecond(Dish second) {
		this.second = second;
	}
}
