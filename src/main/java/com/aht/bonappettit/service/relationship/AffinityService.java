package com.aht.bonappettit.service.relationship;

import java.util.List;

import com.aht.bonappettit.domain.relationship.Affinity;

public interface AffinityService {
	public void create(Affinity affinity);
	public Affinity retrieve(long id);
	public void update(Affinity affinity);
	public void delete(Affinity affinity);
	public List<Affinity> retrieveAll();
}
