package com.cos.costagram.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.costagram.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{

	private User user;	
	
	public PrincipalDetails(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
        //해당 User의 권한을 리턴하는 곳 getRole을 바로 return 할수 없음
		Collection<GrantedAuthority> collectors = new ArrayList<>(); // 자바 유틸꺼임, collectors에 권한을 넣어줘야함
		collectors.add(()-> "ROLE_"+user.getRole().toString()); // collectors 에 리턴되서 들어간다.
		//컬렉터에 GrantedAuthority타입을 넣어줘야한다.
  
		// 위랑 같음
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				
//				return user.getRole().toString(); //collectors 에 리턴되서 들어간다
//			}
//		});
		
		return collectors;
	}
}
