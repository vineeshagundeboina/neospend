package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neomaxer.neospend.models.auth.FileUpload;

public interface FileUploadRepo extends JpaRepository<FileUpload, Long>{
	
	Optional<FileUpload> findByFileName(String fileName);

}
