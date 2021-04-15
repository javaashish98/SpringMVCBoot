package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishMessageController {
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
