package com.aht.bonappettit.repository.node;

import com.aht.bonappettit.domain.node.Dish;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface DishRepository extends GraphRepository<Dish> {
}
