<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="main">
	<section class="container">
		<!--전체 리스트 시작-->
		<article class="story-list">


			<c:forEach var="image" items="${images}">
				<!--전체 리스트 아이템-->
				<div class="story-list__item">
					<!--리스트 아이템 헤더영역-->
					<div class="sl__item__header">
						<div>
							<img src="/images/profile.jpeg" alt="" />
							<svg viewbox="0 0 110 110">
                  <circle cx="55" cy="55" r="53" />
                </svg>
						</div>
						<div>${image.user.username}</div>
					</div>
					<!--헤더영역 end-->

					<!--게시물이미지 영역-->
					<div class="sl__item__img">
						<img src="/upload/${image.postImageUrl}" alt="" />
					</div>

					<!--게시물 내용 + 댓글 영역-->
					<div class="sl__item__contents">
						<!-- 하트모양 버튼 박스 -->
						<div class="sl__item__contents__icon">
								<c:choose>					
									<c:when test="${image.likeState}">
										<button onclick="likeOrUnLike(${image.id})">
											<i class="fas fa-heart active"></i>
										</button>
									</c:when>
									<c:otherwise>
										<button onclick="likeOrUnLike(${image.id})">
											<i class="far fa-heart"></i>
										</button>
									</c:otherwise>
								</c:choose>
								
							
						</div>
						<!-- 하트모양 버튼 박스 end -->

						<!--좋아요-->
						<span class="like"><b id="like_count-${image.id}">${image.likeCount}</b>likes</span>
						<!--좋아요end-->

						<!--태그박스-->
						<div class="sl__item__contents__tags">
							<p>
								<c:forEach var="tag" items="${image.tags}">
									#${tag.name} 
								</c:forEach>
							</p>
						</div>
						<!--태그박스end-->

						<!--게시글내용-->
						<div class="sl__item__contents__content">
							<p>${image.caption}</p>
						</div>
						<!--게시글내용end-->
						
						<!-- 댓글 들어오는 박스 -->
						<div>
							<div class="sl__item__contents__comment">
								
							</div>
						</div>
						<!-- 댓글 들어오는 박스end -->

						<!--댓글입력박스-->
						<div class="sl__item__input">
							<input type="text" placeholder="댓글 달기..." />
							<button type="button" onClick="addComment(1, 'username')">게시</button>
						</div>
						<!--댓글달기박스end-->
					</div>
				</div>
				<!--전체 리스트 아이템end-->
			</c:forEach>
		</article>
	</section>
</main>
<script src="/js/like.js"></script>
</body>
</html>