package com.neomaxer.neospend.service.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neomaxer.neospend.models.auth.FileUpload;
import com.neomaxer.neospend.repositories.masters.FileUploadRepo;

public class FileUploadService {

	@Autowired
	private FileUploadRepo fileUploadRepo;

	public FileUpload storeFile(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileUpload uploadedFile = new FileUpload();
		uploadedFile.setFileName(fileName);
		uploadedFile.setFileData(file.getBytes());
		return fileUploadRepo.save(uploadedFile);
	}

}
