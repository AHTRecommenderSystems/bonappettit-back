package com.aht.bonappettit.domain.node;

import java.util.List;
import java.util.LinkedList;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Restaurant {
	@GraphId
	private Long id;
	private String name;
	private String type;
	private String address;
	private double longitude;
	private double latitude;
	private int avg_price;
	private String url;
	@Relationship(type = "SERVED_AT", direction = Relationship.INCOMING)
	private List<Dish> dishes = new LinkedList<>();
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getAvg_price() {
		return avg_price;
	}

	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public void addDish(Dish dish) {
		if(!dishes.contains(dish))
			dishes.add(dish);
	}
	
	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " type: " + type + " address: " + address + " url: " + url + 
				" longitude: " + longitude + " latitude: " + latitude + " avg_price: " + avg_price;
	}
}
