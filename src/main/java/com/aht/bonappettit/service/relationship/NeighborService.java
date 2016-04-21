package com.aht.bonappettit.service.relationship;

import java.util.List;

import com.aht.bonappettit.domain.relationship.Neighbor;

public interface NeighborService {
	public void create(Neighbor neighbor);
	public Neighbor retrieve(long id);
	public void update(Neighbor neighbor);
	public void delete(Neighbor neighbor);
	public List<Neighbor> retrieveAll();
}
