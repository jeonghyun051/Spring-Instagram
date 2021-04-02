package com.cos.costagram.web.dto.user;



import com.cos.costagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateReqDto {
	
	private String name;
	private String username;
	private String website;
	private String bio;
	private String email;
	private String phone;
	private String gender;
	
	public User toEntitiy() {
		
		return User.builder()
				.name(name)
				.username(username)
				.website(website)
				.bio(bio)
				.email(email)
				.phone(phone)
				.gender(gender)
				.build();
	}
	

}
