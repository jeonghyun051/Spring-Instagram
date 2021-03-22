package com.cos.costagram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

	@GetMapping({"/","/image/feed"})
	public String feed() {
		return "image/feed";
	}
	
	@GetMapping("image/explore")
	public String explore() {
		return "image/explore";
	}
	
	@GetMapping("image/upload")
	public String upload() {
		return "image/upload";
	}
}
