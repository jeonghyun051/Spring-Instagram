package com.cos.costagram.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.domain.user.User;
import com.cos.costagram.service.FollowService;
import com.cos.costagram.service.UserService;
import com.cos.costagram.web.dto.CMRespDto;
import com.cos.costagram.web.dto.follow.FollowRespDto;
import com.cos.costagram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	private final FollowService followService;
	
	//cors정책 막아야함
	@GetMapping("/user/{pageUserId}/follow") // restfull api data 리턴하는 것 / 유저 1번이 팔로우한 리스트를 볼 것이다. // 맨앞 단어로 컨트롤러 구분
	public @ResponseBody CMRespDto<?> followList(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int pageUserId){
		
		List<FollowRespDto> followRespDto = followService.팔로우리스트(principalDetails.getUser().getId(),pageUserId);
		
		return new CMRespDto<>(1,followRespDto); //팔로우컬렉션 리턴
	}

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) throws IllegalAccessException {
		UserProfileRespDto userProfileRespDto = userService.회원프로필(id,principalDetails.getUser().getId());
		model.addAttribute("dto",userProfileRespDto);
		return "user/profile";
	}

	@GetMapping("/user/{id}/profileSetting")
	public String profileSetting(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		//System.out.println("principalDetails은 뭐냐" + principalDetails.getUser());
//		model.addAttribute("principalDetails",principalDetails.getUser());
		return "user/profileSetting";
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody CMRespDto<?> profileUpdate(@PathVariable int id, User user, @AuthenticationPrincipal PrincipalDetails principalDetails){
		System.out.println(user);
		User userEntity = userService.회원수정(id, user);
		principalDetails.setUser(userEntity);
		return new CMRespDto<>(1, null);
	}
	 
	@PutMapping("/user/{id}/profileImageUrl")
	public @ResponseBody CMRespDto<?> profileImageUrlUpdate(@PathVariable int id, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails){
		User userEntity = userService.회원사진변경(profileImageFile, principalDetails);
		principalDetails.setUser(userEntity);
		return new CMRespDto<>(1, null);
	}
}
