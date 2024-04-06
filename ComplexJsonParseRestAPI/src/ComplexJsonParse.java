import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		
		JsonPath js = new JsonPath(payload.CoursePrice()); 
		System.out.println("No. of Courses - " + js.getInt("courses.size()")); 
		System.out.println("Purchased Amount - " +js.get("dashboard.purchaseAmount"));
		System.out.println("Titel of first Course - " + js.get("courses[0].title")); 
		String numberofcopiessold = "";
		int coursesize = js.getInt("courses.size()"); 
		int totalcourseprice = 0;
		for(int i=0;i<coursesize;i++) { 
		String courseTitle = js.get("courses["+i+"].title");
		int coursePrice = js.getInt("courses["+i+"].price"); 
		int coursecount = js.getInt("course["+i+"].count");  
		int coursecountotal = (coursePrice * coursecount);
		totalcourseprice = totalcourseprice + (coursePrice * coursecountotal);
		System.out.print(courseTitle+" - ");
		System.out.println(coursePrice); 
		
		if(courseTitle.equalsIgnoreCase("RPA")) { 
			 numberofcopiessold = js.getString("courses["+i+"].copies").toString();
		}		
		} 
		System.out.println("Number of copies sold by RPA - " + numberofcopiessold);
		if(totalcourseprice == (js.getInt("dashboard.purcareAmount"))) {
			System.out.println("Both are equal");
		} else {
			System.out.println("Both are not equal");
		}
	}

}




