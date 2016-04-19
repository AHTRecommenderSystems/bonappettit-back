package com.aht.neo4j.model.relationship;

import com.aht.neo4j.model.node.Item;

public interface Affinity {
	public Object getId();
	public float getSimilitude();
	public void setSimilitude(float similitude);
	public Item getFirst();
	public Item getSecond();
}
