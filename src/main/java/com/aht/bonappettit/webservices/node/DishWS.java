package com.aht.bonappettit.webservices.node;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.aht.api.recommender.ItemRecommenderCalculatedSimilitude;
import com.aht.bonappettit.domain.node.Characteristic;
import com.aht.bonappettit.serviceimpl.node.CharacteristicServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.aht.bonappettit.utils.FileHelper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;

@Component
@Path("/dishws")
public class DishWS {
	private static final String directory = "/Users/hector9317/Documents/workspace/bonappettit-back/src/main/resources/";
	
	@Autowired DishServiceImpl service;
	@Autowired CharacteristicServiceImpl serviceCharacteristic;
	@Autowired FileHelper helper;
	@Autowired ItemRecommenderCalculatedSimilitude ircs;

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response create(
			@FormDataParam("name") String name,
			@FormDataParam("description") String description,
			@FormDataParam("file") InputStream inputStream,
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
			dish = service.create(dish);
			response.put("success", true);
			response.put("id", dish.getId());
		}
		else response.put("success", false);
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
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
			service.retrieveAll();


			response.put("success", true);
		} catch(Exception e) {
			response.put("success", false);
		}


		/*
				JSONArray dishes = new JSONArray();

		try {
			LinkedList<Dish> dishNodes = service.retrieveAll();
			dishes.put(dishNodes);
			response.put("dishes", dishes);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
*/

		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}
