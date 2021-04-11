package com.cos.costagram.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.costagram.config.auth.PrincipalDetails;
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
	
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public User 회원사진변경(MultipartFile profileImageFile, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename();
		//System.out.println("파일명 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		//System.out.println("파일패스 : "+imageFilePath);
		try {
			Files.write(imageFilePath, profileImageFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User userEntity = userRepository.findById(principalDetails.getUser().getId()).get();
		userEntity.setProfileImageUrl(imageFileName);
		
		return userEntity;
	} // 더티체킹


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