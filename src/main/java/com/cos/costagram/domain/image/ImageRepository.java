package com.cos.costagram.domain.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ImageRepository extends JpaRepository<Image, Integer>{
	
	
	@Query(value = "select * from image where userId in (select toUserId from follow where fromUserId = :principalId) order by id desc", nativeQuery = true)
	List<Image> mFollowFeed(int principalId); 

	// 인기사진
	@Query(value = "select * from image where id in (select imageId from (select imageId, count(imageId) likeCount from likes where userId = :principalId group by imageId order by 2 desc) t)", nativeQuery = true)
	List<Image> mExplore(int principalId);
}
