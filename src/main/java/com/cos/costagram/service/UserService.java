package com.cos.costagram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.follow.FollowRepository;
import com.cos.costagram.domain.user.User;
import com.cos.costagram.domain.user.UserRepository;
import com.cos.costagram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final FollowRepository followRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;


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
	
	@Transactional
	public User 회원수정(int id, User user) {
		// username, email 수정 불가
		User userEntity = userRepository.findById(id).get();
		userEntity.setName(user.getName());
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setGender(user.getGender());

		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		return userEntity;
	} // 더티체킹 //함수종료일때 -> 트랙잭션 종료 -> 영속화 되어있는 데이터를 db로 갱신(flush) -> 디비에 commit 이것을 더티체킹이라고 한다.
}