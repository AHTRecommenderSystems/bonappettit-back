package com.aht.bonappettit.webservices.node;


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
import java.util.LinkedList;
import org.json.JSONObject;
import org.json.JSONArray;

@Component
@Path("/dishws")
public class DishWS {
	@SuppressWarnings("unused")
	private static final String directory = "/Users/hector9317/Documents/workspace/bonappettit-back/src/main/resources/";
	
	@Autowired DishServiceImpl dishService;

	/*
	public boolean createFile(InputStream inputStream, String directory) {
		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream out = new FileOutputStream(new File(directory));
			out = new FileOutputStream(new File(directory));
			while ((read = inputStream.read(bytes)) != -1)
				out.write(bytes, 0, read);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) { return false; }
	}

	public String hashFunction(String fileName, String dishName) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date = dateFormat.format(new Date());
		String name = fileName + dishName + date; 
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(name.getBytes(), 0, name.length());
			String hashed = new BigInteger(1, md.digest()).toString(64);
			return hashed;
		} catch(Exception exception) {
			return null;
		}
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
			@FormParam("name") String name,
			@FormParam("description") String description,
			@FormDataParam("file") InputStream inputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
			
		Dish dish = new Dish();
		JSONObject response = new JSONObject();
		try {
			if(name != null)
				dish.setName(name);
			if(description != null)
				dish.setDescription(description);
			String fileName = hashFunction(name, fileDetail.getFileName());
			if(fileName != null && createFile(inputStream, directory + fileName)) {
				System.out.println("Se creó el archivo, ahora el nodo");
				dish.setPicture(fileName);
			}
			dish = dishService.create(dish);
			if(dish.getId() != null){
				response.put("id", dish.getId());
				response.put("success", true);
			}
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}

	 */

	
	@POST
	@Path("/create2")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
			@FormParam("name") String name, 
			@FormParam("picture") String picture,
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
