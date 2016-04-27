package com.aht.bonappettit.webservices.node;

import java.util.LinkedList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aht.bonappettit.domain.node.Restaurant;
import com.aht.bonappettit.serviceimpl.node.RestaurantServiceImpl;

@Component
@Path("/restaurantws")
public class RestaurantWS {
	@Autowired RestaurantServiceImpl service;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("name") String name, @FormParam("type") String type, @FormParam("address") String address, 
			@FormParam("longitude") String longitude, @FormParam("latitude") String latitude, @FormParam("avg_price") String avg_price,
			@FormParam("url") String url) {
		JSONObject response = new JSONObject();
		Restaurant restaurant = new Restaurant();
		try {
			if(name != null)
				restaurant.setName(name);
			if(type != null)
				restaurant.setType(type);
			if(address != null)
				restaurant.setAddress(address);
			if(longitude != null)
				restaurant.setLongitude(Double.parseDouble(longitude));
			if(latitude != null)
				restaurant.setLatitude(Double.parseDouble(latitude));
			if(avg_price != null)
				restaurant.setAvg_price(Integer.parseInt(avg_price));
			if(url != null)
				restaurant.setURL(url);
			restaurant = service.create(restaurant);
			if(restaurant.getId() != null) {
				response.put("id", restaurant.getId());
				response.put("success", true);
			}
		} catch(Exception exception) {
			response.put("success", true);
		}
		return Response.status(200).entity(response.toString()).build();
	}

	@POST
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@FormParam("id") String id) {
		JSONObject response = new JSONObject();
		Restaurant restaurant = new Restaurant();
		try {
			restaurant = service.retrieve(Long.parseLong(id));
			if(restaurant.getName() != null)
				response.put("name", restaurant.getName());
			if(restaurant.getType() != null)
				response.put("type", restaurant.getType());
			if(restaurant.getAddress() != null)
				response.put("address", restaurant.getAddress());
			response.put("longitude", restaurant.getLongitude());
			response.put("latitude", restaurant.getLatitude());
			response.put("avg_price", restaurant.getAvg_price());
			if(restaurant.getURL() != null)
				response.put("url", restaurant.getURL());
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@FormParam("id") String id, @FormParam("name") String name, @FormParam("type") String type, @FormParam("address") String address, 
			@FormParam("longitude") String longitude, @FormParam("latitude") String latitude, @FormParam("avg_price") String avg_price,
			@FormParam("url") String url) {
		
		JSONObject response = new JSONObject();
		Restaurant restaurant = new Restaurant();
		try {
			restaurant = service.retrieve(Long.parseLong(id));
			if(name != null)
				restaurant.setName(name);
			if(type != null)
				restaurant.setType(type);
			if(address != null)
				restaurant.setAddress(address);
			if(longitude != null)
				restaurant.setLongitude(Double.parseDouble(longitude));
			if(latitude != null)
				restaurant.setLatitude(Double.parseDouble(latitude));
			if(avg_price != null)
				restaurant.setAvg_price(Integer.parseInt(avg_price));
			if(url != null)
				restaurant.setURL(url);
			service.update(restaurant);
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
		JSONObject response = new JSONObject();
		Restaurant restaurant = new Restaurant();
		try {
			restaurant = service.retrieve(Long.parseLong(id));
			service.delete(restaurant);
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
		JSONArray restaurants = new JSONArray();
		try {
			LinkedList<Restaurant> results = service.retrieveAll();
			restaurants.put(results);
			response.put("restaurants", restaurants);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
}
