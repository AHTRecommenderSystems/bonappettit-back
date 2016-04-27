package com.aht.bonappettit.domain.node;

import com.aht.api.model.relationship.Event;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.domain.relationship.Click;
import com.aht.bonappettit.domain.relationship.Upload;
import com.aht.bonappettit.domain.relationship.Neighbor;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.GraphId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NodeEntity
@SuppressWarnings("unchecked")
public class User implements com.aht.api.model.node.User {
	@GraphId
	private Long id;
	private String name;
	private String lastname;
	private String password;
	private String gender;
	private String nationality;
	private String birthdate;
	private String email;
	private String since;
	@Relationship(type = "NEIGHBOR")
	private Set<Neighbor> neighbors = new HashSet<Neighbor>();
	@Relationship(type = "CLICKED")
	private Set<Click> clicks = new HashSet<Click>();
	@Relationship(type = "RATED")
	private Set<Rate> ratings = new HashSet<Rate>();
	@Relationship(type = "UPLOADED")
	private Set<Upload> uploads = new HashSet<Upload>();

	public User(Long id, String name, String lastname, String password, String gender, String nationality, String birthdate, String email, String since) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.gender = gender;
		this.nationality = nationality;
		this.birthdate = birthdate;
		this.email = email;
		this.since = since;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Neighbor> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(Set<Neighbor> neighbors) {
		this.neighbors = neighbors;
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

	public String toString() {
		return "name: " + name + " lastname: " + lastname + " password: " + password +
				" gender: " + gender + " nationality: " + nationality + " birthdate: " + birthdate + " email: " + 
				email + " since: " + since;
	}

	public Object getModelId() {
		return id;
	}

	public List<com.aht.api.model.relationship.Neighbor> getModelNeighbors() {
		return (List<com.aht.api.model.relationship.Neighbor>)(Object)neighbors;
	}

	public List<Event> getModelEvents() {
		return (List<Event>)(Object)ratings;
	}
}
