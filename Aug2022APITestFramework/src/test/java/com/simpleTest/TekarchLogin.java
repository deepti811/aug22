package com.simpleTest;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
//https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/login
public class TekarchLogin {
	
	@BeforeClass 
	public static void setUp() {
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}
	
	public  static String login() {
		Response res=	RestAssured
				.given()
				.body("{\"username\":\"deepti.solanki08@gmail.com\",\"password\":\"deepti123\"}")
				.contentType(ContentType.JSON)
				.when()
				.post("login");
		String token=res.jsonPath().get("[0].token");
		return token;
		
	}
	@Test(enabled=false)
	public static void loginToApi() {
	Response res=	RestAssured
		.given()
		.body("{\"username\":\"deepti.solanki08@gmail.com\",\"password\":\"deepti123\"}")
		.contentType(ContentType.JSON)
		.when()
		.post("login");
	res.then().statusCode(201);
	String token=res.jsonPath().get("[0].token");
	String userid=res.jsonPath().get("[0].userid");
	System.out.println("token="+token);
	System.out.println("user id="+userid);
	res.prettyPrint();	
	}

	@Test(dependsOnMethods= "updateUSerDetails",enabled=false)
	public static void getUserDetails() {
		Header header=new Header("token",login());
	Response res=	RestAssured
		.given()
		.header(header)
		.when()
		.get("getdata");
	res.then().statusCode(200);
	System.out.println("number of record=" + res.jsonPath().get("size()"));
	String userId=res.jsonPath().getString("[0].userid");
	String id=res.jsonPath().getString("[0].id");
	String accountnum=res.jsonPath().getString("[0].accountno");
	
	String departmentno=res.jsonPath().getString("[0].departmentno");
	System.out.println("departmentno" +departmentno);
	String salary=res.jsonPath().getString("[0].salary");
	System.out.println("salary" +salary);
	System.out.println("account no is" +accountnum);
	System.out.println("first entry user data userid and id is" +userId +"   and    "+id);
	//res.then().body("[0].departmentno", Matchers.equalTo(3));
	}
	
	@Test(enabled=false)
	public static void createUSerDetails() {
		Header header=new Header("token",login());
		Response res= RestAssured
		.given()
		.header(header)
		.contentType(ContentType.JSON)
		.body("{\"accountno\":\"TA-Aug2202\",\"departmentno\":\"1\",\"salary\":\"5000\",\"pincode\":\"123123\"}")
		.when()
		.post("addData");
		
		res.then().statusCode(201);
		res.then().body("status",Matchers.equalTo("success"));	
	}
	
	@Test(enabled=false)
	public static void updateUSerDetails() {
		String token1=login();
		Header header=new Header("token",token1);
		Response res= RestAssured.given()
		.header(header)
		.contentType(ContentType.JSON)
		.body("{\"accountno\":\"TA-Aug2202\",\"departmentno\":3,\"salary\":6000,\"pincode\":123123,\"userid\":\"f4dTFeiZ4qiLC5JTtieF\","
				+ "\"id\":\"5IxVK1gM4FVtR7HCT7Ae\"}")
		.when()
		.put("updateData");
		
		res.then().statusCode(200);
		res.then().body("status",Matchers.equalTo("success"));	
		
	}
	
	@Test(enabled = true)
	public static void deleteUserInfo() {
		String token1=login();
		Header header=new Header("token",token1);
		Response res= RestAssured.given()
		.header(header)
		.contentType(ContentType.JSON)
		.body("{\"userid\":\"f4dTFeiZ4qiLC5JTtieF\",\"id\":\" 5IxVK1gM4FVtR7HCT7Ae\"}")
		.when()
		.delete("deleteData");
		
		res.then().statusCode(200);
		res.then().contentType(ContentType.JSON);
		res.then().body("status",Matchers.equalTo("success"));	
		
		
	}
	
	
	
	
	
	
	
	
	
	
}