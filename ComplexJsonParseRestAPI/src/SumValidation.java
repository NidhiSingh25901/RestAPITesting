import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourse() {
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()"); 
		System.out.println(count); 
		int sum=0;
		
		for(int i=0;i<count;i++) {
			int price = js.getInt("courses["+i+"].price"); 
			int coursecount = js.getInt("courses["+i+"].copies"); 
			int amount = price * coursecount; 
			System.out.println(amount); 
			sum+=amount; 
		} 
		int purchaseamount = js.getInt("dashboard.purchaseAmount"); 
		Assert.assertEquals(sum,purchaseamount);
	}
}
