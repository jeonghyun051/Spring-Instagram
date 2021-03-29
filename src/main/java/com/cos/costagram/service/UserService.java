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
   private final ImageRepository imageRepository;
   
   @Transactional(readOnly = true) //스프링 프레임워크꺼 javax ㄴ
   public UserProfileRespDto 회원프로필(int userId, int principalId) throws IllegalAccessException {
      UserProfileRespDto userProfileRespDto = new UserProfileRespDto();

      User userEntity = userRepository.findById(userId).orElseThrow(()->{
         return new IllegalAccessException();
      });
      

      int followCount = followRepository.mFollowCount(userId);
      int followState = followRepository.mFollowState(principalId,userId);
      
      userProfileRespDto.setFollowState(followState == 1); // 내가 팔로우하고 있는지 상태
      userProfileRespDto.setImageCount(userEntity.getImages().size()); // 내가 팔로우하고 있는 카운터
      userProfileRespDto.setFollowCount(followCount);
      userProfileRespDto.setUser(userEntity);
      
      System.out.println("값좀보자" + userProfileRespDto);
      
      return userProfileRespDto;
   }
}