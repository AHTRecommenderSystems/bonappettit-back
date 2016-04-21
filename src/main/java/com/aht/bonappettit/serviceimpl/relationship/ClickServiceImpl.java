package com.aht.bonappettit.serviceimpl.relationship;

import java.util.LinkedList;
import java.util.List;
import org.neo4j.ogm.session.Session;
import com.aht.bonappettit.domain.relationship.Click;
import com.aht.bonappettit.service.relationship.ClickService;
import com.aht.bonappettit.repository.relationship.ClickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClickServiceImpl implements ClickService {
	@Autowired Session session;
	@Autowired ClickRepository repository;

	public void create(Click click) {
		repository.save(click);
	}

	public Click retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(Click click) {
		repository.save(click, click.getId().intValue());
	}

	public void delete(Click click) {
		repository.delete(click);
	}

	public List<Click> retrieveAll() {
		return new LinkedList<Click>(session.loadAll(Click.class));
	}
}
