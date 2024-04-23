package stepDefinition;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Resources.TestDataBuild;
import Resources.utils;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
public class stepDefinition extends utils {
 
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response; 
	TestDataBuild data = new TestDataBuild();
	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
		
		
		
		
		 res = given().spec(requestSpecification()).body(data.addPlacePayload());  
		
	}
	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions 
		
		 resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 response = res.when().post("/maps/api/place/add/json")
		.then().spec(resspec).extract().response();
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
		assertEquals(response.getStatusCode(),200);
	
	}
	
	

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException(); 
		
		 String resBody = response.asString();
		    JsonPath jsonPath = new JsonPath(resBody);
		    String actualValue = jsonPath.get(keyValue).toString();
		    assertEquals(expectedValue, actualValue);
	}



	
	@Then("{string} in respose body is {string}")
	public void in_respose_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
		
		String res =  response.asString();
		JsonPath js = new JsonPath(res);
		assertEquals(js.get(keyValue).toString(),Expectedvalue);
	}
 
 
 
}
 