package com.cos.costagram.web.dto.user;

import com.cos.costagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileRespDto {

	private boolean followState; // 구독 정보
	private int followCount;
	private int imageCount;
	private User user;
	
	//save가 아니라서 toEntity가 필요없다.
}
