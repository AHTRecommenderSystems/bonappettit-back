package com.aht.bonappettit.webservices.recommendation;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.aht.api.model.node.Item;
import com.aht.api.recommender.ItemRecommenderCalculatedSimilitude;
import com.aht.bonappettit.domain.node.Characteristic;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.Restaurant;
import com.aht.bonappettit.serviceimpl.node.CharacteristicServiceImpl;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

@Component
@Path("recommendationws")
public class RecommendationWS {
	@Autowired ItemRecommenderCalculatedSimilitude recommender;
	@Autowired DishServiceImpl dishService;
	@Autowired CharacteristicServiceImpl characteristicService;

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

	@POST
	@Path("recommendByItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommendByItem(String id) {

		Dish dish = dishService.retrieve(Long.parseLong(id));
		List<Characteristic> characteristicList = new LinkedList<Characteristic>();
		for(Characteristic characteristic : dish.getCharacteristics()) {
			Characteristic ch = characteristicService.retrieve(characteristic.getId());
			List<Dish> dishList = new LinkedList<Dish>();
			for(Dish neighbor : ch.getDishes()) {
				dishList.add(dishService.retrieve(neighbor.getId()));
			}
			ch.setDishes(dishList);
			characteristicList.add(ch);
		}
		dish.setCharacteristics(characteristicList);

		List<Dish> topNRecommendationByItem = (List<Dish>)(Object)recommender.getTopNRecommendationByItem(dish, 10);
		System.out.println(topNRecommendationByItem);

		JSONArray data = new JSONArray();
		JSONObject response = new JSONObject();
		try{
			for(Dish reco : topNRecommendationByItem) {
				if(reco.getId() != Long.parseLong(id)) {
					JSONObject json = new JSONObject();
					json.put("id", reco.getId());
					json.put("name", reco.getName());
					json.put("description", reco.getDescription());
					json.put("picture", reco.getPicture());
					json.put("averageRating", reco.getAverageRating());
					JSONArray restaurants = new JSONArray();
					for (Restaurant restaurant : reco.getRestaurants()) {
						JSONObject rs = new JSONObject();
						rs.put("id", restaurant.getId());
						rs.put("name", restaurant.getName());
						rs.put("type", restaurant.getType());
						rs.put("address", restaurant.getAddress());
						rs.put("longitude", restaurant.getLongitude());
						rs.put("latitude", restaurant.getLatitude());
						rs.put("avg_price", restaurant.getAvg_price());
						rs.put("url", restaurant.getURL());
						restaurants.put(rs);
					}
					json.put("restaurants", restaurants);
					data.put(json);
				}
			}
			response.put("data", data);
			response.put("success", true);
		}catch(Exception e){
			response.put("status",false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
}
