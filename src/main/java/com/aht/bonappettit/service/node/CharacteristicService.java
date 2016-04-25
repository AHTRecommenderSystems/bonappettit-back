package com.aht.bonappettit.service.node;

import java.util.LinkedList;
import com.aht.bonappettit.domain.node.Characteristic;

public interface CharacteristicService {
	public Characteristic create(Characteristic characteristic);
	public Characteristic retrieve(long id);
	public void update(Characteristic characteristic);
	public void delete(Characteristic characteristic);
	public void delete(long id);
	public LinkedList<Characteristic> retrieveAll();
}
