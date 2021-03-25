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
      
      int imageCount = imageRepository.mImageCount(userId);
      int followCount = followRepository.mFollowCount(userId);
      boolean followState = followRepository.mFollowState(userId, principalId);
      
      userProfileRespDto.setFollowState(followState);
      userProfileRespDto.setImageCount(imageCount);
      userProfileRespDto.setFollowCount(followCount);
      userProfileRespDto.setUser(userEntity);
      
      System.out.println("값좀보자" + userProfileRespDto);
      
      return userProfileRespDto;
   }
}