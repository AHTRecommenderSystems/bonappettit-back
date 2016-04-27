package com.aht.bonappettit.webservices.relationship;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.relationship.Click;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.bonappettit.serviceimpl.relationship.ClickServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

@Component
@Path("/clickws")
public class ClickWS {
	@Autowired UserServiceImpl userService;
	@Autowired DishServiceImpl dishService;
	@Autowired ClickServiceImpl clickService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("idUser") String idUser, @FormParam("idDish") String idDish, @FormParam("on") String on) {
		JSONObject response = new JSONObject();
		try {
			User user = userService.retrieve(Long.parseLong(idUser));
			Dish dish = dishService.retrieve(Long.parseLong(idDish));
			Click click = new Click(user, dish, on);
			clickService.create(click);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
}
