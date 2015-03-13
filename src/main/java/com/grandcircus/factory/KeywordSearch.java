package com.grandcircus.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class KeywordSearch {
	String file = ("/resources/words.txt");

	public List<String> keyWords(String file) {
		List<String> keyWords = new ArrayList<String>();

		ClassLoader classLoader = getClass().getClassLoader();
		File file3 = new File(classLoader.getResource(file).getFile());

		try {
			BufferedReader buff = new BufferedReader(new FileReader(file3));
			for (String str = buff.readLine(); str != null; str = buff
					.readLine()) {
				keyWords.add(str);
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Exception -- " + e.getMessage());
			System.exit(1);
			return null;
		}
		return keyWords;

	}
}

/*String searchFile = ("resources/SearchTerms.txt");
String articleFile = ("resources/Articles.txt");
List<String> keyWords;
List<String> articleAddresses;

KeywordSearch () {
	keyWords(searchFile);
	articleSearch(articleFile);
}

public void keyWords(String file) {
	ClassLoader classLoader = getClass().getClassLoader();
	File file3 = new File(classLoader.getResource(file).getFile());

	try {
		BufferedReader buff = new BufferedReader(new FileReader(file3));
		for (String str = buff.readLine(); str != null; str = buff
				.readLine()) {
			keyWords.add(str);
		}
		buff.close();
	} catch (IOException e) {
		System.out.println("Exception -- " + e.getMessage());
		System.exit(1);
	}
}

public void articleSearch(String file) {
	try {
		BufferedReader buff = new BufferedReader(new FileReader(file));
		for (String str = buff.readLine(); str != null; str = buff.readLine()) {
			articleAddresses.add(str);
		}
		buff.close();
	} catch (IOException e) {
		System.out.println("Exception -- " + e.getMessage());
		System.exit(1);
	}
}


public static void main (String arguments[]) throws IOException {
	
	
	
	WebpageSearch testArticle = new WebpageSearch("https://medium.com/backchannel/cody-wilson-wants-to-destroy-your-world-ad121c8b0a6");
	testArticle.search("BM");
	
}
}
*/