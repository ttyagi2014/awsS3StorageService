package com.tanuj.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.Bucket;
import com.tanuj.aws.bucket.S3Service;

@RestController
@RequestMapping("/fileStorage")
public class StorageController {
	@Autowired
	S3Service s3service;
	
	@GetMapping("/listbuckets")
	public List<Bucket> getAllBucketDetails(){
		return s3service.listBucket();
	}
	
	@PostMapping(value = "/fileupload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadFiles(@RequestParam("file") MultipartFile file) {
		System.out.println("INSIDE");
		System.out.println(file.getName()+" "+file.getOriginalFilename());
		s3service.uploadFile(file.getOriginalFilename(), file);
	}

}
