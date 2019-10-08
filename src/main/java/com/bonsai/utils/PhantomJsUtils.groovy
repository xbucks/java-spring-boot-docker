package com.bonsai.utils;

import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

class PhantomJsUtils {
    private static String filePath = 'https://pixabay.com/images/search/bonsai/';
	
	public static Document renderPage(String filePath) {
		System.setProperty("phantom.js.binary.path", 'libs/phantomjs')
		WebDriver ghostDriver = new PhantomJSDriver();
		try{
			ghostDriver.get(filePath);
			return Jsoup.parse(ghostDriver.getPageSource());
		}
		finally {
			ghostDriver.quit();		}
	}
	
	public static Document renderPage(Document doc) {
		String tmpFileName	= '$filePath${Calendar.getInstance().timeInMillis}.html';
		FileUtils.writeStringToFile(tmpFileName, doc.toString(), 'UTF-8');
		return renderPage(tmpFileName);
	}
}