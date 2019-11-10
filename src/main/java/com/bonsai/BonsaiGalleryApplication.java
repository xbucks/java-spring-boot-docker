package com.bonsai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.bonsai.model.FileStorageProperties;

@Import({
	FileStorageProperties.class
})
@SpringBootApplication
public class BonsaiGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BonsaiGalleryApplication.class, args);
	}
	@Configuration
    @Profile("local")
    @ComponentScan(lazyInit = true)
	static class LocalConfig {
    }
}
