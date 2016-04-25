package com.aht.bonappettit.domain.node;

import java.util.Set;
import java.util.HashSet;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.Characteristic;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.domain.relationship.Click;
import com.aht.bonappettit.domain.relationship.Upload;
import com.aht.bonappettit.domain.relationship.Affinity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.GraphId;

@SuppressWarnings("unchecked")
public class Dish implements Item {
	@GraphId
	private Long id;
	private String name;
	private String picture; 
	private String description;
	@Relationship(type = "HAS", direction = Relationship.UNDIRECTED)
	private Set<Characteristic> characteristics = new HashSet<Characteristic>();
	@Relationship(type = "CLICKED")
	private Set<Click> clicks = new HashSet<Click>();
	@Relationship(type = "RATED")
	private Set<Rate> ratings = new HashSet<Rate>();
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
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addCharacteristic(Characteristic characteristic) {
		if(!characteristics.contains(characteristic))
			characteristics.add(characteristic);
	}

	public Set<Characteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(Set<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	public Set<Click> getClicks() {
		return clicks;
	}

	public void setClicks(Set<Click> clicks) {
		this.clicks = clicks;
	}

	public Set<Rate> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rate> ratings) {
		this.ratings = ratings;
	}

	public Set<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(Set<Upload> uploads) {
		this.uploads = uploads;
	}

	public Set<Affinity> getAffinities() {
		return affinities;
	}

	public void setAffinities(Set<Affinity> affinities) {
		this.affinities = affinities;
	}

	// get affinities interfaces
	public Set<com.aht.api.model.relationship.Affinity> getModelAffinities() {
		return (Set<com.aht.api.model.relationship.Affinity>)(Object)affinities;
	}
	
	// get characteristics interfaces
	public Set<Characteristic> getModelCharacteristics() {
		return (Set<Characteristic>)(Object) characteristics;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name;
	}
}
