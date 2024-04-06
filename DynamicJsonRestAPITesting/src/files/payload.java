package files;

public class payload {

	public static  String addbook(String aisle, String isbn) { 
		String payload =  "{\r\n"
				+ "  \"name\": \"Learn appium with java\",\r\n"
				+ "  \"isbn\": \""+isbn+"\",\r\n"
				+ "  \"aisle\": \""+aisle+"\",\r\n"
				+ "  \"author\": \"John Foe\"\r\n"
				+ "}";
		return payload;
	}
}
