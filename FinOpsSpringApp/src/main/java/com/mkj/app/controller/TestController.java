package com.mkj.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	
	public TestController()
	{
		System.out.println("Test controller constructor");
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "test endpoint";
	}
}
