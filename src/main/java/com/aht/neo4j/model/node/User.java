package com.aht.neo4j.model.node;

import com.aht.neo4j.model.relationship.Affinity;
import com.aht.neo4j.model.relationship.Event;
import com.aht.neo4j.model.relationship.Neighbor;

import java.util.Set;

public interface User {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Neighbor> getNeighbors();
	public Set<Event> getEvents();
}
