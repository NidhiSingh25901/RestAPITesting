package Pojo;

import java.util.List;

public class Courses {  
	
	private List<webAutomation> webAutomation; 
	private List<api> api;
	private List<mobile> mobile; 
	
	public List<Pojo.webAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<Pojo.webAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Pojo.api> getApi() {
		return api;
	}
	public void setApi(List<Pojo.api> api) {
		this.api = api;
	}
	public List<Pojo.mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Pojo.mobile> mobile) {
		this.mobile = mobile;
	}

}
