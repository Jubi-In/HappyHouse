package com.ssafy.happyhouse.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// index page 처리용 controller
@Controller
public class MainController {

//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}
