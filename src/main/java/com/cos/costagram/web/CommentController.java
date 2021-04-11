package com.cos.costagram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.service.CommentService;
import com.cos.costagram.web.dto.CMRespDto;
import com.cos.costagram.web.dto.comment.CommentReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // 부분 변경해야지 ajax로 , 댓글 쓴다고 페이지이동해서 새로고침하면 미친 짓
public class CommentController {

	private final CommentService commentService;
	
	@DeleteMapping("/comment/{id}")
	public CMRespDto<?> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		// 딴놈이 포스트맨으로 접속해서 댓글 삭제할 수 있으니 프린시펄 아이디 던져줌
		commentService.댓글삭제(id,principalDetails.getUser().getId());
		return new CMRespDto<>(1,null);
		
	}
}