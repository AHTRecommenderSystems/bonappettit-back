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
	private double similitude;
	
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

	public void setId(Long id) {
		this.id = id;
	}

	public User getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(User firstUser) {
		this.firstUser = firstUser;
	}

	public User getSecondUser() {
		return secondUser;
	}

	public void setSecondUser(User secondUser) {
		this.secondUser = secondUser;
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

	public com.aht.api.model.node.User getFirstModelUser() {
		return firstUser;
	}

	public com.aht.api.model.node.User getSecondModelUser() {
		return secondUser;
	}
}
