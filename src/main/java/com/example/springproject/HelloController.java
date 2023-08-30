package com.example.springproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/hello2")
	public String index2() {
		return "Greetings from Spring Boot!";
	}

	// @GetMapping("/company/getPersons")
    // public String getPersonsFromCompany() {
    //     return "spring" ;
    // }

}
