package com.cos.costagram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "user/profile";
	}

	@GetMapping("/user/{id}/profileSetting")
	public String profileSetting(@PathVariable int id) {
		return "user/prifileSetting";
	}
	
	
}
