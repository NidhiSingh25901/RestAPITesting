import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; 
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;
public class Index {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//To Validate if AddPlace API is working as expected 
		
		//given - all input details
		//when - submit the API - resource, http method
		//Then - Validate the response 
		//When the json is used as a json text file, then first we need to convert the file into string format 
	    // Content of the file will be converted into byte  
		RestAssured.baseURI = "https://rahulshettyacademy.com"; 
		
	String response =	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Abby\\Desktop\\index.json")))).when().post("maps/api/place/add/json")
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
	
	JsonPath getplace = ReusableMethods.rawToJson(getPlaceresponse);
	// Alternate of above statement
	//JsonPath getplace = new JsonPath(getPlaceresponse); 
	String updatedAddress = getplace.getString("address"); 
	System.out.println(updatedAddress);
	
	//Two frameworks that are used for checking assertion methods - TestNG, Junit 
	
	Assert.assertEquals(updatedAddress, newaddress);
	
	}

}
