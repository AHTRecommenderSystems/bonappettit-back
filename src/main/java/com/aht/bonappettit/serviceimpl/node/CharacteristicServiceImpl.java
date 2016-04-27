package com.aht.bonappettit.serviceimpl.node;

import java.util.LinkedList;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.aht.bonappettit.repository.node.CharacteristicRepository;
import com.aht.bonappettit.service.node.CharacteristicService;
import com.aht.bonappettit.domain.node.Characteristic;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {
	@Autowired Session session;
	@Autowired CharacteristicRepository repository;

	public Characteristic create(Characteristic characteristic) {
		return repository.save(characteristic);
	}

	public Characteristic retrieve(long id) {
		return session.load(Characteristic.class, id);
	}

	public void update(Characteristic characteristic) {
		repository.save(characteristic, characteristic.getId().intValue());
	}

	public void delete(Characteristic characteristic) {
		repository.delete(characteristic);
	}
	
	public void delete(long id) {
		repository.delete(id);
	}

	public LinkedList<Characteristic> retrieveAll() {
		return new LinkedList<Characteristic>(session.loadAll(Characteristic.class));
	}
}
