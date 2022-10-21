package com.test.helpers;

import java.util.concurrent.TimeUnit;

import com.test.models.EmployeePOJO;

import io.restassured.response.Response;

public class ReusableMethods {
	public static EmployeePOJO employee = new EmployeePOJO();

	public static EmployeePOJO getUserDataToAdd() {
		employee.setEmployeeName("test");
		employee.setEmployeeSalary(123);
		employee.setEmployeeAge(23);
		return employee;
	}

	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	public String getContentType(Response response) {
		return response.getContentType();
	}

	public static String getJsonPathData(Response response, String status) {
		return response.jsonPath().getString(status);
	}

	public long getResponseTimeIn(Response response, TimeUnit unit) {
		return response.getTimeIn(unit);
	}

	public static void verifyStatusCodeis(Response response, int statuscode) {
		response.then().statusCode(statuscode);
	}

}