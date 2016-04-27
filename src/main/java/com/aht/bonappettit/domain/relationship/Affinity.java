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
	private double similitude;
	
	public Affinity() { }

	public Affinity(Dish first, Dish second, double similitude) {
		this.first = first;
		this.second = second;
		this.similitude = similitude;
		this.first.getAffinities().add(this);
		this.second.getAffinities().add(this);
	}

	public Long getId() {
		return id;
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

	public double getSimilitude() {
		return similitude;
	}

	public void setSimilitude(double similitude) {
		this.similitude = similitude;
	}


	public double getSimilitudeValue() {
		return similitude;
	}

	public Item getFirstModelItem() {
		return first;
	}

	public Item getSecondModelItem() {
		return second;
	}
}
