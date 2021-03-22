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