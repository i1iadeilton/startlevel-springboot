package com.calculadoraapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	 
	@RequestMapping("/")
	public String index() {
		return "index"; 
	}
	
	@RequestMapping("/index2")
	public String index2() {
		return "index2"; 
	}
}
