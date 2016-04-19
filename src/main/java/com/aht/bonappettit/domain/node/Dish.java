package com.aht.bonappettit.domain.node;

import java.util.Set;
import java.util.HashSet;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;

import com.aht.bonappettit.domain.relationship.Affinity;
import com.aht.bonappettit.domain.relationship.Click;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.domain.relationship.Upload;
import com.aht.neo4j.model.node.Item;

public class Dish implements Item {
	@GraphId
	private Long id;
	private String name;
	@Relationship(type = "HAS", direction = Relationship.OUTGOING)
	private Set<Category> categories = new HashSet<Category>();
	@Relationship(type = "CLICKED")
	private Set<Click> clicks = new HashSet<Click>();
	@Relationship(type = "RATED")
	private Set<Rate> rates = new HashSet<Rate>();
	@Relationship(type = "UPLOADED")
	private Set<Upload> uploads = new HashSet<Upload>();
	@Relationship(type = "AFFINITY")
	private Set<Affinity> affinities = new HashSet<Affinity>();

	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<com.aht.neo4j.model.node.Category> getCategories() {
		return (Set<com.aht.neo4j.model.node.Category>)(Object)categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category category) {
		if(!categories.contains(category))
			categories.add(category);
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name;
	}

	public Set<com.aht.neo4j.model.relationship.Affinity> getAffinities() {
		return (Set<com.aht.neo4j.model.relationship.Affinity>)(Object)affinities;
	}

	public void setAffinities(Set<Affinity> affinities) {
		this.affinities = affinities;
	}
}
