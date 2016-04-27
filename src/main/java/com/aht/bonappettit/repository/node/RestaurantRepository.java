package com.aht.bonappettit.repository.node;

import com.aht.bonappettit.domain.node.Restaurant;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface RestaurantRepository extends GraphRepository<Restaurant> {

}
