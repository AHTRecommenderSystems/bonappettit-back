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
import com.aht.bonappettit.domain.relationship.Affinity;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.relationship.AffinityServiceImpl;

@Component
@Path("/affinityws")
public class AffinityWS {
	@Autowired DishServiceImpl dishService;
	@Autowired AffinityServiceImpl affinityService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("idFirst") String idFirst, @FormParam("idSecond") String idSecond, @FormParam("similitude") String similitude) {
		JSONObject response = new JSONObject();
		try {
			Dish first = dishService.retrieve(Long.parseLong(idFirst));
			Dish second = dishService.retrieve(Long.parseLong(idSecond));
			Affinity affinity = new Affinity(first, second, Float.parseFloat(similitude));
			affinityService.create(affinity);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}

