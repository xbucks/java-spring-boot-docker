package com.bonsai.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonsai.model.Image;

@Service("CrawlService")
public class WebCrawler {
	private static final int MAX_DEPTH = 2;
	private static String phantomJSPath = "C:\\Users\\ADMIN\\Downloads\\Compressed\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";
	private static String scriptFile = "C:\\Users\\ADMIN\\Documents\\workspace-spring-tool-suite-4-4.1.2.RELEASE\\BonsaiGallery\\src\\main\\java\\com\\bonsai\\service\\page.js";
	private String searchTerm = "bonsai";
	
	@Autowired
	private ImageServiceImpl ImageRepository;
	
	private int picsNum = 0;
	
//	private HashSet<String> links;
	
	public WebCrawler() {
//		links = new HashSet<String>();
	}
	
	
	public void getPageLinks(int depth) {
		if(depth < MAX_DEPTH) {
			try {
				

//				searchTerm += "/?pagi=2";
			    String urlParameter = "https://pixabay.com/images/search/" + searchTerm;
			    String querySelector = "a img[src^=https://cdn.pixabay]";

			    Process process = Runtime.getRuntime().exec(phantomJSPath + " " + scriptFile + " " + urlParameter + " " + searchTerm);
			    process.waitFor();

			    Document doc = Jsoup.parse(new File(searchTerm + ".html"),"UTF-8"); // output.html is created by phantom.js, same path as page.js

			    int pageSize = doc.select(querySelector).size(); 
//			    depth ++;
			    
			    for (Element element : doc.select(querySelector)) {
			    	picsNum ++;
			    	pageSize --;
			    	Image img = new Image(
			    			"img"+picsNum,
			    		    "Bonsai_"+picsNum,
			    		    "All bonsai",
			    		    "This is the web version of the Bonsai Gallery. You can sign up for daily delivery here … ",
			    		    "This is the web version of the Bonsai Gallery. You can sign up for daily delivery here. The U.S. economy grew at a 2.1% pace in the second quarter, a marked slowdown from earlier in the year. Jeff Sparshott and Greg Ip here to take you through",
			    		    element.attr("src"),
			    		    "1",
			    		    "QuanDoan");
			    	img.setCreated_at(new Date());
			    	img.setLast_modified(new Date());
			    	img.setHighlights(true);
			    	img.setViews(100);
			    	try {
			    		ImageRepository.getAllImage();
			    	}
			    	catch(NullPointerException ex) {
			    		System.out.println(ex);
			    	}
			    	System.out.println(element.attr("src") + "-> IMG: " + picsNum);
			    }
//			    if(pageSize == 0) {
//			    	
//			    	getPageLinks(depth);
//			    	
//			    }
			    System.out.println("Number of results: " + pageSize);
			}catch(IOException e) {
				System.out.println("For '" + "':" + e.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
