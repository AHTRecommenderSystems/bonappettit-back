package com.aht.bonappettit.domain.node;

import java.util.List;
import java.util.LinkedList;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.domain.relationship.Upload;
import com.aht.bonappettit.domain.relationship.Neighbor;
import com.aht.bonappettit.domain.relationship.Click;
import com.aht.api.model.relationship.Event;

@NodeEntity
@SuppressWarnings("unchecked")
public class User implements com.aht.api.model.node.User {
	@GraphId
	private Long id;
	private String name;
	private String email;
	private String since;
	private String gender;
	private String password;
	private String lastname;
	private String birthdate;
	private String nationality;

	@Relationship(type = "NEIGHBOR")
	private List<Neighbor> neighbors = new LinkedList<>();
	@Relationship(type = "CLICKED")
	private List<Click> clicks = new LinkedList<>();
	@Relationship(type = "RATED")
	private List<Rate> ratings = new LinkedList<>();
	@Relationship(type = "UPLOADED")
	private List<Upload> uploads = new LinkedList<>();

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Neighbor> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<Neighbor> neighbors) {
		this.neighbors = neighbors;
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

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " email: " + email + " since: " + since + " gender: " + gender
				+ " lastname: " + lastname + " password: " + password + " birthdate: " + birthdate + " nationality: "
				+ nationality + " password: " + password;
	}

	public Object getModelId() {
		return id;
	}

	public List<com.aht.api.model.relationship.Neighbor> getModelNeighbors() {
		return (List<com.aht.api.model.relationship.Neighbor>)(Object) neighbors;
	}

	public List<Event> getModelEvents() {
		return (List<Event>)(Object)ratings;
	}
}