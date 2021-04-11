package com.cos.costagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.comment.Comment;
import com.cos.costagram.domain.comment.CommentRepository;
import com.cos.costagram.domain.image.Image;
import com.cos.costagram.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;

	@Transactional
	public Comment 댓글쓰기(User principal,String content, int imageId) {
		
		Image image = Image.builder()
				.id(imageId)
				.build();
		
		// Save할 때 연관관계가 있으면 오브젝트로 만들어서 id값만 넣어주면 된다.
		Comment comment = Comment.builder()
				.content(content)
				.image(image)
				.user(principal)
				.build();
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public void 댓글삭제(int id, int principalId) {
		
		Comment commentEntity = commentRepository.findById(id).get();
		
		if (commentEntity.getUser().getId() == principalId) {
			commentRepository.deleteById(id);
		} else {
			// 스로우 익센션 날려서 ControllAdive 처리
			// 여기는 정상적인 사람이 아님, 블랙리스트 ip따서 넣어 놓는 것도 가능
		}
		
		
	}
	
}
