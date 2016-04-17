package com.aht.bonappettit.serviceimpl.relationship;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aht.bonappettit.domain.relationship.Upload;
import com.aht.bonappettit.repository.relationship.UploadRepository;
import com.aht.bonappettit.service.relationship.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired Session session;
	@Autowired UploadRepository repository;

	public void create(Upload upload) {
		repository.save(upload);
	}

	public Upload retrieve(long id) {
		return repository.findOne(id);
	}

	public void update(Upload upload) {
		repository.save(upload, upload.getId().intValue());
	}

	public void delete(Upload upload) {
		repository.delete(upload);
	}

	public List<Upload> retrieveAll() {
		return new LinkedList<Upload>(session.loadAll(Upload.class));
	}
}
