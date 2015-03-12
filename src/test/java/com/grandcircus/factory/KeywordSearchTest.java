
package com.grandcircus.factory;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

import com.grandcircus.factory.KeywordSearch;

public class KeywordSearchTest {
	String file = "resources/words1.txt";
	private KeywordSearch gen = null;
	
@Before
	public void initialize() {
		gen = new KeywordSearch();
	}
	
@Test
	public void shouldReturnWords (){
		assertValues(file, "abc def ghi");
	}
	private <T> void assertValues(String file, T... expected) {
		List<String> actual = gen.keyWords(file);
		assertEquals(Arrays.asList(expected), actual);
		}	
}