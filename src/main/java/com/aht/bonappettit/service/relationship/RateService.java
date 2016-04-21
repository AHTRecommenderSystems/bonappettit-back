package com.aht.bonappettit.service.relationship;

import java.util.List;

import com.aht.bonappettit.domain.relationship.Rate;

public interface RateService {
	public void create(Rate rate);
	public Rate retrieve(long id);
	public void update(Rate rate);
	public void delete(Rate rate);
	public List<Rate> retrieveAll();
}
