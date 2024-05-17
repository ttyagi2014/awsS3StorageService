package com.tanuj.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tanuj.aws.bucket.S3Service;

@SpringBootApplication
public class AwsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(AwsApplication.class, args);
		S3Service service=ctx.getBean("s3Service", S3Service.class);
		service.createBucket();
		service.listBucket();
	}

}
