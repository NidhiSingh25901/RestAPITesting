package Files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import java.io.*;

import org.testng.Assert;

import static io.restassured.RestAssured.*;
public class JiraTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI =  "http://localhost:8080"; 
		
		
		SessionFilter session = new SessionFilter(); 
		
		//Login to the system
		String response = given().relaxedHTTPSValidation().header("Content-Type","application/json")  
		.body("{\r\n"
				+ "    \"username\": \"automationtesting156\",\r\n"
				+ "    \"password\": \"Acteam@13\"\r\n"
				+ "}")
		.log().all() 
		.filter(session)
		.when().post("/rest/auth/1/session") 
		.then().assertThat().statusCode(200).extract().response().asString();
		
		String expectedmessage = "Hi, I am Nidhi";
		//Adding Comments
	String addCommentResponse =	given().pathParam("key","10003").log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \""+expectedmessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}\r\n"
				+ "") 
		.filter(session)
		.when().post("/rest/api/2/issue/{key}/comment") 
		.then().log().all().assertThat().statusCode(201).extract().response().asString(); 
		
	JsonPath js = new JsonPath(addCommentResponse); 
	String commentid = js.getString("id");
	
		//Adding attachments
        given().header("X-Atlassian-Token", "no-check").filter(session)
        .pathParam("key", "10003")
        .header("Content-Type", "multipart/form-data")
        .multiPart("file", new File("C:\\Users\\v-nidhsingh\\eclipse-workspace\\JiraRestApiTestin\\src\\Jira.txt"))
        .when().post("/rest/api/2/issue/{key}/attachments")
        .then().log().all().assertThat().statusCode(200); 
        
        //Get Issue 
       String issueDetails =  given().filter(session).pathParam("key", "10003")
    		   .queryParam("fields", "comment")
    		   .log().all()
        .when().get("/rest/api/2/issue/{key}").then().log().all().extract().response().asString();	
	
	System.out.println(issueDetails); 
	
	JsonPath js1 = new JsonPath(issueDetails); 
	int commentsCount = js1.getInt("fields.comment.comments.size()");
	for(int i=0;i<commentsCount;i++) {
		String commentIdIssue= (js1.get("fields.comment.comments["+i+"].id")).toString();
		if(commentIdIssue.equalsIgnoreCase(commentid)) {
			String message = (js1.get("fields.comment.comments["+i+"].body")).toString();
		    System.out.println(message); 
		    Assert.assertEquals(message,expectedmessage);
		}
		
	}
	}

}
