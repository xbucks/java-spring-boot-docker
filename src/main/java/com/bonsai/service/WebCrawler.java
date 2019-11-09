package com.bonsai.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CrawlService")
public class WebCrawler {
	private static final int MAX_DEPTH = 2;

	// Get Path đến Phantom.js
	Path phantomDirectory = Paths.get("src", "main", "resources", "phantomjs-2.1.1-windows", "bin", "phantomjs.exe");
	String phantomPath = phantomDirectory.toFile().getAbsolutePath();
	
	// Get Path đến page.js
	Path pageDirectory = Paths.get("src", "main", "java", "com", "bonsai","service", "page.js");
	String pagePath = pageDirectory.toFile().getAbsolutePath();


	public void test() {
		System.out.println(pagePath);
	}

	private String phantomJSPath = phantomPath;
	private String scriptFile = pagePath;

	@Autowired
	private ImageServiceImpl ImageRepository;

	private int picsNum = 0;

	public WebCrawler() {
	}

	public List<String> getPageLinks(String searchTerm) throws InterruptedException {
		List<String> allImages = new ArrayList<String>();
		if (searchTerm.isEmpty()) {
			//Default search bonsai
			searchTerm = "bonsai";
		}
		try {
			String urlParameter = "https://pixabay.com/images/search/" + searchTerm;
			String querySelector = "a img[src^=https://cdn.pixabay]";

			Process process = Runtime.getRuntime()
					.exec(phantomJSPath + " " + scriptFile + " " + urlParameter + " " + searchTerm);
			process.waitFor();

			Document doc = Jsoup.parse(new File(searchTerm + ".html"), "UTF-8"); // output.html is created by
																					// phantom.js, same path as page.js

			int pageSize = doc.select(querySelector).size();

			for (Element element : doc.select(querySelector)) {
				picsNum++;
				allImages.add(element.attr("src"));
//				System.out.println(element.attr("src") + "-> IMG: " + picsNum);
			}
			System.out.println("Number of results: " + pageSize);
		} catch (IOException e) {
			System.out.println("For '" + "':" + e.getMessage());
		}
		return allImages;
	}
}
