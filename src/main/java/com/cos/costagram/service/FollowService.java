package com.cos.costagram.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.follow.FollowRepository;
import com.cos.costagram.web.dto.follow.FollowRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {

	private final FollowRepository followRepository;
	private final EntityManager em;
	
	@Transactional(readOnly = true)
	public List<FollowRespDto> 팔로우리스트(int principalId, int pageUserId){ // 로그인한사람, 페이지에 있는 사람
		
		// 쿼리문 맨뒤 한칸 띄어주기
		StringBuffer sb = new StringBuffer();
		sb.append("select u.id userId, u.username, u.profileImageUrl, ");
		sb.append("if((select True from follow where fromUserId = ? and toUserId = u.id),true,false) followState, "); //principalDetails.user.id
		sb.append("if(u.id = ?, true, false) equalState "); //principalDetails.user.id 
		sb.append("from follow f inner join user u on u.id = f.toUserId ");
		sb.append("where f.fromUserId = ?; "); // pageUserId
		
		Query query = em.createNativeQuery(sb.toString()) // Query 자바 펄시스턴스 꺼임
				.setParameter(1, principalId)
				.setParameter(2,principalId)
				.setParameter(3, pageUserId);
		
		JpaResultMapper result = new JpaResultMapper(); 
		List<FollowRespDto> followRespDtos = result.list(query, FollowRespDto.class);		
		
		return followRespDtos;
	}
	
	@Transactional // 프레임워크꺼
	public int 팔로우(int fromUserId, int toUserId) {
		
		return followRepository.mFollow(fromUserId, toUserId);
	}
	
	@Transactional // 프레임워크꺼
	public int 언팔로우(int fromUserId, int toUserId) {
		
		return followRepository.mUnFollow(fromUserId, toUserId);
	}
}