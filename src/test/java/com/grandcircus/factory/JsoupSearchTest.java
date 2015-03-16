package com.grandcircus.factory;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

public class JsoupSearchTest {

	
	
	@Test
	public void objectAndPropertyInstantiation() throws IOException {

		// Test instantiation
		JsoupSearch testObject = new JsoupSearch("http://google.com");

		// There will be 3 properties instantiated:
		// (1) String called mWebAddress
		boolean a = testObject.mWebAddress instanceof String;
		
		// (2) String called mWebPageText
		boolean b = testObject.mWebPageText instanceof String;
		
		// (3) ArrayList called mPageIndex
		boolean c = testObject.mPageIndex instanceof ArrayList;	
		
		// (4) Constructor worked
		boolean d = testObject instanceof JsoupSearch;
		
		// (2) String called mWebPageHTML
		boolean e = testObject.mWebPageHTML instanceof String;
		
		if (!( a && b && c && d && e)) {
			fail();			
		}


	}

	@Test
	public void streamPage() throws IOException {
		
		JsoupSearch testObject = new JsoupSearch("http://google.com");
		if (testObject.mWebPageText.length() == 0 || testObject.mWebPageHTML.length() == 0) {
			fail();
		}
	}

	@Test
	public void indexPage() throws IOException {
		
		JsoupSearch testObject = new JsoupSearch("http://google.com");
		
		if (testObject.mPageIndex.size() == 0) {
			fail();
		}
	}
	
	@Test
	public void binarySearch() throws IOException {
		
		JsoupSearch testObject = new JsoupSearch("http://google.com");
		
		if (testObject.binarySearch("Grand Circus")) {
			fail();
		}
	}
	
	
	@Test
	public void updateFile() throws IOException {
		
		JsoupSearch testObject = new JsoupSearch("http://google.com");
		
		String highlightTest = "<span style=\"background-color: #FFFF00\">google</span>";
		testObject.updateFile("google");
		if (!(testObject.mWebPageHTML.contains(highlightTest))) {
			fail();
		}
	}
	
	@Test
	public void createFile() throws IOException {
		
		JsoupSearch testObject = new JsoupSearch("http://www.businessinsider.com/google-firefox-message-yahoo-search-share-decline-2015-3");
		testObject.createFile();
		Path path = Paths.get("HTML/google-firefox-message-yahoo-search-share-decline-2015-3.html");
		if (!(Files.exists(path))){
			fail();
		}
		
		
	}
	
	@Test
	public void nameFile() throws IOException {
		String article = "https://medium.com/geeks-bearing-gifts/advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86";
		JsoupSearch testObject = new JsoupSearch(article);
		String articleTitle = "advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86+JSoup.html";
		if (!(testObject.nameFile(article).equals(articleTitle))) {
			fail();
		}
	}
	
	@Test
	public void search() throws IOException {
		String article = "https://medium.com/geeks-bearing-gifts/advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86";
		JsoupSearch testObject = new JsoupSearch(article);
		ArrayList<String> testArray = new ArrayList<String>();
		testArray.add("eyeballs");
		testArray.add("impressions");
		testObject.search(testArray);
		
		String articleTitle = "advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86+JSoup.html";
		if (!(testObject.nameFile(article).equals(articleTitle))) {
			fail();
		}
	}
	
}
