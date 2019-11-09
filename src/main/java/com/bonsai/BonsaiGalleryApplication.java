package com.bonsai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bonsai.model.FileStorageProperties;
import com.bonsai.service.WebCrawler;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class BonsaiGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BonsaiGalleryApplication.class, args);
		new WebCrawler().test();
	}

}
