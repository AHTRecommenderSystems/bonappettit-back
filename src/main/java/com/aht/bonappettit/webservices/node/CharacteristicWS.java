package com.aht.bonappettit.webservices.node;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.LinkedList;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.aht.bonappettit.domain.node.Characteristic;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.serviceimpl.node.CharacteristicServiceImpl;

import org.json.JSONArray;
import org.json.JSONObject;

@Component
@Path("/characteristicws")
public class CharacteristicWS {
	@Autowired CharacteristicServiceImpl service;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
			@FormParam("type") String type,
			@FormParam("name") String name) {
		JSONObject response = new JSONObject();
		Characteristic characteristic = new Characteristic();
		try {
			if(name != null)
				characteristic.setName(name);
			if(type != null)
				characteristic.setType(type);
			characteristic = service.create(characteristic);
			if(characteristic.getId() != null) {
				response.put("success", true);
				response.put("id", characteristic.getId());
			}
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@FormParam("id") String id) {
		JSONObject response = new JSONObject();
		Characteristic characteristic = new Characteristic();
		
		try {
			characteristic = service.retrieve(Long.parseLong(id));
			if(characteristic.getName() != null)
				response.put("name", characteristic.getName());
			if(characteristic.getType() != null)
				response.put("type", characteristic.getType());
			response.put("success", true);
		} catch (Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@FormParam("id") String id, 
			@FormParam("name") String name,
			@FormParam("type") String type) {
		JSONObject response = new JSONObject();
		Characteristic characteristic = new Characteristic();
		try {
			characteristic = service.retrieve(Long.parseLong(id));
			if(name != null)
				characteristic.setName(name);
			if(type != null)
				characteristic.setType(type);
			service.update(characteristic);
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
		JSONObject response = new JSONObject();
		Characteristic characteristic = new Characteristic();
		try {
			characteristic = service.retrieve(Long.parseLong(id));
			service.delete(characteristic);
			response.put("success", true);
		} catch (Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONArray data = new JSONArray();
		JSONObject response = new JSONObject();
		try {
			LinkedList<Characteristic> results = service.retrieveAll();
			for(Characteristic c: results){
				JSONObject characteristic = new JSONObject();
				characteristic.put("id", c.getId());
				characteristic.put("name", c.getName());
				characteristic.put("type", c.getType());
				JSONArray dishes = new JSONArray();
				for(Dish d : c.getDishes()) {
					JSONObject dish = new JSONObject();
					dish.put("id", d.getId());
					dish.put("name", d.getName());
					dish.put("picture", d.getPicture());
					dish.put("description", d.getDescription());
					dishes.put(dish);
				}
				characteristic.put("dishes", dishes);
				data.put(characteristic);
			}
			response.put("data", data);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	
	/*
	@POST
	@Path("/retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONObject response = new JSONObject();
		JSONArray characteristics = new JSONArray();
		try{
			LinkedList<Characteristic> characters = service.retrieveAll();
			characteristics.put(characters);
			response.put("characteristics", characteristics);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	*/
	
}
