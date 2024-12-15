package com.neomaxer.neospend.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neomaxer.neospend.models.auth.FileUpload;
import com.neomaxer.neospend.service.common.FileUploadService;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

	FileUploadService fileUploadService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			FileUpload uploadedFile = fileUploadService.storeFile(file);
			return ResponseEntity.ok("File uploaded successfully with ID: " + uploadedFile.getId());
		} catch (IOException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload the file: " + ex.getMessage());
		}
	}
}
