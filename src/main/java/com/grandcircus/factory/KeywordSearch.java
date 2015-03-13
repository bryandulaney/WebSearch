package com.grandcircus.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class KeywordSearch {
	String file = ("/resources/words.txt");
	String url =("");
	List<String>keyWords;
	List<String> urlAddress;
	
	KeywordSearch() {
		keyWords(file);
		urlAddress(url);
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
	
	public void urlAddress(String url) {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));
			for (String str = buff.readLine(); str != null; str = buff.readLine()) {
				urlAddress.add(str);
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Exception -- " + e.getMessage());
			System.exit(1);
			//test
		}
	}
}