package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {

	RequestSpecification reqspec;
	public RequestSpecification requestSpecification() throws IOException { 
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));		
		 reqspec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
		.addQueryParam("key","qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build(); 
		 return reqspec;
	} 
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties props = new Properties(); 
		FileInputStream fis = new FileInputStream("C:\\Users\\v-nidhsingh\\eclipse-workspace\\ApiFramework\\src\\test\\java\\Resources\\global.properties");
		props.load(fis);
		return props.getProperty(key); 
	} 
	
	public String getJsonPath(Response response,String key) {
		String resp = response.asString(); 
		JsonPath js = new JsonPath(resp); 
		return js.get(key).toString(); 
		
	}
}
