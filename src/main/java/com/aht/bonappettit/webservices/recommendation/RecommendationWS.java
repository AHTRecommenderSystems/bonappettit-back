package com.aht.bonappettit.webservices.recommendation;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.aht.api.model.node.Item;
import com.aht.api.recommender.ItemRecommenderCalculatedSimilitude;
import com.aht.bonappettit.domain.node.Characteristic;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.domain.node.Restaurant;
import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.serviceimpl.node.CharacteristicServiceImpl;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.bonappettit.serviceimpl.relationship.RateServiceImpl;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

@Component
@Path("/recommendations")
public class RecommendationWS {
	@Autowired ItemRecommenderCalculatedSimilitude recommender;
	@Autowired DishServiceImpl dishService;
	@Autowired UserServiceImpl userService;
	@Autowired CharacteristicServiceImpl characteristicService;
	@Autowired RateServiceImpl rateService;

	@GET
	@Path("/byUser/{id}/{topN}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommendByUser(@PathParam("id") String id, @PathParam("topN") String n) {
		JSONObject response = new JSONObject();
		try {
			User user = userService.retrieve(Long.parseLong(id));
			List<Rate> ratings = new LinkedList<Rate>();
			for(Rate rating : user.getRatings()) {
				Dish rated = dishService.retrieve(rating.getDish().getId());
				List<Rate> ratedRatings = new LinkedList<Rate>();
				for(Rate r : rated.getRatings()){
					Rate k = rateService.retrieve(r.getId());
					User l = userService.retrieve(r.getUser().getId());
					List<Rate> rl = new LinkedList<Rate>();
					for(Rate o : l.getRatings()){
						rl.add(rateService.retrieve(o.getId()));
					}
					l.setRatings(rl);
					k.setUser(l);
					ratedRatings.add(k);
				}
				rated.setRatings(ratedRatings);
				rating.setDish(rated);
				ratings.add(rating);
			}
			user.setRatings(ratings);
			List<Item> recommendations = recommender.getTopNRecommendationByUser(user, Integer.parseInt(n));
			System.out.println("WEBSERVICE " + recommendations);
			JSONArray data = new JSONArray();
			for (Item reco : recommendations) {
				Dish dish = (Dish) reco;
				JSONObject json = new JSONObject();
				json.put("id", dish.getId());
				json.put("name", dish.getName());
				json.put("description", dish.getDescription());
				json.put("picture", dish.getPicture());
				json.put("averageRating", dish.getAverageRating());
				JSONArray restaurants = new JSONArray();
				for (Restaurant restaurant : dish.getRestaurants()) {
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
			response.put("data", data);
			response.put("success", true);
		} catch(Exception e){
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "GET").build();
	}

	@POST
	@Path("/byItemList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response recommendByItemList(@FormParam("dishes") String dishes) {
		System.out.println(dishes);
		JSONObject dishesJ = new JSONObject(dishes);
		JSONObject response = new JSONObject();
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}

	@GET
	@Path("/byItem/{id}/{topN}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommendByItem(@PathParam("id") String id, @PathParam("topN") String N) {

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

		List<Dish> topNRecommendationByItem = (List<Dish>)(Object)recommender.getTopNRecommendationByItem(dish, Integer.parseInt(N) * 10);
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
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "GET").build();
	}
}
