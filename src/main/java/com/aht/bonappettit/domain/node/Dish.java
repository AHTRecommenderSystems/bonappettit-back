package com.aht.bonappettit.domain.node;

import java.util.List;
import java.util.LinkedList;
import com.aht.api.model.node.Item;
import com.aht.api.model.relationship.Event;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.domain.relationship.Click;
import com.aht.bonappettit.domain.relationship.Upload;
import com.aht.bonappettit.domain.relationship.Affinity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.GraphId;

@NodeEntity
@SuppressWarnings("unchecked")
public class Dish implements Item {
	@GraphId
	private Long id;
	private String name;
	private String picture;
	private String description;
	@Relationship(type = "HAS", direction = Relationship.OUTGOING)
	private List<Characteristic> characteristics = new LinkedList<Characteristic>();
	@Relationship(type = "CLICKED")
	private List<Click> clicks = new LinkedList<Click>();
	@Relationship(type = "RATED")
	private List<Rate> ratings = new LinkedList<Rate>();
	@Relationship(type = "UPLOADED")
	private List<Upload> uploads = new LinkedList<Upload>();
	@Relationship(type = "AFFINITY")
	private List<Affinity> affinities = new LinkedList<Affinity>();
	@Relationship(type = "SERVED_AT", direction = Relationship.OUTGOING)
	private List<Restaurant> restaurants = new LinkedList<Restaurant>();
		
	public Dish() { }

	public Dish(String name, String picture, String description) {
		this.name = name;
		this.picture = picture;
		this.description = description;
	}

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
	
	public List<Characteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}
	
	public List<Click> getClicks() {
		return clicks;
	}

	public void setClicks(List<Click> clicks) {
		this.clicks = clicks;
	}

	public List<Rate> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rate> ratings) {
		this.ratings = ratings;
	}

	public List<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}

	public List<Affinity> getAffinities() {
		return affinities;
	}

	public void setAffinities(List<Affinity> affinities) {
		this.affinities = affinities;
	}
	
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public void addCharacteristic(Characteristic characteristic) {
		if(!characteristics.contains(characteristic))
			characteristics.add(characteristic);
	}
	
	public void addRestaurant(Restaurant restaurant) {
		if(!restaurants.contains(restaurant))
			restaurants.add(restaurant);
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " picture: " + picture + " description: " + description;
	}

	public Object getModelId() {
		return id;
	}

	public List<Event> getModelEvents() {
		return (List<Event>)(Object)ratings;
	}

	public List<com.aht.api.model.node.Characteristic> getModelCharacteristics() {
		return (List<com.aht.api.model.node.Characteristic>)(Object)characteristics;
	}

	public List<com.aht.api.model.relationship.Affinity> getModelAffinities() {
		return (List<com.aht.api.model.relationship.Affinity>)(Object)affinities;
	}
}