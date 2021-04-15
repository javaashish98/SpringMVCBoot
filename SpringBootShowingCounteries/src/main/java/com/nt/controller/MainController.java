package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("welcome.php")
	public String welcomePage() {
		return "welcome";
	}
	
	
	@GetMapping("country.js")
	public String showCountry(){
		return "country";
	}

}
