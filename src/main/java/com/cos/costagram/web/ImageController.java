package com.cos.costagram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	
	
	@GetMapping({"/","/image/feed"})
	public String feed(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		// ssar이 누구를 팔로우했는지 정보확인 -> love
		// ssar로 로그인했으니 -> image1(love), image2(love) 만 들고 피드로 가게
		
		model.addAttribute("images",imageService.피드이미지(principalDetails.getUser().getId()));
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
