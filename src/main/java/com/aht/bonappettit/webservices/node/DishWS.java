package com.aht.bonappettit.webservices.node;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.InputStream;
import java.util.LinkedList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.node.RestaurantServiceImpl;
import com.aht.bonappettit.domain.node.Characteristic;
import com.aht.bonappettit.domain.node.Restaurant;
import com.sun.jersey.multipart.FormDataParam;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.utils.FileHelper;
import org.json.JSONArray;
import org.json.JSONObject;

@Component
@Path("/dishws")
public class DishWS {
	private static final String directory = "/home/hector9317/workspace/bonappettit-back/src/main/webapp/images/";
	@Autowired FileHelper helper;
	@Autowired DishServiceImpl service;
	@Autowired RestaurantServiceImpl restaurantService;

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
		JSONObject response;
		String received = content.getFileName();
		String ext = received.substring(received.lastIndexOf("."), received.length());
		try {
			response = new JSONObject();
			String filename = helper.hashFunction(received, name) + ext;
			if(description != null)
				dish.setDescription(description);
			if(name != null)
				dish.setName(name);
			if(helper.createFile(inputStream, directory + filename)) {
				dish = service.create(dish);
				response.put("id", dish.getId());
			}
			response.put("success", true);
		} catch(Exception exception) {
			response = new JSONObject();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
 	public Response retrieve(@FormParam("id") String id) {
		Dish dish = new Dish();
		JSONObject response;
		JSONArray restaurants = new JSONArray();
		JSONArray characteristics = new JSONArray();
		try {
			response = new JSONObject();
			dish = service.retrieve(Long.parseLong(id));
			response.put("name", dish.getName());
			response.put("description", dish.getDescription());
			response.put("picture", dish.getPicture());
			response.put("averageRating", dish.getAverageRating());
			for(Characteristic c : dish.getCharacteristics()) {
				JSONObject characteristic = new JSONObject();
				characteristic.put("id", c.getId());
				characteristic.put("name", c.getName());
				characteristic.put("type", c.getType());
				characteristics.put(characteristic);
			}
			for(Restaurant r : dish.getRestaurants()) {
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
			response.put("characteristics", characteristics);
			response.put("restaurants", restaurants);
			response.put("success", true);
		} catch(Exception exception) {
			response = new JSONObject();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response update(
			@FormDataParam("id") String id,
			@FormDataParam("name") String name,
			@FormDataParam("file") InputStream inputStream,
			@FormDataParam("description") String description,
			@FormDataParam("file") FormDataContentDisposition content) {
		
		Dish dish = new Dish();
		JSONObject response;
		String received = content.getFileName();
		String ext = received.substring(received.lastIndexOf("."), received.length());
		try {
			response = new JSONObject();
			dish = service.retrieve(Long.parseLong(id));
			String filename = helper.hashFunction(received, name) + ext;
			if(description != null)
				dish.setDescription(description);
			if(name != null)
				dish.setName(name);
			if(helper.createFile(inputStream, directory + filename) && helper.deleteFile(directory, dish.getPicture())) {
				service.update(dish);
				response.put("success", true);	
			}
		} catch(Exception exception) {
			response = new JSONObject();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@FormParam("id") String id) {
		Dish dish = new Dish();
		JSONObject response;
		try {
			response = new JSONObject();
			dish = service.retrieve(Long.parseLong(id));
			service.delete(dish);
			response.put("success", true);
		} catch(Exception exception) {
			response = new JSONObject();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@POST
	@Path("retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONArray data = new JSONArray();
		JSONObject response = new JSONObject();
		try {
			LinkedList<Dish> dishes = service.retrieveAll();
			for(Dish dish : dishes) {
				JSONObject json = new JSONObject();
				json.put("id", dish.getId());
				json.put("name", dish.getName());
				json.put("description", dish.getDescription());
				json.put("picture", dish.getPicture());
				json.put("averageRating", dish.getAverageRating());
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
	
	
	@POST
	@Path("/servedAt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response servetAt(@FormParam("idUser") String idUser, @FormParam("idRestaurant") String idRestaurant) {
		JSONObject response;
		try {
			response = new JSONObject();
			Dish dish = new Dish();
			Restaurant restaurant = restaurantService.retrieve(Long.parseLong(idRestaurant));
			dish.addRestaurant(restaurant);
			restaurant.addDish(dish);
			response.put("success", true);
		} catch(Exception exception) { 
			response = new JSONObject();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}
