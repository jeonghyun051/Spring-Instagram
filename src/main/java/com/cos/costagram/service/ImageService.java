package com.cos.costagram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.domain.image.Image;
import com.cos.costagram.domain.image.ImageRepository;
import com.cos.costagram.domain.tag.Tag;
import com.cos.costagram.domain.tag.TagRepository;
import com.cos.costagram.utils.TagUtils;
import com.cos.costagram.web.dto.image.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final TagRepository tagRepository;
	
	@Value("${file.path}") // 팩토리 어노테이션 , 이제 부터 yml 파일에 접근 할 수 있음.
	private String uploadFolder;
	
	@Transactional(readOnly = true)
	public List<Image> 인기사진(int principalId){
		return imageRepository.mExplore(principalId);
	}
	
	public List<Image> 피드이미지(int principalId){
		
		//1. principalId로 내가 팔로우 하고 있는 사용자를 찾아야 됨. (한개이거나 컬렉션이거나)
		//SELECT toUserId FROM follow where toUser=principalId
		//그리고 애들이 가지고있는 이미지를 뿌려야함
	
		List<Image> images = imageRepository.mFollowFeed(principalId);
		
		// 좋아요 하트 색깔 로직 + 좋아요 카운트 로직
		images.forEach((image)->{
			
			int likeCount = image.getLikes().size();
			image.setLikeCount(likeCount);
			
			image.getLikes().forEach((like)->{
				if(like.getUser().getId() == principalId) { // 좋아요한사람이랑 이미지 유저의 아이디랑
					image.setLikeState(true);
				}
			});
		});
		
		return images;
	}
	
	@Transactional
	public void 사진업로드(ImageReqDto imageReqDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageReqDto.getFile().getOriginalFilename();
		System.out.println("파일명" + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName); // 파일에 대한 경로 이 경로에 input 사진을 쓰기함
		System.out.println("파일패스"+imageFilePath);
		
		try {
			//파일 쓰기 통신이기 때문에 트라이켓치
			Files.write(imageFilePath, imageReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 1.image 엔티티에 Tag는 주인이 아니다. image 엔티티로 통해서 tag를 save할 수 없다.
		
		// 1.image 저장
		Image image = imageReqDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		// 2 Tag 저장
		List<Tag> tags = TagUtils.parsingToTagObject(imageReqDto.getTags(),imageEntity);
		tagRepository.saveAll(tags); // 컬렉션 한방에 저장
				
	}
}

