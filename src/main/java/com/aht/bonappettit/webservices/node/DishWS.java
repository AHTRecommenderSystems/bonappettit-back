package com.aht.bonappettit.webservices.node;

import java.io.InputStream;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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

import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.Restaurant;
import com.aht.bonappettit.serviceimpl.node.CharacteristicServiceImpl;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.utils.FileHelper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.server.impl.container.servlet.JerseyServletContainerInitializer;

@Component
@Path("/dishws")
public class DishWS {
	private static final String directory = "/Users/hector9317/Documents/workspace/bonappettit-back/src/main/resources/";
	
	
	@Autowired FileHelper helper;
	@Autowired DishServiceImpl dishService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response create(
			@FormDataParam("name") String name,
			@FormDataParam("file") InputStream inputStream,
			@FormDataParam("description") String description,
			@FormDataParam("file") FormDataContentDisposition content) {

		Dish dish = new Dish();
		String fn = content.getFileName();
		JSONObject response = new JSONObject();
		String ext = fn.substring(fn.lastIndexOf("."), fn.length());
		String picture = helper.hashFunction(fn, name) + ext;
		dish.setDescription(description);
		dish.setPicture(picture);
		dish.setName(name);
		
		if(helper.createFile(inputStream, directory + picture)) {
			dish = dishService.create(dish);
			response.put("success", true);
			response.put("id", dish.getId());
		}
		else response.put("success", false);

		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONArray data = new JSONArray();
		JSONObject response = new JSONObject();
		try {
			LinkedList<Dish> dishes = dishService.retrieveAll();
			for(Dish dish : dishes) {
				JSONObject json = new JSONObject();
				json.put("id", dish.getId());
				json.put("name", dish.getName());
				json.put("description", dish.getDescription());
				json.put("picture", dish.getPicture());
				JSONArray restaurants = new JSONArray();
				for(Restaurant restaurant : dish.getRestaurants()) {
					JSONObject rs = new JSONObject();
					rs.put("id", restaurant.getId());
					rs.put("name", restaurant.getName());
					rs.put("type", restaurant.getType());
					rs.put("address", restaurant.getAddress());
					rs.put("longitude", restaurant.getLongitude());
					rs.put("latitude", restaurant.getLatitude());
					rs.put("avg_price", restaurant.getAvg_price());
					rs.put("url", restaurant.getURL());
					restaurants.put(rs);
				}
				json.put("restaurants", restaurants);
				data.put(json);
			}
			response.put("data", data);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}

/*
	@Autowired DishServiceImpl service;
	@Autowired CharacteristicServiceImpl serviceCharacteristic;
	@Autowired ItemRecommenderCalculatedSimilitude ircs;
	
	@POST
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@FormParam("id") String id) {
		Dish dish = new Dish();
		JSONObject response = new JSONObject();
		try{
			dish = service.retrieve(Long.parseLong(id));
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
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@FormParam("id") String id, @FormParam("name") String name, @FormParam("picture") String picture,
			@FormParam("description") String description) {
		Dish dish = new Dish();
		JSONObject response = new JSONObject();
		try {
			dish = service.retrieve(Long.parseLong(id));
			if(name != null)
				dish.setName(name);
			if(description != null)
				dish.setDescription(description);
			if(picture != null)
				dish.setPicture(picture);
			service.update(dish);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@FormParam("id") String id) {
		Dish dish = new Dish();
		JSONObject response = new JSONObject();
		try {
			dish = service.retrieve(Long.parseLong(id));
			service.delete(dish);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@POST
	@Path("/retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONObject response = new JSONObject();

		try {
			LinkedList<Dish> dishes = service.retrieveAll();
			for(Dish dish : dishes) {
				System.out.println("----------");
				System.out.println("id: " + dish.getId());
				System.out.println("name: " + dish.getName());
				System.out.println("picture: " + dish.getPicture());
				System.out.println("description: " + dish.getDescription());
				System.out.println("characteristics: " + dish.getCharacteristics().size());
				System.out.println("ratings: " +  dish.getRatings());
				System.out.println("clicks: " + dish.getClicks());
				System.out.println("uploads: " + dish.getUploads());
				System.out.println("affinities: "  + dish.getAffinities());
				System.out.println("restaurants: " + dish.getRestaurants());
			}
			
			response.put("success", true);
		} catch(Exception e) {
			response.put("success", false);
		}

		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
 * */