package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	public static JsonPath rawToJson(String getPlaceResponse) {
		JsonPath getjsondata = new JsonPath(getPlaceResponse); 
		return getjsondata;
	}
}
