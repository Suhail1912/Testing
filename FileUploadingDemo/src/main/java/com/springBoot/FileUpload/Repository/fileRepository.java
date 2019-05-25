package com.springBoot.FileUpload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springBoot.FileUpload.Model.FileModel;

public interface fileRepository extends JpaRepository<FileModel, String> {
	
	@Query("select e from FileModel e where e.filename = ?1")
	FileModel getByFileName(String name);
	
	
	@Query("select e from FileModel e where e.id = ?1")
	FileModel getByFileId(String name);
}
