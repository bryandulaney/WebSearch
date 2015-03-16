package com.grandcircus.factory;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class KeywordSearchTest {

	String file = "resources/testwords.txt";
	private KeywordSearch gen = null;

	@Before
	public void initialize() {
		gen = new KeywordSearch();
	}

	@Test
	public void shouldReturnWords() {
		KeywordSearch testObject = new KeywordSearch();
		ArrayList<String> testArray = testObject.getWords(file);
		
		if (testArray.get(0).equals("abc") && testArray.get(1).equals("def") && testArray.get(2).equals("ghi")) {
			//Test passes
		} else {
			// Test fails
			fail();
		}
	}

}
