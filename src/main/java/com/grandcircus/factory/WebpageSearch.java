package com.grandcircus.factory;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

public class WebpageSearch {

	private String mWebAddress;
	
	private String mWebPage;
	
	private ArrayList mPageIndex = new ArrayList();

	WebpageSearch(String address) {
		mWebAddress = address;
		// WebpageSearch applepie = New WebpageSearch();
		// applepie.mWebAddress = "crust";
		// indexPage();
	}
	
	private void streamPage () throws IOException {	
		// Fetch webpage using URLConnection and URL
		URL url = new URL(mWebAddress);
		URLConnection con = url.openConnection();
		// Parse stream with regex classes Pattern and Matcher
		Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m = p.matcher(con.getContentType());
		// Set character set of text. If Content-Type doesn't match this pre-conception, choose default. 
		String charset = m.matches() ? m.group(1) : "ISO-8859-1";
		// Create input stream, and use StringBuilder to build mWebPage string
		Reader r = new InputStreamReader(con.getInputStream(), charset);
		StringBuilder sb = new StringBuilder();
		while (true) {
		  int ch = r.read();
		  if (ch < 0)
		    break;
		  sb.append((char) ch);
		}
		mWebPage = sb.toString();
	}
	
	private void indexPage () {
		// Tokenize mWebPage
        StringTokenizer st = new StringTokenizer(mWebPage,"[]{}://&., -<>()?!;—\\s\\b’“”");
        
        // Fill index array
        for (int i = 0; i < st.countTokens(); i++) {
            mPageIndex.add(st.nextToken());        	
        }
        
        // Sort index array
        Collections.sort(mPageIndex);
	}
	
	private boolean binarySearch (String keyword) {
		int indexing = Collections.binarySearch(mPageIndex, keyword);
		if (indexing < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	private void createFile(String keyword) {
		
		// Find and Replace keyword with highlights in page
		String regex = ".*\\b" + keyword + "\\b.*";
		String replacement = "<span style=\"background-color: #FFFF00\">" + keyword + "</span>";
		mWebPage.replaceAll(regex, replacement);
		
		// Name File
		String file = nameFile(mWebAddress);
		
		// Write File
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
		    fWriter = new FileWriter("HTML/" + file);
		    writer = new BufferedWriter(fWriter);
		    writer.write(mWebPage);
		    writer.close();
		} catch (Exception e) {
		  System.out.println("Exception!");
		}
		
		// Inform user
		System.out.println("Your file has been created. It's called: " + file);
	}
	
	private String nameFile(String inputAddress) {
		// Create a substring for stuff after the last / in inputAddress
		// and append file extension .html
		String outputName = inputAddress.substring(inputAddress.lastIndexOf("/") + 1) + ".html";	
		return outputName;
	}

	public void search(String keyword) {
		if (this.binarySearch(keyword)) {
			this.createFile(keyword);
		} else {
			System.out.println("Not here!");
		}
	}

	
}