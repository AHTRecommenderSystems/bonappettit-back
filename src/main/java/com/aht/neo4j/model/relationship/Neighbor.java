package com.aht.neo4j.model.relationship;

import com.aht.neo4j.model.node.User;

public interface Neighbor {
	public Object getId();
	public float getSimiluted();
	public void setSimilitude(float similitude);
	public User getFirst();
	public User getSecond();
}
