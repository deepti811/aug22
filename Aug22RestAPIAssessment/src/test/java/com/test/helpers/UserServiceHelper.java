package com.test.helpers;

import java.util.List;

import org.testng.Assert;

import com.test.constants.Endpoints;
import com.test.models.Datum;
import com.test.models.EmployeePOJO;
import com.test.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserServiceHelper {
	private static Response response;
	private static String token = null;

	public static String getBaseUri() {
		String baseURI = Utils.getConfigProperty("baseURI");
		return baseURI;
	}

	public static List<EmployeePOJO> getUserData() {

		response = RestAssured.given().contentType("application/json").when().get(Endpoints.TestCase1);
		System.out.println("response : " + response.asPrettyString());
		Datum datum = response.as(Datum.class);

		response.then().statusCode(200);
		String status = ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status, "success");
		System.out.println("number of records ##### " + datum.getData().size());
		return datum.getData();
	}

	public static Response addUserData(EmployeePOJO user) {
		System.out.println("EmployeePOJO@@@@@@@@" + user);
		response = RestAssured.given().contentType("application/json").body(user).when().post(Endpoints.TestCase2);
		System.out.println("response @@@@@@" + response);
		return response;
	}

	public static int getStatuscode(Response res) {
		return res.getStatusCode();
	}

	public static Response deleteUserData(Integer id) {

		System.out.println("deleteUserData $$$$$$$" + id);
		Response response = RestAssured.given().contentType(ContentType.JSON).pathParam("id", id)
				.delete(Endpoints.TestCase3);
		
		return response;

	}

	public static Response getUserDataById(Integer id) {

		response = RestAssured.given().contentType("application/json").when().pathParam("id", id)
				.get(Endpoints.TestCase5);
		System.out.println("response : " + response.asPrettyString());

		return response;
	}

}
