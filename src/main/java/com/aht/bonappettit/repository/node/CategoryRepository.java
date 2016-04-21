package com.aht.bonappettit.repository.node;

import com.aht.bonappettit.domain.node.Category;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CategoryRepository extends GraphRepository<Category> {

}
