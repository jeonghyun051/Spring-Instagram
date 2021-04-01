package com.cos.costagram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

//web.xml 파일 이라고 생각하면 된다

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${file.path}") // 팩토리 어노테이션 , 이제 부터 yml 파일에 접근 할 수 있음.
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/upload/**") // url패턴:/upload/파일명 -> 낚아챈다. 그리고 밑에 경로를 잡아준다. 업로드에 내부에 있는것을 찾겟다.
		.addResourceLocations("file:///"+uploadFolder) // 실제 물리적인 경로
		.setCachePeriod(60*10*6)
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}
}
