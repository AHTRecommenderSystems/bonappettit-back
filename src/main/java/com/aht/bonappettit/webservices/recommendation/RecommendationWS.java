package com.aht.bonappettit.webservices.recommendation;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.aht.api.recommender.ItemRecommenderCalculatedSimilitude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

@Component
@Path("recommendationws")
public class RecommendationWS {
	@Autowired ItemRecommenderCalculatedSimilitude recommender;
	
	@POST
	@Path("recommendByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommendByUser(@FormParam("id") String id, @FormParam("topN") String n) {
		JSONObject response = new JSONObject();
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("recommendByItemList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommendByItemList() {
		JSONObject response = new JSONObject();
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}
