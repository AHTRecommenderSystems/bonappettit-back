package com.aht.bonappettit.serviceimpl.relationship;

import java.util.List;
import java.util.LinkedList;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aht.bonappettit.repository.relationship.NeighborRepository;
import com.aht.bonappettit.service.relationship.NeighborService;
import com.aht.bonappettit.domain.relationship.Neighbor;

@Service
public class NeighborServiceImpl implements NeighborService {
	@Autowired Session session;
	@Autowired NeighborRepository repository;

	public void create(Neighbor neighbor) {
		repository.save(neighbor);
	}

	public Neighbor retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(Neighbor neighbor) {
		repository.save(neighbor, neighbor.getId().intValue());
	}

	public void delete(Neighbor neighbor) {
		repository.delete(neighbor);
	}

	public List<Neighbor> retrieveAll() {
		return new LinkedList<Neighbor>(session.loadAll(Neighbor.class));
	}
}
