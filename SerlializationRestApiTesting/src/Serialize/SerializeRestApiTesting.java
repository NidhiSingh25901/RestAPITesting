package Serialize;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
public class SerializeRestApiTesting {

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
		
		
		
		Response res = given().queryParam("key","qaclick123").body(obj)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response(); 
		
		String restring = res.asString();
		System.out.println(restring);
	}

}
