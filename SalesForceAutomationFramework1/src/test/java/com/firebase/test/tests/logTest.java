package com.firebase.test.tests;

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class logTest {

	
	private static Logger logger=LogManager.getLogger(logTest.class);
	
	@Test
	public void performSomeTask() {
		logger.debug("This is debug message");
		logger.info("This is info message");
		logger.error("This is error message");
		logger.warn("This is warn message");
		logger.fatal("This is fatal message");
	}
	@Test
	public void performSomeTask1() {
		logger.debug("This is debug message");
		logger.info("This is info message");
		logger.error("This is error message");
		logger.warn("This is warn message");
		logger.fatal("This is fatal message");
	}
	
}
