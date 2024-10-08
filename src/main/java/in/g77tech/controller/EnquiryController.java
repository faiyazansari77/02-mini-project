package in.g77tech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {

	
	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/addEnquiry")
	public String enquiryPage() {
		return "add_enquiry";
	}
	
	@GetMapping("/enquiries")
	public String viewEnquiryPage() {
		return "view_enquiry";
	}
}
