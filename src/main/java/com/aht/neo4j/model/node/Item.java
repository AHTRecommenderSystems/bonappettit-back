package com.aht.neo4j.model.node;

import com.aht.neo4j.model.relationship.Affinity;

import java.util.Set;

public interface Item {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Category> getCategories();
	public Set<Affinity> getAffinities();
}
