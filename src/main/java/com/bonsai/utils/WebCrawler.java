package com.bonsai.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

public class WebCrawler {

	// Get Path đến Phantom.js
	Path phantomDirectory = Paths.get("src", "main", "resources", "phantomjs-2.1.1-windows", "bin", "phantomjs.exe");
	private String phantomJSPath = phantomDirectory.toFile().getAbsolutePath();
	
	// Get Path đến page.js
	Path pageDirectory = Paths.get("src", "main", "java", "com", "bonsai","utils", "page.js");
	private String scriptFile = pageDirectory.toFile().getAbsolutePath();

	public WebCrawler() {
	}

	public List<String> getPageLinks(String searchTerm) throws InterruptedException, IOException {
		List<String> allImages = new ArrayList<String>();
		if (searchTerm.isEmpty()) {
			//Default search bonsai
			searchTerm = "bonsai";
		}
		String urlParameter = "https://pixabay.com/images/search/" + searchTerm;
		String querySelector = "a img[src^=https://cdn.pixabay]";

		Process process = Runtime.getRuntime()
				.exec(phantomJSPath + " " + scriptFile + " " + urlParameter + " " + searchTerm);
		process.waitFor();

		Document doc = Jsoup.parse(new File(searchTerm + ".html"), "UTF-8"); // output.html is created by
																				// phantom.js, same path as page.js
		
		Elements result = doc.select(querySelector);
		int pageSize = result.size();
		
		for (int i=0;i<pageSize;i++) {
			allImages.add(result.get(i).attr("src"));
		}
		System.out.println("Number of results: " + pageSize);
		return allImages;
	}
}
