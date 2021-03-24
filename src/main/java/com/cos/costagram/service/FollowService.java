package com.cos.costagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.follow.Follow;
import com.cos.costagram.domain.follow.FollowRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {

	private final FollowRepository followRepository;
	
	@Transactional // 프레임워크꺼
	public int 팔로우(int fromUserId, int toUserId) {
		
		return followRepository.mFollow(fromUserId, toUserId);
	}
	
	@Transactional // 프레임워크꺼
	public int 언팔로우(int fromUserId, int toUserId) {
		
		return followRepository.mUnFollow(fromUserId, toUserId);
	}
}
