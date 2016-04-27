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

import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.relationship.Neighbor;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.bonappettit.serviceimpl.relationship.NeighborServiceImpl;

@Component
@Path("/neighborws")
public class NeighborWS {
	@Autowired UserServiceImpl userService;
	@Autowired NeighborServiceImpl neighborService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("idFirst") String idFirst, @FormParam("idSecond") String idSecond, @FormParam("similitude") String similitude) {
		JSONObject response = new JSONObject();
		try {
			User first = userService.retrieve(Long.parseLong(idFirst)); 
			User second = userService.retrieve(Long.parseLong(idSecond)); 
			Neighbor neighbor = new Neighbor(first, second, Float.parseFloat(similitude));
			neighborService.create(neighbor);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).build();
	}
	
}
