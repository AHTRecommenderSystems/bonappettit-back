package com.aht.bonappettit.repository.relationship;

import org.springframework.data.neo4j.repository.GraphRepository;
import com.aht.bonappettit.domain.relationship.Affinity;

public interface AffinityRepository extends GraphRepository<Affinity> {

}
