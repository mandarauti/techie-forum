package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest")
public class HelloController {

	@RequestMapping("/greeting")
	public String index() {
		return "Greeting from Mandar";
	}

}
