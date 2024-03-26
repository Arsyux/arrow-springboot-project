package com.arsyux.arrow.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// 환경설정 클래스
// 다국어 설정
@Configuration
public class ArrowWebMvcConfiguration implements WebMvcConfigurer {
	
	// 스프링 컨테이너가 ModelMapper를 생성할 수 있도록 @Bean 어노테이션 등록
	@Bean
	public ModelMapper modelMapper() { return new ModelMapper(); }
	
	// 다국어 설정 적용
	@Override
	public void addInterceptors(InterceptorRegistry registry) { registry.addInterceptor(localeChangeInterceptor()); }
	
	// SessionLocaleResolver : 로케일 추출 및 등록, 세션이 종료될 때까지 로케일 유지
	@Bean
	public LocaleResolver localeResolver() { return new SessionLocaleResolver(); }
	
	// 다국어 처리
	@Bean("messageSource")
	public MessageSource messageSource() {
		// messageSource_en.properties, messageSource_ko.properties에 등록된 메시지를 메모리 로딩
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message/messageSource");
		return messageSource;
	}
	
	// LocaleChangeInterceptor : 파라미터 lang으로 로케일 정보를 전달하면 기존의 로케일을 변경해줌
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	// 썸머노트 이미지 업로드시 정적 자원 루트 지정 (이미지 파일 업로드)
	
    // 주소창 입력값
    private String connectPath = "/image/test/**";

    // 업로드 파일  위치
    private String resourcePath = "file:///C:/DEV/eclipse-workspace/arrow-springboot-project/Arrow/src/main/resources/static/image/test/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        // 업로드 이미지 정적 리소스 설정
        registry.addResourceHandler(connectPath).addResourceLocations(resourcePath);

        // 기본 정적 파일 설정
        registry.addResourceHandler("resources/**").addResourceLocations("classpath:/static/");
    }
}
