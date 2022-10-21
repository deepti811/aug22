package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {
				"src/test/resources/SalesForceLogin.feature" }, glue = { "com.steps" })
public class RunnerSalesForce extends AbstractTestNGCucumberTests {
}
