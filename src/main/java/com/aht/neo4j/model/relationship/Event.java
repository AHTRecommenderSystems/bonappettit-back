package com.aht.neo4j.model.relationship;

import com.aht.neo4j.model.node.Item;
import com.aht.neo4j.model.node.User;

public interface Event {
	public Object getId();
	public String getOn();
	public void setOn(String on);
	public Item getItem();
	public User getUser();
}
