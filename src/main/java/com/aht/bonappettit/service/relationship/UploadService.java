package com.aht.bonappettit.service.relationship;

import java.util.List;

import com.aht.bonappettit.domain.relationship.Upload;

public interface UploadService {
	public void create(Upload upload);
	public Upload retrieve(long id);
	public void update(Upload upload);
	public void delete(Upload upload);
	public List<Upload> retrieveAll();
}
