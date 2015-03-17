package com.grandcircus.factory;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;

public class JsoupSearch {

	// Declare member variables
	String mWebAddress;
	String mWebPageText;
	String mWebPageHTML;
	ArrayList<String> mPageIndex = new ArrayList<String>();
	
	// Constructor
	JsoupSearch(String address) throws IOException {
		mWebAddress = address;
		streamPage();
		indexPage();
	}
	
	// Get wepbage contents, save text-only and full-HTML contents in separate variables
	void streamPage () throws IOException {	
		Document doc = Jsoup.connect(mWebAddress).get();
		mWebPageText = doc.body().text();
		mWebPageHTML = doc.html();
	}
	
	void indexPage () {
		
		// Tokenize mWebPage
        StringTokenizer st = new StringTokenizer(mWebPageText,"‘|…\"#%*[]{}://&.$+\', -<>()?!;=—\\s\\b’“”");
        
        // Send tokens to page index array
        for (int i = 0; i < st.countTokens(); i++) {
            mPageIndex.add(st.nextToken());        	
        }
        
        // Sort page index array (for binary search)
        Collections.sort(mPageIndex);
	}
	
	boolean doesPageContainKeyword (String keyword) {
		int indexing = Collections.binarySearch(mPageIndex, keyword);
		return indexing >= 0;
	}
	
	void updateFile(String keyword) {		
		String replacement = "<span style=\"background-color: #FFFF00\">" + keyword + "</span>";
		mWebPageHTML = mWebPageHTML.replaceAll("\\b" + keyword + "\\b", replacement);
		
	}
	
	 String nameFile(String inputAddress) {
		String outputName = inputAddress.substring(inputAddress.lastIndexOf("/") + 1) + "+JSoup.html";	
		return outputName;
	}
	
	// Updated to only contain File Writing instructions, removed unnecessary parameter
	void createFile() {
		// Name File
		String file = nameFile(mWebAddress);
		
		// Write File
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
		    fWriter = new FileWriter("HTML/" + file);
		    writer = new BufferedWriter(fWriter);
		    writer.write(mWebPageHTML);
		    writer.close();
		} catch (Exception e) {
		  System.out.println("Exception!");
		}
		
		// Inform user
		System.out.println("Your searched file has been created. It's called: " + file);
	}
	

	public void search(ArrayList<String> keywordList) {
		for (String keyword : keywordList) {
			if (this.doesPageContainKeyword(keyword)) {
				this.updateFile(keyword);
			}			
		}
		this.createFile();
	}
	
}


