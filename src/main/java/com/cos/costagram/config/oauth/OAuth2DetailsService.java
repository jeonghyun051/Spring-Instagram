package com.cos.costagram.config.oauth;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.domain.user.RoleType;
import com.cos.costagram.domain.user.User;
import com.cos.costagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("OAuth 로그인 진행중");
		System.out.println("토큰 : " + userRequest.getAccessToken());

		// 1. AccessTocken으로 회원정보를 받았다는 의미 회원정보 요청 -> 토큰이 있음
		OAuth2User oauth2User = super.loadUser(userRequest);

		// 레트로핏
		System.out.println("oauth2User.getAttributes() : " + oauth2User.getAttributes());

		return processOAuth2User(userRequest, oauth2User); // null이면 로그인이 안됐다는 뜻.
	}

	// 구글,페이스북 로그인 프로세스
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oauth2User) {

		// userRequest : 뭐로 로그인 됐는지 정보를 가지고있음 (ex : google,naver,facebook)
		// oAuth2User : attribute 를 가지고있다.
		System.out.println("머로 로그인 됐지?" + userRequest.getClientRegistration().getClientName()); // Google, Facebook 이런게 나옴
		
		// 1번 통합 클래스를 생성
		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getClientName().equals("Google")) {
			//oAuth2UserInfo = new GoogleInfo(oauth2User.getAttributes());

		} else if (userRequest.getClientRegistration().getClientName().equals("Facebook")) {
			oAuth2UserInfo = new FacebookInfo(oauth2User.getAttributes());
		}

		// 2번 최초 : 회원가입 + 로그인 최초X : 로그인
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());

		UUID uuid = UUID.randomUUID();
		String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());

		if (userEntity == null) {
			User user = User.builder()
					.username(oAuth2UserInfo.getUsername())
					.password(encPassword)
					.email(oAuth2UserInfo.getEmail())
					.role("USER")
					.build();
			userEntity = userRepository.save(user);
			return new PrincipalDetails(userEntity, oauth2User.getAttributes());

		} else { // 이미 회원가입이 되어있다는 뜻 (원래는 구글 정보가 변경될 수 있기 때문에 update 해야되는데 지금은 안한다)

			return new PrincipalDetails(userEntity, oauth2User.getAttributes());
		}
	}
}