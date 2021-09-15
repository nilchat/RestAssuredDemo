package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class TestDemo {
	
	@Test
	public void testFirst() {
		Response response =  RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	

}
