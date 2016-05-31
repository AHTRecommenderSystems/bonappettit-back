package com.aht.bonappettit.webservices.relationship;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.relationship.Upload;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.bonappettit.serviceimpl.relationship.UploadServiceImpl;

@Component
@Path("/uploadws")
public class UploadWS {
	@Autowired UserServiceImpl userService;
	@Autowired DishServiceImpl dishService;
	@Autowired UploadServiceImpl uploadService;

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(@FormParam("userId") String userId, @FormParam("dishId") String dishId, @FormParam("on") String on) {
		JSONObject response = new JSONObject();
		try {
			User user = userService.retrieve(Long.parseLong(userId));
			Dish dish = dishService.retrieve(Long.parseLong(dishId));
			Upload upload = new Upload(user, dish, on);
			uploadService.create(upload);
			response.put("success", true);
		} catch (Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}
