package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // final로 선언된 객체가 주입받을 수 있게 설정해줌.
public class MvcConfig implements WebMvcConfigurer {
    // @AutoWired로 받을 수도 있으나, 순환참조 관련 문제로 최근에는 위의 어노테이션을 ㅓㄷ 많이 쓰낟.
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*"); // 특정 uri에서만 인터셉터가 동작하도록 설정.
    }
}
