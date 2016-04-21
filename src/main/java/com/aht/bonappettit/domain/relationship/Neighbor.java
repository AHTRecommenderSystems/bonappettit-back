package com.aht.bonappettit.domain.relationship;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.aht.bonappettit.domain.node.User;

@RelationshipEntity(type = "NEIGHBOR")
public class Neighbor implements com.aht.api.model.relationship.Neighbor {
	@GraphId
	private Long id;
	@StartNode
	private User firstUser;
	@EndNode
	private User secondUser;
	private float similitude;
	
	public Neighbor() { }

	public Neighbor(User firstUser, User secondUser, float similitude) {
		this.firstUser = firstUser;
		this.secondUser = secondUser;
		this.similitude = similitude;
		
		this.firstUser.getNeighbors().add(this);
		this.secondUser.getNeighbors().add(this);
	}

	public Long getId() {
		return id;
	}

	public Object getSimilitude() {
		return similitude;
	}

	public void setSimilitude(Object similitude) {
		this.similitude = Float.parseFloat((String) similitude);
	}

	public com.aht.api.model.node.User getFirstUser() {
		return firstUser;
	}

	public com.aht.api.model.node.User getSecondUser() {
		return secondUser;
	}
}
