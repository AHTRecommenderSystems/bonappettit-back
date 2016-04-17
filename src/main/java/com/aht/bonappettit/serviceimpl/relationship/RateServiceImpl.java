package com.aht.bonappettit.serviceimpl.relationship;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aht.bonappettit.domain.relationship.Rate;
import com.aht.bonappettit.repository.relationship.RateRepository;
import com.aht.bonappettit.service.relationship.RateService;

@Service
public class RateServiceImpl implements RateService {
	@Autowired Session session;
	@Autowired RateRepository repository;

	public void create(Rate rate) {
		repository.save(rate);
	}

	public Rate retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(Rate rate) {
		repository.save(rate, rate.getId().intValue());
	}

	public void delete(Rate rate) {
		repository.delete(rate);
	}

	public List<Rate> retrieveAll() {
		return new LinkedList<Rate>(session.loadAll(Rate.class));
	}
}
