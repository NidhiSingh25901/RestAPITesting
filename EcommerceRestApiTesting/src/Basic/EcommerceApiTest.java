package Basic;

import Pojo.LoginRequest;
import Pojo.LoginResponse;
import Pojo.OrderDetails;
import Pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List; 

public class EcommerceApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestSpecification req =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com") 
		.setContentType(ContentType.JSON).build();
		
		LoginRequest loginrequest = new LoginRequest(); 
		loginrequest.setUserEmail("peacock156mst@tcs.com"); 
		loginrequest.setUserPassword("Acteam@13"); 
		
		RequestSpecification reglogin = given().log().all().spec(req).body(loginrequest); 
		
		LoginResponse loginresponse = reglogin.when().post("/api/ecom/auth/login")
		.then().extract().response().as(LoginResponse.class); 
		String token = loginresponse.getToken();
		System.out.println(loginresponse.getToken()); 
		String userId = loginresponse.getUserId();
		System.out.println(loginresponse.getUserId()); 
		
		//AddProduct 
		
		RequestSpecification addProductBasereq =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com") 
				.addHeader("Authorization", token).build();
				//.setContentType(ContentType.JSON).build();
		
		RequestSpecification addProductreq  = given().log().all().spec(addProductBasereq)
		.param("productName", "Laptop")
		.param("productAddedBy", userId)
		.param("productCategory", "Electronic")
		.param("productSubCategory", "Study Device")
		.param("productPrice", "1500")
		.param("productDescription", "Electronic Gadget")
		.param("productFor", "women")
		.multiPart("productImage", new File("/Users/v-nidhsingh/Downloads/pexels-tuur-tisseghem-812264.jpg"));
	
	     String addProductResponse = addProductreq.when().post("/api/ecom/product/add-product/")
	     .then().log().all().extract().response().asString(); 
	     
	     JsonPath js = new JsonPath(addProductResponse);
	     String productid = js.get("productId");
	     System.out.println(productid); 
	     
	     //Create Order 
	     
	 	RequestSpecification CreateOrderReq =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com") 
				.addHeader("Authorization", token) 
				.setContentType(ContentType.JSON)
				.build();
	 	
	 	OrderDetails orderdetail = new OrderDetails(); 
	 	orderdetail.setCountry("India");
	 	orderdetail.setProductorderid(productid); 
	 	
	 	List<OrderDetails> orderdetailist = new ArrayList<OrderDetails>();
	 	
	 	orderdetailist.add(orderdetail); 
	 	
	 	Orders order = new Orders();
	 	order.setOrders(orderdetailist);
	 	
	 	
	  	RequestSpecification orderreq= given().log().all().spec(CreateOrderReq).body(order);
	  	
	  	String orderres = orderreq.when().post("/api/ecom/order/create-order")
	  	.then().log().all().extract().response().asString(); 
	  	
	  	System.out.println(orderres);
	  	
	  	
	  	//Delete Project
	 	RequestSpecification DeleteProductBaseReq =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com") 
				.addHeader("Authorization", token) 
				.setContentType(ContentType.JSON)
				.build();	  	
	  	
	  	RequestSpecification DeleteProductReq = given().log().all().spec(DeleteProductBaseReq).pathParam("productId",productid);
	  	
	  	String DeleteResponse = DeleteProductReq.when().delete("/api/ecom/product/delete-product/{productId}")
	  	.then().log().all().extract().response().asString(); 
	  	
	  	JsonPath deletejson = new JsonPath(DeleteResponse);
	  	String msg = deletejson.get("message"); 
	  	System.out.println(msg);
	}

}
