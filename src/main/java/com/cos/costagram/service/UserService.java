package com.cos.costagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.follow.FollowRepository;
import com.cos.costagram.domain.image.ImageRepository;
import com.cos.costagram.domain.user.User;
import com.cos.costagram.domain.user.UserRepository;
import com.cos.costagram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final FollowRepository followRepository;

	@Transactional(readOnly = true) // 스프링 프레임워크꺼 javax ㄴ
	public UserProfileRespDto 회원프로필(int userId, int principalId) throws IllegalAccessException {
		UserProfileRespDto userProfileRespDto = new UserProfileRespDto();

		User userEntity = userRepository.findById(userId).orElseThrow(() -> {
			return new IllegalArgumentException();
		});

		int followState = followRepository.mFollowState(principalId, userId);
		int followCount = followRepository.mFollowCount(userId);
		System.out.println(followState == 1);

		userProfileRespDto.setFollowState(followState == 1);
		userProfileRespDto.setFollowCount(followCount); // 내가 팔로우 하고 있는 카운트
		userProfileRespDto.setImageCount(userEntity.getImages().size());

		userEntity.getImages().forEach((image) -> {
			image.setLikeCount(image.getLikes().size());
		});

		userProfileRespDto.setUser(userEntity);

		return userProfileRespDto;
	}
}