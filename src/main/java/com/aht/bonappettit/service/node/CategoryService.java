package com.aht.bonappettit.service.node;

import java.util.List;
import com.aht.bonappettit.domain.node.Category;

public interface CategoryService {
	public void create(Category category);
	public Category retrieve(long id);
	public void update(Category category);
	public void delete(Category category);
	public List<Category> retrieveAll();
}
