package com.grandcircus.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.lang.*;

public class KeywordSearch {
			
	ArrayList<String> getWords(String directory) {
			
			// Magic
			ClassLoader classLoader = getClass().getClassLoader();
			// Create a file
			File file = new File(classLoader.getResource(directory).getFile());
			
			// Create buffer from the file, create string from the buffer, create array from the string
			try {
				// Create buffer from the file
				BufferedReader buff = new BufferedReader(new FileReader(file));
				// Create String from the buffer
				String str = buff.readLine();
				// Close the buffer
				buff.close();
				// Tokenize the string
				StringTokenizer tokened = new StringTokenizer(str, ",\"");
				// Create array from the string
				ArrayList<String> arrayOut = new ArrayList<String>();
				while (tokened.hasMoreTokens()) {
					arrayOut.add(tokened.nextToken());
				}
			return arrayOut;
			// If bad things happen...throw an error and quit
			} catch (IOException e) {
				System.out.println("Exception -- " + e.getMessage());
				System.exit(1);
				return null;
			}
	}

	public static void main (String[] args) {
		
		// Directories for search words, articles
		String articlesFile = ("resources/articles.txt");
		String wordFile = ("resources/words.txt");
		
		// New KeywordSearch object
		KeywordSearch key = new KeywordSearch();
		
		// Call first method: keyWords to get List of keywords
		ArrayList<String> articles = key.getWords(articlesFile);
		
		// Call first method: keyWords to get List of keywords
		ArrayList<String> keywords = key.getWords(wordFile);
		
		// Run using WebpageSearch
		WebpageSearch firstArticle;
		try {
			firstArticle = new WebpageSearch(articles.get(2));
			firstArticle.search(keywords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// Run using Jsoup
		JsoupSearch secondArticle;
		try {
			secondArticle = new JsoupSearch(articles.get(1));
			secondArticle.search(keywords);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
	}
	
}	
	
