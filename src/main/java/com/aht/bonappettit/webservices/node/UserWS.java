package com.aht.bonappettit.webservices.node;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import java.util.LinkedList;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.neo4j.ogm.session.result.ResultProcessingException;

@Component
@Path("/userws")
public class UserWS {
	@Autowired UserServiceImpl userService;

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("name") String name, @FormParam("lastname") String lastname, @FormParam("lastnameII") String lastnameII, @FormParam("password") String password, @FormParam("gender") String gender, @FormParam("nationality") String nationality, @FormParam("birthdate") String birthdate, @FormParam("since") String since) {

		JSONObject response = new JSONObject();
		User user = new User();
		try {
			if(name != null)
				user.setName(name);
			if(lastname != null)
				user.setLastname(lastname);
			if(lastnameII != null)
				user.setLastnameII(lastnameII);
			if(password != null)
				user.setPassword(password);
			if(gender != null)
				user.setGender(gender);
			if(nationality != null)
				user.setNationality(nationality);
			if(birthdate != null)
				user.setBirthdate(birthdate);
			if(since != null)
				user.setSince(since);
			
			user = userService.create(user);
			if(user.getId() != null) {
				response.put("success", true);
				response.put("id", user.getId());
			}
			
		} catch (ResultProcessingException exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}

	@POST
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@FormParam("id") String id) {
		User user = new User();
		JSONObject response = new JSONObject();
		try {
			user = userService.retrieve(Long.parseLong(id));
			if(user.getName() != null)
				response.put("name", user.getName());
			if(user.getLastname() != null)
				response.put("lastname", user.getLastname());
			if(user.getLastnameII() != null)
				response.put("lastnameII", user.getLastnameII());
			if(user.getPassword() != null)
				response.put("password", user.getPassword());
			if(user.getGender() != null)
				response.put("gender", user.getGender());
			if(user.getNationality() != null)
				response.put("nationality", user.getNationality());
			if(user.getBirthdate() != null)
				response.put("birthdate", user.getBirthdate());
			if(user.getEmail() != null)
				response.put("email", user.getEmail());
			if(user.getSince() != null)
				response.put("since", user.getSince());
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		} 
		return Response.status(200).entity(response.toString()).build();
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@FormParam("id") String id, @FormParam("name") String name, @FormParam("lastname") String lastname, @FormParam("lastnameII") String lastnameII, @FormParam("password") String password, @FormParam("gender") String gender, @FormParam("nationality") String nationality, @FormParam("birthdate") String birthdate, @FormParam("since") String since) {
		User user = new User();
		JSONObject response = new JSONObject();
		
		try {
			user = userService.retrieve(Long.parseLong(id));
			if(name != null)
				user.setName(name);
			if(lastname != null)
				user.setLastname(lastname);
			if(lastnameII != null)
				user.setLastnameII(lastnameII);
			if(password != null)
				user.setPassword(password);
			if(gender != null)
				user.setGender(gender);
			if(nationality != null)
				user.setNationality(nationality);
			if(birthdate != null)
				user.setBirthdate(birthdate);
			if(since != null)
				user.setSince(since);
			userService.update(user);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@FormParam("id") String id) {
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
		return Response.status(200).entity(response.toString()).build();
	}
	
	@POST
	@Path("/retrieveAll")
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
		
		return Response.status(200).entity(response.toString()).build();
	}
/**
	public Response retrieveAll() {
		JSONObject response = new JSONObject();
		JSONArray users = new JSONArray();
		
		try {
			LinkedList<User> userNodes = userService.retrieveAll();

			for(User user : userNodes) {
				JSONObject json = new JSONObject();
				if(user.getId() != null)
					json.put("id", user.getId());
				if(user.getName() != null)
					json.put("name", user.getName());
				if(user.getLastname() != null)
					json.put("lastname", user.getLastname());
				if(user.getLastnameII() != null)
					json.put("lastnameII", user.getLastnameII());
				if(user.getPassword() != null)
					json.put("password", user.getPassword());
				if(user.getGender() != null)
					json.put("gender", user.getGender());
				if(user.getNationality() != null)
					json.put("nationality", user.getNationality());
				if(user.getBirthdate() != null)
					json.put("birthdate", user.getBirthdate());
				if(user.getEmail() != null)
					json.put("email", user.getEmail());
				if(user.getSince() != null)
					json.put("since", user.getSince());
				users.put(json);
			}
			response.put("users", users);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
 */
}