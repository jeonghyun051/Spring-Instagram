package com.cos.costagram.domain.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	 
	// write (Modifying를 써줘야함)
	
	//네이밍 쿼리(jpa가 제공해주는 것, 간단한 쿼리일 때 사용)
	//List<Follow> findByFromUserId(int userId);
	
	@Modifying // javax 의 트랜잭션, 서비스에서는 프레임워크
	@Query(value = "INSERT INTO follow(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId,now())", nativeQuery = true)
	int mFollow(int fromUserId, int toUserId); // prepareStatement updateQuery() => -1, 0, 1 응답의 결과 그래서 int로 받기

	@Modifying // javax 의 트랜잭션, 서비스에서는 프레임워크
	@Query(value = "DELETE FROM follow WHERE fromUserId = :fromUserId AND toUserId=:toUserId", nativeQuery = true)
	int mUnFollow(int fromUserId, int toUserId); // prepareStatement updateQuery() => -1, 0, 1 응답의 결과 그래서 int로 받기
	
	@Query(value = "select count(*) from follow where fromUserId = :principalId AND toUserId = :userId", nativeQuery = true)
	int mFollowState(int principalId, int userId);
	
	@Query(value = "select count(*) from follow where fromUserId = :userId", nativeQuery = true)
	int mFollowCount(int userId);
	

}
