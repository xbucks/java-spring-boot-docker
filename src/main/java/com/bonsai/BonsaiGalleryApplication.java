package com.bonsai;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bonsai.model.FileStorageProperties;
import com.bonsai.model.Image;
import com.bonsai.service.ImageServiceImpl;
import com.bonsai.service.WebCrawler;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class BonsaiGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BonsaiGalleryApplication.class, args);
		// Start from index 0
		Image img = new Image(
    			"img5",
    		    "Bonsai_"+5,
    		    "All bonsai",
    		    "This is the web version of the Bonsai Gallery. You can sign up for daily delivery here … ",
    		    "This is the web version of the Bonsai Gallery. You can sign up for daily delivery here. The U.S. economy grew at a 2.1% pace in the second quarter, a marked slowdown from earlier in the year. Jeff Sparshott and Greg Ip here to take you through",
    		    "src",
    		    "1",
    		    "QuanDoan");
    	img.setCreated_at(new Date());
    	img.setLast_modified(new Date());
    	img.setHighlights(true);
    	img.setViews(100);
		new WebCrawler().getPageLinks(0);
	}

}
