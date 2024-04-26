package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		
		AddPlace obj = new AddPlace();
		obj.setAccuracy(50);
	//	obj.setAddress("Hyderabad");  
		obj.setAddress(address);
		// obj.setLanguage("English"); 
		obj.setLanguage(language);
		obj.setPhone_number("6200156713");
		obj.setWebsite("https://rahulshettyacademy.com/");
		// obj.setName("FrontLine House"); 
		 obj.setName(name);
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		obj.setTypes(mylist);
		
		Location l = new Location();
		l.setLat(10);
		l.setLng(20);
		obj.setLocation(l); 
		return obj;
	} 
	
	public String deletePlacePayload(String place_id) {
		return "{\r \n \"place_id\" : \""+place_id+" \"\r\n}";
	}
}
