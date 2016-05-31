package com.aht.bonappettit.webservices.relationship;

import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.bonappettit.serviceimpl.relationship.RateServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/ratews")
public class RateWS {
	@Autowired DishServiceImpl dishService;
	@Autowired UserServiceImpl userService;
	@Autowired RateServiceImpl rateService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(@FormParam("on") String on,
			@FormParam("idUser") String idUser,
			@FormParam("idDish") String idDish,
			@FormParam("value") String value) {
		System.out.println("valores" + on + ' ' + idUser + ' ' + idDish + ' ' + value);
		JSONObject response = new JSONObject();
		try {
			User user = userService.retrieve(Long.parseLong(idUser));
			Dish dish = dishService.retrieve(Long.parseLong(idDish));
			Rate rate = new Rate(user, dish, Double.parseDouble(value), on);
			rateService.create(rate);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
			exception.printStackTrace();
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}
