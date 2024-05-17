package com.tanuj.aws.bucket;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;

@Component
public class S3Service {
	
	
	@Value("${aws.bucket.name}")
    private String bucketName;
	
	private AmazonS3 s3client;
	
	public S3Service(AmazonS3 s3client) {
		System.out.println("ISNIDE CONSTRUCTOR");
		this.s3client=s3client;
	}
	
	public void createBucket() {
		if(s3client.doesBucketExistV2(bucketName)) {
			System.out.println("BUCKET EXIST");
		}else {
			
			
			Bucket bucket=s3client.createBucket(bucketName);
			System.out.println( bucket.getName()+" "+bucket.getCreationDate());		
		}
		
	}
	
	public List<Bucket> listBucket() {
		List<Bucket> listBucket = s3client.listBuckets();
		listBucket.stream().forEach(b1->
		System.out.println(b1.getName()+" "+b1.getCreationDate()));
		return listBucket;
		
	}
	
	public void uploadFile(String keyName,MultipartFile file) {
		try {
			s3client.putObject(bucketName, keyName, file.getInputStream(), null);
		} catch (SdkClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
