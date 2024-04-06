package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	public static JsonPath rawToJson(String getPlaceResponse) {
		JsonPath getjsondata = new JsonPath(getPlaceResponse); 
		return getjsondata;
	}
}
