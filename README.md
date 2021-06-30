# 인스타그램 클론 코딩
### 의존성
- Spring Boot DevTools
- Lombok
- Spring Data JPA
- Spring Security
- Spring Web
- Oauth2

```xml
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-jasper</artifactId>
	<version>9.0.43</version>
</dependency>

<!-- JSTL -->
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
	<version>1.2</version>
</dependency>

<!-- OAUTH2 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

### 데이터베이스
```mysql
create user 'costa'@'%' identified by 'costa1234';
GRANT ALL PRIVILEGES ON *.* TO 'costa'@'%';
create database costa;
```

### yml 설정
```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/costa?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true
    username: costa
    password: costa1234
    
  jpa: 
    open-in-view: true 컨트롤러가 끝날때까지 세션이 유지 
    hibernate:
      ddl-auto: create 할때마다 데이터베이스 생성
      naming: 네이밍 전략 방식 모델에있는 그대로 적힘
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet: 사진을 업로드 해야하는데 max사이즈 2mb으로 지정
    multipart:
      enabled: true
      max-file-size: 2MB

  security: 시큐리티 처음에 로그인 설정 user였는데 바꿀수 있음
    user:
      name: test
      password: 1234   

file: 사진을 업로드하면/ 배포를 하면 리눅스에서 경로를 설정해주면됨.
  path: C:/src/instagram/src/main/resources/upload/
```

### 태그라이브러리
```jpa
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
```
<br/>

### 기능
- 댓글 쓰기, 삭제
- 스크롤 페이징
- 좋아요
- 인기페이지 확인
- 다른 유저 구독
- 자신의 게시물 확인
- 구독 정보확인, 구독취소
- 회원정보 수정
- 게시물 쓰기 (태그)
- 프로필 사진 변경
- 페이스북 로그인(Oauth2)
<br/>
<br/>

### ✔로그인, 메인화면, 좋아요, 피드화면
![1 수정](https://user-images.githubusercontent.com/74044292/120471383-9a5f6b00-c3df-11eb-8238-d4976cbb4cbe.gif)
<br />
<br />

### ✔회원정보 화면, 로그아웃, 다른 유저 로그인
![2  인스타그램 회원정보화면, 로그아웃](https://user-images.githubusercontent.com/74044292/117572129-6c527800-b10c-11eb-8d6a-2973a1b3ee5c.gif)
<br />
<br />

### ✔프로필사진 변경, 게시물 쓰기
![3  인스타그램 프사바꾸기, 게시글 쓰기](https://user-images.githubusercontent.com/74044292/117572130-71172c00-b10c-11eb-9bcb-eb3e32d71fc3.gif)
<br />
<br />

### ✔팔로우한 유저로 로그인 하고 최신글 확인
![4  인스타그램 팔로우 글쓴거 확인](https://user-images.githubusercontent.com/74044292/117572140-770d0d00-b10c-11eb-9f09-6e229776886f.gif)


