package com.springboot.api.book.controller;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.api.book.helper.FileUploadHelper;

@RestController
public class FileController {
	@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping("upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		// System.out.println(file.getSize());
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
			}
			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Only JPEG content type are allowed");
			}
			boolean f = fileUploadHelper.uploadFile(file);
			if (f) {
				// return ResponseEntity.ok("File Uploaded sucessfully");
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
						.path(file.getOriginalFilename()).toUriString());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(file.getBytes());
		return ResponseEntity.ok("Working");
	}

}
