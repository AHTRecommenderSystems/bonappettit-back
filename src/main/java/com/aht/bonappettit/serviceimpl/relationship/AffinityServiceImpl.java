package com.aht.bonappettit.serviceimpl.relationship;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.ogm.session.Session;
import com.aht.bonappettit.domain.relationship.Affinity;
import com.aht.bonappettit.service.relationship.AffinityService;
import com.aht.bonappettit.repository.relationship.AffinityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AffinityServiceImpl implements AffinityService {
	@Autowired Session session;
	@Autowired AffinityRepository repository;

	public void create(Affinity affinity) {
		repository.save(affinity);
	}

	public Affinity retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(Affinity affinity) {
		repository.save(affinity, affinity.getId().intValue());
	}

	public void delete(Affinity affinity) {
		repository.delete(affinity);
	}

	public List<Affinity> retrieveAll() {
		return new LinkedList<Affinity>(session.loadAll(Affinity.class));
	}
}
