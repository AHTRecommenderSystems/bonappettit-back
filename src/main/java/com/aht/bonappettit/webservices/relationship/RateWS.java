package com.aht.bonappettit.webservices.relationship;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.bonappettit.serviceimpl.relationship.RateServiceImpl;

@Component
@Path("/ratews")
public class RateWS {
	@Autowired DishServiceImpl dishService;
	@Autowired UserServiceImpl userService;
	@Autowired RateServiceImpl rateService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("on") String on,
			@FormParam("idUser") String idUser,
			@FormParam("idDish") String idDish,
			@FormParam("value") String value) {
		JSONObject response = new JSONObject();
		try {
			User user = userService.retrieve(Long.parseLong(idUser));
			Dish dish = dishService.retrieve(Long.parseLong(idDish));
			Rate rate = new Rate(user, dish, Double.parseDouble(value), on);
			rateService.create(rate);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}
