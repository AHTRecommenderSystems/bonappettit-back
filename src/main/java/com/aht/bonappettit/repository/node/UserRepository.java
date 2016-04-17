package com.aht.bonappettit.repository.node;

import com.aht.bonappettit.domain.node.User;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User> {

}
