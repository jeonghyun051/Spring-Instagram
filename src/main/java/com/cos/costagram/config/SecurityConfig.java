package com.cos.costagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	// 모델 : image, User, Likes, Follow, Tag : 인증 필요함
	// Auth 주소 : 인증 필요없음.
	// static  폴더
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		//http.cors().disable(); // 컨트롤러에 CrossOrigin을 붙여도, 시큐리티에서 막힘
		http.authorizeRequests()
			.antMatchers("/", "/user/**", "/image/**", "/follow/**","/comment/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //막을것만 막고 나머진 허용 / authenticated:로그인만하면 허용 /
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest()
			.permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/login") // post/login 주소를 디스패처가 확인하면 필터가 낚아챔
			.defaultSuccessUrl("/");
		
			// OAuth2.0과 CORS는 나중에!!
		
	}
}
