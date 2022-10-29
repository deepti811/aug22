package com.simpleTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HomeWorkAPI {
	@Test
	public static void FirstTest1() {
Response res=RestAssured
.given()
.when()
.get("https://dummy.restapiexample.com/api/v1/employees");
		res.prettyPrint();
		//.then() 
	//	.statusCode(200)
		String statusCode=res.jsonPath().getString("status");
		System.out.println();
		res.then().body("status",Matchers.equalTo(statusCode));	
		int noofEmployee = res. jsonPath().get ("data.size()") ;
		System.out.println("noofEmployee:"+noofEmployee);
		
	}
}
