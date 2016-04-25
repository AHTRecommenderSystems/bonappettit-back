package com.aht.bonappettit.webservices.node;

import java.util.LinkedList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.domain.node.Dish;
import org.json.JSONObject;
import org.json.JSONArray;

@Component
@Path("/dishws")
public class DishWS {
	@Autowired DishServiceImpl dishService;

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("name") String name, @FormParam("picture") String picture,
			@FormParam("description") String description) {
		JSONObject response = new JSONObject();
		Dish dish = new Dish();
		
		try {
			if(name != null)
				dish.setName(name);
			if(picture != null)
				dish.setPicture(picture);
			if(description != null)
				dish.setDescription(description);
			
			dish = dishService.create(dish);
			if(dish.getId() != null){
				response.put("success", true);
				response.put("id", dish.getId());
			}
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
	
	@POST
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@FormParam("id") String id) {
		Dish dish = new Dish();
		JSONObject response = new JSONObject();
		try{
			dish = dishService.retrieve(Long.parseLong(id));
			if(dish.getName() != null)
				response.put("name", dish.getName());
			if(dish.getDescription() != null)
				response.put("description()", dish.getDescription());
			if(dish.getPicture() != null)
				response.put("picture", dish.getPicture());
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@FormParam("id") String id, @FormParam("name") String name, @FormParam("picture") String picture,
			@FormParam("description") String description) {
		Dish dish = new Dish();
		JSONObject response = new JSONObject();
		try {
			dish = dishService.retrieve(Long.parseLong(id));
			if(name != null)
				dish.setName(name);
			if(description != null)
				dish.setDescription(description);
			if(picture != null)
				dish.setPicture(picture);
			dishService.update(dish);
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
		Dish dish = new Dish();
		JSONObject response = new JSONObject();
		try {
			dish = dishService.retrieve(Long.parseLong(id));
			dishService.delete(dish);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}

	@POST
	@Path("/retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONObject response = new JSONObject();
		JSONArray dishes = new JSONArray();
		
		try {
			LinkedList<Dish> dishNodes = dishService.retrieveAll();
			dishes.put(dishNodes);
			response.put("dishes", dishes);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
}
