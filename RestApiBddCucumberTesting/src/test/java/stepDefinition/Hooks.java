package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario( ) throws IOException {
		
		stepDefinition m = new stepDefinition(); 
		
		if(m.place_id == null) {
		m.add_place_payload_with("Nidhi", "French", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Nidhi", "getPlaceAPI"); 
		} 
		
	}
}
