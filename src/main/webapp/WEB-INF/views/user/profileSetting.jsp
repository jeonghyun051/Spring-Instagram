<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
    <%@ include file="../layout/header.jsp"%>
    
    <main class="main">
        <section class="setting-container">
            <article class="setting__content">
                <div class="content-item__01">
                    <div class="item__img"><img src="/images/profile.jpeg" alt=""></div>
                    <div class="item__btn">
                        <h2>아이디</h2>
                    </div>
                </div>
                <div class="content-item__02">
                    <div class="item__title">이름</div>
                    <div class="item__input">
                        <input type="text" name="name" placeholder="이름" />
                    </div>
                </div>
                <div class="content-item__03">
                    <div class="item__title">사용자 이름</div>
                    <div class="item__input">
                        <input type="text" name="useName" placeholder="사용자 이름" />
                    </div>
                </div>
                <div class="content-item__04">
                    <div class="item__title">웹사이트</div>
                    <div class="item__input">
                        <input type="text" name="website" placeholder="웹 사이트" />
                    </div>
                </div>
                <div class="content-item__05">
                    <div class="item__title">소개</div>
                    <div class="item__input">
                        <textarea name="intro" id="" rows="3"></textarea>
                    </div>
                </div>
                <div class="content-item__06">
                    <div class="item__title"></div>
                    <div class="item__input">
                        <span><b>개인정보</b></span>
                        <span>비즈니스나 반려동물 등에 사용된 계정인 경우에도 회원님의 개인 정보를 입력하세요. 공개 프로필에는 포함되지 않습니다.</span>
                    </div>
                </div>
                <div class="content-item__07">
                    <div class="item__title">이메일</div>
                    <div class="item__input">
                        <input type="text" name="email" placeholder="이메일" />
                    </div>
                </div>
                <div class="content-item__08">
                    <div class="item__title">전회번호</div>
                    <div class="item__input">
                        <input type="text" name="tel" placeholder="전화번호" />
                    </div>
                </div>
                <div class="content-item__09">
                    <div class="item__title">성별</div>
                    <div class="item__input">
                        <input type="text" name="gender" />
                    </div>
                </div>
                <div class="content-item__11">
                    <div class="item__title"></div>
                    <div class="item__input">
                        <button>제출</button>
                    </div>
                </div>
            </article>
        </section>
    </main>
    
    
    <%@ include file="../layout/footer.jsp" %>