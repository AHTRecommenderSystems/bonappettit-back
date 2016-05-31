package com.aht.bonappettit.webservices.node;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.neo4j.ogm.session.result.ResultProcessingException;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.Restaurant;
import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.relationship.Upload;

import java.util.LinkedList;
import org.json.JSONObject;
import org.json.JSONArray;

@Component
@Path("/users")
public class UserWS {
	@Autowired UserServiceImpl userService;
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(
			@FormParam("name") String name, 
			@FormParam("email") String email, 
			@FormParam("since") String since, 
			@FormParam("gender") String gender, 
			@FormParam("lastname") String lastname, 
			@FormParam("password") String password, 
			@FormParam("country") String nationality,
			@FormParam("birthdate") String birthdate) {
		User user = new User();
		JSONObject response = new JSONObject();
		try {
			if(name != null)
				user.setName(name);
			if(email != null)
				user.setEmail(email);
			if(since != null)
				user.setSince(since);
			if(gender != null)
				user.setGender(gender);
			if(lastname != null)
				user.setLastname(lastname);
			if(password != null)
				user.setPassword(password);
			if(nationality != null)
				user.setNationality(nationality);
			if(birthdate != null)
				user.setBirthdate(birthdate);
			user = userService.create(user);
			if(user.getId() != null) {
				response.put("success", true);
				response.put("id", user.getId());
			}
		} catch (ResultProcessingException exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@PathParam("id") String id) {
		User user = new User();
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			user = userService.retrieve(Long.parseLong(id));
			if(user.getName() != null)
				data.put("name", user.getName());
			if(user.getLastname() != null)
				data.put("lastname", user.getLastname());
			if(user.getGender() != null)
				data.put("gender", user.getGender());
			if(user.getNationality() != null)
				data.put("country", user.getNationality());
			if(user.getBirthdate() != null)
				data.put("birthdate", user.getBirthdate());
			if(user.getSince() != null)
				data.put("since", user.getSince());
			response.put("data", data);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		} 
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "GET").build();
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response update(
			@FormParam("id") String id, 
			@FormParam("name") String name, 
			@FormParam("email") String email, 
			@FormParam("since") String since, 
			@FormParam("gender") String gender, 
			@FormParam("lastname") String lastname, 
			@FormParam("password") String password, 
			@FormParam("nationality") String nationality, 
			@FormParam("birthdate") String birthdate) {
		User user = new User();
		JSONObject response = new JSONObject();		
		try {
			user = userService.retrieve(Long.parseLong(id));
			if(name != null)
				user.setName(name);
			if(email != null)
				user.setEmail(email);
			if(since != null)
				user.setSince(since);
			if(gender != null)
				user.setGender(gender);
			if(lastname != null)
				user.setLastname(lastname);
			if(password != null)
				user.setPassword(password);
			if(nationality != null)
				user.setNationality(nationality);
			if(birthdate != null)
				user.setBirthdate(birthdate);
			userService.update(user);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "PUT").build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		User user = new User();
		JSONObject response = new JSONObject();
		try {
			user = userService.retrieve(Long.parseLong(id));
			userService.delete(user);
			response.put("success", true);
		} catch(Exception exception) {
			exception.printStackTrace();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "DELETE").build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONObject response = new JSONObject();
		JSONArray users = new JSONArray();
		try {
			LinkedList<User> userNodes = userService.retrieveAll();
			users.put(userNodes);
			response.put("users", users);
			response.put("success", true);
		} catch (Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "GET").build();
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("email") String email, @FormParam("password") String password) {
		JSONObject response = new JSONObject();
		try {
			User user = userService.login(email, password);
			response.put("id", user.getId());
			response.put("name", user.getName());
			response.put("lastname", user.getLastname());
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@GET
	@Path("/{id}/dishes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDishes(@PathParam("id") String id) {
		JSONObject response;
		User user = new User();
		JSONArray dishes = new JSONArray();
		JSONArray restaurants = new JSONArray();
		try {
			response = new JSONObject();
			user = userService.retrieve(Long.parseLong(id));
			
			for(Upload upload : user.getUploads()) {
				JSONObject dish = new JSONObject();
				Dish d = upload.getDish();
				dish.put("name", d.getName());
				dish.put("picture", d.getPicture());
				dish.put("description", d.getDescription());
				dish.put("averageRating", d.getAverageRating());
				for(Restaurant r : d.getRestaurants()) {
					JSONObject restaurant = new JSONObject();
					restaurant.put("id", r.getId());
					restaurant.put("name", r.getName());
					restaurant.put("type", r.getType());
					restaurant.put("address", r.getAddress());
					restaurant.put("longitude", r.getLongitude());
					restaurant.put("latitude", r.getLatitude());
					restaurant.put("avg_price", r.getAvg_price());
					restaurant.put("url", r.getURL());
					restaurants.put(restaurant);
				}
				dish.put("restaurants", restaurants);
				dishes.put(dish);
			}
			response.put("dishes", dishes);
			response.put("success", true);
		} catch(Exception exception) {
			response = new JSONObject();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "GET").build();
	}
}