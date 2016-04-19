package com.aht.recommend;

import com.aht.neo4j.model.node.Item;
import com.aht.neo4j.model.node.User;
import com.aht.neo4j.model.relationship.Affinity;
import com.aht.neo4j.model.relationship.Event;
import com.aht.neo4j.model.relationship.Neighbor;

import java.util.*;

/**
 * Created by azu on 18/04/16.
 */

public class ItemRecommender {
    public Set<Item> getTopNRecommendationsByItem(Item item, int N){
        Set<Item> topNRecommendations = new HashSet<Item>();
        Set<Affinity> affinities = item.getAffinities();
        // Sort Affinities

        for (Affinity affinity: affinities) {
            Item temp = affinity.getFirst();
            if(temp.getId() == item.getId()){
                temp = affinity.getSecond();
            }
            topNRecommendations.add(temp);
        }
        return topNRecommendations;
    }

    public Set<Item> getTopNRecommendationsByUser(User user, int N){
        Set<Neighbor> neighbors = user.getNeighbors();
        // Sort Affinities

        Set<User> users = new HashSet<User>();
        for (Neighbor neighbor: neighbors) {
            User temp = neighbor.getFirst();
            if(temp.getId() == user.getId()){
                temp = neighbor.getSecond();
            }
            users.add(temp);
        }

        Set<Item> topNRecommendations = new HashSet<Item>();
        for(User u: users){
            // getList of items by user event sorted by value
            Set<Event> events = u.getEvents();
            for(Event e: events){
                Item temp = e.getItem();
                topNRecommendations.add(temp);
            }
        }
        return topNRecommendations;
    }
}
