package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import json.Payload;

public class ParseComplexJson {
	private JsonPath json;
	@Test
	public void printCourses() {
		String response = Payload.getCoursePriceResponse();
		json = new JsonPath(response);
		// Print No of courses returned by API
		String courseCount = json.get("courses.size").toString();
		System.out.println(courseCount);
		// Print Purchase Amount
		String purchaseAmount = json.get("dashboard.purchaseAmount").toString();
		System.out.println(purchaseAmount);
		// Print Title of the first course
		String firstCourse = json.get("courses[0].title").toString();
		System.out.println(firstCourse);
		
		//Print All course titles and their respective Prices
		for(int i = 0; i < Integer.parseInt(courseCount); i++) {
			System.out.println(json.get("courses["+i+"].title"));
			System.out.println(json.get("courses["+i+"].price"));
		}
		
		//Print no of copies sold by RPA Course
		for(int i = 0; i < Integer.parseInt(courseCount); i++) {
			if(json.get("courses["+i+"].title").toString().equalsIgnoreCase("RPA")){
				System.out.println(json.get("courses["+i+"].copies"));
				break;
			}
		}
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		double totalPrice = 0.0;
		double itemPrice = 0.0;
		int copies = 0;
		for(int i = 0; i < Integer.parseInt(courseCount); i++) {
			
			itemPrice = json.getDouble("courses["+i+"].price");
			copies = json.getInt("courses["+i+"].copies");
			totalPrice += (itemPrice * copies);
		}
		Assert.assertEquals(totalPrice, Double.parseDouble(purchaseAmount));
	}

}
