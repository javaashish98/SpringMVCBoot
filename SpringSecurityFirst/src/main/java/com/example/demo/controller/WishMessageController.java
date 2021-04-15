package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishMessageController {
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}

}
