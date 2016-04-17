package com.aht.bonappettit.domain.relationship;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.aht.bonappettit.domain.node.User;

@RelationshipEntity(type = "NEIGHBOR")
public class Neighbor implements com.aht.neo4j.model.relationship.Neighbor {
	@GraphId
	private Long id;
	@StartNode
	private User first;
	@EndNode
	private User second;
	private float similitude;
	
	public Neighbor() { }

	public Neighbor(User first, User second, float similitude) { 
		this.first = first;
		this.second = second;
		this.similitude = similitude;
		
		this.first.getNeighbors().add(this);
		this.second.getNeighbors().add(this);
	}

	public Long getId() {
		return id;
	}

	public User getFirst() {
		return first;
	}

	public void setFirst(User first) {
		this.first = first;
	}

	public User getSecond() {
		return second;
	}

	public void setSecond(User second) {
		this.second = second;
	}

	public float getSimiluted() {
		return similitude;
	}

	public void setSimilitude(float similitude) {
		this.similitude = similitude;
	}
}
