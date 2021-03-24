package com.cos.costagram.service;

import org.springframework.stereotype.Service;

import com.cos.costagram.domain.user.User;
import com.cos.costagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
   
   private final UserRepository userRepository;
   
   public void 회원프로필(int userId) throws IllegalAccessException {
      
      User userEntity = userRepository.findById(userId).orElseThrow(()->{
         return new IllegalAccessException();
      });
   }
}