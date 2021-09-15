package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestWithStaticImport {
	@Test
	public void testwithStatic() {
		baseURI = "https://reqres.in";
		given().
		   get("/api/users?page=2").
		then().
		   statusCode(200).
		   body("data[2].email", equalTo("tobias.funke@reqres.in"));		    
	}
	
	@Test
	public void testGetRequest() {
		baseURI = "https://reqres.in";
		given().
			get("/api/users?page=2").
		then().
			body("data[4].first_name", equalTo("George")).
			body("data[4].last_name", equalTo("Edwards"));
	}
	
	@Test
	public void testPostRequest() {
		baseURI = "https://reqres.in";
		JSONObject request = new JSONObject();
		request.put("name", "morpheus");
		request.put("job", "leader");
		
		given().
			body(request.toJSONString()).
		when().
			post("/api/users").
		then().
			statusCode(201).
			log().all();
	}
}
	
