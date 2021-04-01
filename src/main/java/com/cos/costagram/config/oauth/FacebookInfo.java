package com.cos.costagram.config.oauth;

import java.util.Map;

public class FacebookInfo extends OAuth2UserInfo{

	public FacebookInfo(Map<String, Object> attributes) {
		super(attributes);
		
	}

	@Override
	public String getId() {
		
		return (String)attributes.get("id");
	}

	@Override
	public String getName() {
		
		return (String)attributes.get("name");
	}

	@Override
	public String getEmail() {
		
		return (String)attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		
		return null;
	}

	@Override
	public String getUsername() {
		
		return "Facebook_"+(String)attributes.get("id"); // sub는 중복될 수 있는데 여기 앞에 어디서 로그인했는지 적어놓으면 중복될수 없음 프라이머리키가 될수있다.
	}
	

}