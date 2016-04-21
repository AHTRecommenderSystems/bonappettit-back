package com.aht.bonappettit.repository.relationship;

import com.aht.bonappettit.domain.relationship.Neighbor;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface NeighborRepository  extends GraphRepository<Neighbor> {

}
