import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; 
import static org.hamcrest.Matchers.*;

import files.payload;
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//To Validate if AddPlace API is working as expected 
		
		//given - all input details
		//when - submit the API - resource, http method
		//Then - Validate the response 
		
		RestAssured.baseURI = "https://rahulshettyacademy.com"; 
		
	String response =	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString(); 
	
	System.out.println(response); 
	JsonPath js = new JsonPath(response); 
	String placeId = js.getString("place_id"); 
	System.out.println(placeId); 
	
	
	//update Place 
	
	String newaddress = "70 Summer walk, USA";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json") 
	.body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\""+newaddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "")
	.when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated")); 
	
	//Get Place 
	String getPlaceresponse = given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id",placeId)
	.when().get("maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath getplace = new JsonPath(getPlaceresponse); 
	String updatedAddress = getplace.getString("address"); 
	System.out.println(updatedAddress);	
	
	}

}
