package SpecBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
public class SpecBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com"; 
		
		AddPlace obj = new AddPlace(); 
		obj.setAccuracy(50);
		obj.setAddress("Hyderabad");
		obj.setLanguage("English");
		obj.setPhone_number("6200156713");
		obj.setWebsite("https://rahulshettyacademy.com/");
		obj.setName("FrontLine House"); 
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		obj.setTypes(mylist);
		
		Location l = new Location();
		l.setLat(10);
		l.setLng(20);
		obj.setLocation(l); 
		
		
		RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com/")
		.addQueryParam("key","qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(reqspec).body(obj); 
		
		Response response = res.when().post("/maps/api/place/add/json")
		.then().spec(resspec).extract().response(); 
		
		String restring = response.asString();
		System.out.println(restring);
	}

}
