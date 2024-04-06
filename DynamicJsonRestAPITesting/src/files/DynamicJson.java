package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test; 
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class DynamicJson {
 @Test(dataProvider="booksdata")
 public void addBook(String name, String author) {
	 RestAssured.baseURI="http://216.10.245.166";   
	
	String resp = given().header("Content-Type","application/json")
	 .body(payload.addbook(name,author))
	 .when().post("/Library/Addbook.php")
	 .then().log().all().statusCode(200).extract().response().asString(); 
	JsonPath jsondata = ReusableMethod.rawToJson(resp);
	String id = jsondata.get("ID");
	System.out.println(id);
 } 
 
 @DataProvider(name="booksdata")
 public Object[][] getData() {
	return  new Object[][] {{"ada","123"},{"rgth","3456"},{"fefj","76896"}};
 }
}
