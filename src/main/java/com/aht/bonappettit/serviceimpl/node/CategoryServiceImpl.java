package com.aht.bonappettit.serviceimpl.node;

import java.util.List;
import java.util.LinkedList;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.aht.bonappettit.repository.node.CategoryRepository;
import com.aht.bonappettit.service.node.CategoryService;
import com.aht.bonappettit.domain.node.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired Session session;
	@Autowired CategoryRepository repository;

	public void create(Category category) {
		repository.save(category);
	}

	public Category retrieve(long id) {
		return session.load(Category.class, id);
	}

	public void update(Category category) {
		repository.save(category, category.getId().intValue());
	}

	public void delete(Category category) {
		repository.delete(category);
	}

	public List<Category> retrieveAll() {
		return new LinkedList<Category>(session.loadAll(Category.class));
	}
}
