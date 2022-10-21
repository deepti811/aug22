package com.test.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;

import com.test.models.EmployeePOJO;
import com.test.models.EmployeeResponsePOJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeeApiTestScriptE2E extends UserServiceHelper {

	private static EmployeePOJO employee = null;
	@BeforeMethod
	public static void baseUri() {
		RestAssured.baseURI = getBaseUri();
		System.out.println(RestAssured.baseURI);
	}

	@Test(priority = 1, enabled = true)
	public static void TC_001() {
		getUserData();
	}

	@Test(priority = 2, enabled = true)
	public static void TC_002_addUserData() {
		EmployeePOJO employeeLocal = ReusableMethods.getUserDataToAdd();

		Response res = UserServiceHelper.addUserData(employeeLocal);
		System.out.println("Responce   $$$$$$$$$$$$$$$$  " + res.asPrettyString());
		EmployeeResponsePOJO resPojo = res.as(EmployeeResponsePOJO.class);
		System.out.println("Response Pojo : " + resPojo.toString());
		ReusableMethods.verifyStatusCodeis(res, 200);
		String status = ReusableMethods.getJsonPathData(res, "status");
		AssertJUnit.assertEquals(status, "success");
		AssertJUnit.assertEquals(employeeLocal.getEmployeeName(), resPojo.getData().getEmployeeName());
		AssertJUnit.assertEquals(employeeLocal.getEmployeeAge(), resPojo.getData().getEmployeeAge());
		AssertJUnit.assertEquals(employeeLocal.getEmployeeSalary(), resPojo.getData().getEmployeeSalary());
		EmployeeApiTestScriptE2E.employee = resPojo.getData();
	}

	@Test(priority = 3, enabled = true)
	public static void TC_003() {
		//TC_002_addUserData();
		System.out.println("CreateResponsePOJO==============="+ employee.toString());
		Response res = UserServiceHelper.deleteUserData(employee.getId());
		ReusableMethods.verifyStatusCodeis(res, 200);
		String status = ReusableMethods.getJsonPathData(res, "status");
		System.out.println("deleted successfully===============");
		AssertJUnit.assertEquals(status, "success");
		AssertJUnit.assertEquals(employee.getId().intValue(), res.jsonPath().getInt("data"));
	}
	@Test(priority = 4, enabled = true)
	public static void TC_004() {
		
		Response res = UserServiceHelper.deleteUserData(0);
		ReusableMethods.verifyStatusCodeis(res, 400);
		String message = ReusableMethods.getJsonPathData(res, "message");
		System.out.println("Response" + message);
		String status = ReusableMethods.getJsonPathData(res, "status");		
		AssertJUnit.assertEquals(status, "error");
	}
	@Test(priority = 5, enabled = true)
	public static void TC_005() {
		
		Response response = UserServiceHelper.getUserDataById(2);
		EmployeeResponsePOJO employeeResponse = response.as(EmployeeResponsePOJO.class);

		response.then().statusCode(200);
		String status = ReusableMethods.getJsonPathData(response, "status");
		AssertJUnit.assertEquals(status, "success");
	    AssertJUnit.assertEquals(response.getContentType(), "application/json");
	    AssertJUnit.assertEquals(employeeResponse.getData().getEmployeeName(), "Garrett Winters");
	    AssertJUnit.assertEquals(employeeResponse.getData().getEmployeeSalary().intValue(), 170750);
	    AssertJUnit.assertEquals(employeeResponse.getData().getEmployeeAge().intValue(), 63);
	}
}
