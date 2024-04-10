package SerializationDeserilization;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Pojo.GetCourse;
import Pojo.api;
import Pojo.webAutomation;
import io.restassured.path.json.JsonPath; 

public class RestApiOAuthTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		String response = given() 
		.formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString(); 
		
		System.out.println(response);
		
		JsonPath jsondata = new JsonPath(response); 
		String accesstoken = jsondata.getString("access_token");
		
		GetCourse response2 = given()
		.queryParams("access_token",accesstoken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(GetCourse.class);	
		
		System.out.println(response2.getLinkedIn());
		System.out.println(response2.getInstructor()); 
		response2.getCourses().getApi().get(1).getCourseTitle(); 
		
		List<api> apiCourses = response2.getCourses().getApi(); 
		for(int i=0;i<apiCourses.size();i++) {
			if((apiCourses.get(i).getCourseTitle()).equalsIgnoreCase("SoapUI WebServices Testing")) {
				System.out.println(response2.getCourses().getApi().get(i).getPrice());
			}
		} 
		
		ArrayList<String> arrlist = new ArrayList<String>();
		List<webAutomation> webAutomationCourses = response2.getCourses().getWebAutomation(); 
		for(int j=0;j<webAutomationCourses.size();j++) {
			arrlist.add(response2.getCourses().getWebAutomation().get(j).getCourseTitle());
		} 
		
		List<String> expectedList = Arrays.asList(courseTitles); 
		
		Assert.assertTrue(arrlist.equals(expectedList));
		
		
		
	}

}