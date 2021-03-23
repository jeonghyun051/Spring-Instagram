package com.cos.costagram.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.costagram.service.AuthService;
import com.cos.costagram.service.UserService;
import com.cos.costagram.utils.Script;
import com.cos.costagram.web.auto.dto.UserJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm(){

	return "auth/joinForm";
	}
	
	//회원가입
	//회원가입할때 비밀번호 해쉬해줘야함
	@PostMapping("/auth/join")
	public @ResponseBody String join(UserJoinReqDto userJoinReqDto){
		authService.회원가입(userJoinReqDto.toEntity());

	return Script.href("성공", "/auth/loginForm");
	}
}
