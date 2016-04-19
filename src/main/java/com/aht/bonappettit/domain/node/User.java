package com.aht.bonappettit.domain.node;

import java.util.Set;
import java.util.HashSet;

import com.aht.neo4j.model.relationship.Event;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.aht.bonappettit.domain.relationship.Click;
import com.aht.bonappettit.domain.relationship.Neighbor;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.domain.relationship.Upload;

@NodeEntity
public class User implements com.aht.neo4j.model.node.User {
	@GraphId
	private Long id;
	private String name;
	private String lastname;
	private String lastnameII;
	private String username;
	private String password;
	private String gender;
	private String nationality;
	private String birthdate;
	private String since;
	@Relationship(type = "NEIGHBOR")
	private Set<Neighbor> neighbors = new HashSet<Neighbor>();
	@Relationship(type = "CLICKED")
	private Set<Click> clicks = new HashSet<Click>();
	@Relationship(type = "RATED")
	private Set<Rate> ratings = new HashSet<Rate>();
	@Relationship(type = "UPLOADED")
	private Set<Upload> uploads = new HashSet<Upload>();
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastnameII() {
		return lastnameII;
	}

	public void setLastnameII(String lastnameII) {
		this.lastnameII = lastnameII;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public Set<com.aht.neo4j.model.relationship.Neighbor> getNeighbors() {
		return (Set<com.aht.neo4j.model.relationship.Neighbor>)(Object)neighbors;
	}

	@Override
	public Set<Event> getEvents() {
		Set<Event> events = new HashSet<Event>();
		events.addAll(ratings);
		// add all the events
		return events;
	}

	public void setNeighbors(Set<Neighbor> neighbors) {
		this.neighbors = neighbors;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " username: " + username + " password: " + password;
	}
}
