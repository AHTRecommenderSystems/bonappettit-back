package com.aht.bonappettit.webservices.node;

import javax.annotation.Generated;
import javax.ws.rs.*;
import java.io.InputStream;
import java.util.LinkedList;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.aht.bonappettit.serviceimpl.node.CharacteristicServiceImpl;
import com.sun.media.jfxmedia.Media;
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
@Path("/dishes")
public class DishWS {
	private static final String directory = "/home/hector9317/workspace/bonappettit-back/src/main/webapp/images/";
	@Autowired FileHelper helper;
	@Autowired DishServiceImpl service;
	@Autowired CharacteristicServiceImpl characteristicService;
	@Autowired RestaurantServiceImpl restaurantService;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll(){
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
			exception.printStackTrace();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "GET").build();
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response crear(
			@FormParam("name") String name,
			@FormParam("description") String description,
			@FormParam("picture") String picture,
			@FormParam("userId") String userId) {
		JSONObject response = new JSONObject();
		Dish dish = new Dish();
		try{
			if(name != null)
				dish.setName(name);
			if(description != null)
				dish.setDescription(description);
			if(picture != null)
				dish.setPicture(picture);
			//if(userId != null)
			//TODO: Who has uploaded this dish? add userId to "uploads"
			dish = service.create(dish);
			response.put("id",dish.getId());
			response.put("success", true);
		}catch(Exception e){
			response.put("success",false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@PathParam("id") String id) {
		Dish dish = new Dish();
		JSONObject data = new JSONObject();
		JSONObject response;
		JSONArray restaurants = new JSONArray();
		JSONArray characteristics = new JSONArray();
		try {
			response = new JSONObject();
			dish = service.retrieve(Long.parseLong(id));
			data.put("id", dish.getId());
			data.put("name", dish.getName());
			data.put("description", dish.getDescription());
			data.put("picture", dish.getPicture());
			data.put("averageRating", dish.getAverageRating());
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
			data.put("characteristics", characteristics);
			data.put("restaurants", restaurants);
			response.put("data",data);
			response.put("success", true);
		} catch(Exception exception) {
			response = new JSONObject();
			response.put("success", false);
			exception.printStackTrace();
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
			@FormParam("picture") String picture,
			@FormParam("description") String description) {
		Dish dish = new Dish();
		JSONObject response;
		try {
			response = new JSONObject();
			dish = service.retrieve(Long.parseLong(id));
			if(description != null)
				dish.setDescription(description);
			if(name != null)
				dish.setName(name);
			if(picture != null)
				dish.setPicture(picture);
			service.update(dish);
			response.put("success", true);
		} catch(Exception exception) {
			response = new JSONObject();
			response.put("success", false);
			exception.printStackTrace();
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "PUT").build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response delete(@PathParam("id") String id) {
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
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "DELETE").build();
	}




	@POST
	@Path("/servedAt")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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

	@POST
	@Path("/characteristics")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addCharacteristic(@FormParam("idCategory") String idCategory, @FormParam("idDish") String idDish){
		JSONObject response = new JSONObject();
		try{
			Dish dish = service.retrieve(Long.parseLong(idDish));
			Characteristic characteristic = characteristicService.retrieve(Long.parseLong(idCategory));
			dish.addCharacteristic(characteristic);
			service.update(dish);
			response.put("status",true);
		} catch(Exception e){
			response.put("status",false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@POST
	@Path("/characteristics/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response removeCharacteristic(@FormParam("idCategory") String idCategory, @FormParam("idDish") String idDish){
		JSONObject response = new JSONObject();
		try{
			Dish dish = service.retrieve(Long.parseLong(idDish));
			Characteristic characteristic = characteristicService.retrieve(Long.parseLong(idCategory));
			dish.removeCharacteristic(characteristic);
			service.update(dish);
			response.put("status",true);
		} catch(Exception e){
			response.put("status",false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

}
