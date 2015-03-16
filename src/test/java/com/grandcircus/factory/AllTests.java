package com.grandcircus.factory;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JsoupSearchTest.class, KeywordSearchTest.class,
		WebpageSearchTest.class })
public class AllTests {

}
