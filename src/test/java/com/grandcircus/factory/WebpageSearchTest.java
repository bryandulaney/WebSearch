package com.grandcircus.factory;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

public class WebpageSearchTest {

	
	
	@Test
	public void objectAndPropertyInstantiation() throws IOException {

		// Test instantiation
		WebpageSearch testObject = new WebpageSearch("http://google.com");

		// There will be 3 properties instantiated:
		// (1) String called mWebAddress
		boolean a = testObject.mWebAddress instanceof String;
		// (2) String called mWebPage
		boolean b = testObject.mWebPage instanceof String;
		
		// (3) ArrayList called mPageIndex
		boolean c = testObject.mPageIndex instanceof ArrayList;	
		
		// (4) Constructor worked
		boolean d = testObject instanceof WebpageSearch;
		
		if (!( a && b && c && d)) {
			fail();			
		}


	}

	@Test
	public void streamPage() throws IOException {
		
		WebpageSearch testObject = new WebpageSearch("http://google.com");
		if (testObject.mWebPage.length() == 0) {
			fail();
		}
	}

	@Test
	public void indexPage() throws IOException {
		
		WebpageSearch testObject = new WebpageSearch("http://google.com");
		
		if (testObject.mPageIndex.size() == 0) {
			fail();
		}
	}
	
	@Test
	public void binarySearch() throws IOException {
		
		WebpageSearch testObject = new WebpageSearch("http://google.com");
		
		if (testObject.binarySearch("Grand Circus")) {
			fail();
		}
	}
	
	
	@Test
	public void updateFile() throws IOException {
		
		WebpageSearch testObject = new WebpageSearch("http://google.com");
		
		String highlightTest = "<span style=\"background-color: #FFFF00\">google</span>";
		testObject.updateFile("google");
		if (!(testObject.mWebPage.contains(highlightTest))) {
			fail();
		}
	}
	
	@Test
	public void createFile() throws IOException {
		
		WebpageSearch testObject = new WebpageSearch("https://medium.com/geeks-bearing-gifts/advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86");
		testObject.createFile();
		Path path = Paths.get("HTML/advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86.html");
		if (!(Files.exists(path))){
			fail();
		}
		
		
	}
	
	@Test
	public void nameFile() throws IOException {
		String article = "https://medium.com/geeks-bearing-gifts/advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86";
		WebpageSearch testObject = new WebpageSearch(article);
		String articleTitle = "advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86.html";
		if (!(testObject.nameFile(article).equals(articleTitle))) {
			fail();
		}
	}
	
	@Test
	public void search() throws IOException {
		String article = "https://medium.com/geeks-bearing-gifts/advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86";
		WebpageSearch testObject = new WebpageSearch(article);
		ArrayList<String> testArray = new ArrayList<String>();
		testArray.add("impressions");
		testArray.add("eyeballs");
		testObject.search(testArray);
		
		String articleTitle = "advertising-the-myth-of-mass-media-and-the-relationship-strategy-cece3698ba86.html";
		if (!(testObject.nameFile(article).equals(articleTitle))) {
			fail();
		}
	}
	
}
