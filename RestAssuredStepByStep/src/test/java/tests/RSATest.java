package tests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RSATest {
	private String keyValue = "qaclick123";
	private String placeId;
	private JsonPath json;
	private String addressVar = "168 B.T.Road";
	
	@Test(priority = 1)
	public void addPlace() {
		baseURI = "https://rahulshettyacademy.com";
		String response =  given()
		    .log().all()
			.queryParam("key", keyValue)
			.header("Content-Type", "application/json")
			.body("{\r\n"
					+ "  \"location\": {\r\n"
					+ "    \"lat\": -38.383494,\r\n"
					+ "    \"lng\": 33.427362\r\n"
					+ "  },\r\n"
					+ "  \"accuracy\": 50,\r\n"
					+ "  \"name\": \"Indranil test from Rest Assured\",\r\n"
					+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
					+ "  \"address\": \""+addressVar+"\",\r\n"
					+ "  \"types\": [\r\n"
					+ "    \"shoe park\",\r\n"
					+ "    \"shop\"\r\n"
					+ "  ],\r\n"
					+ "  \"website\": \"http://google.com\",\r\n"
					+ "  \"language\": \"French-IN\"\r\n"
					+ "}")
		.when()
			.post("maps/api/place/add/json")
		.then()
			.assertThat()
			.statusCode(200)
			.log().all()
			.extract().response().asString();
		
		json = new JsonPath(response);
		placeId = json.get("place_id").toString();
		
	}
	
	@Test(priority = 2)
	public void getPlace() {
		baseURI = "https://rahulshettyacademy.com";
		String response =  given()
			    .log().all()
				.queryParam("key", keyValue)
				.queryParam("place_id", placeId)
			.when()
				.get("maps/api/place/get/json")
			.then()
				.assertThat()
				.statusCode(200)
				.log().all()
				.assertThat().body("address", equalTo(addressVar))
				.extract().response().asString();
		
		System.out.println(response);

	}

}
