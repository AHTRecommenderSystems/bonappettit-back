package com.aht.bonappettit.service.relationship;

import java.util.List;

import com.aht.bonappettit.domain.relationship.Click;

public interface ClickService {
	public void create(Click click);
	public Click retrieve(long id);
	public void update(Click click);
	public void delete(Click click);
	public List<Click> retrieveAll();
}
