package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


// - 필터
// > 클라이언트로부터 보내는 요청을 가장 먼저 받는 역할을 함.
// > 클라이언트로 보내는 응답을 가장 늦게 받는 역할을 함.
// > 로깅이나 로그인 처리, 권한 관련 처리를 할 때 많이 사용한다.
@Slf4j
// @Component: 모든 패키지에 필터를 걸고 싶다면 사용
@WebFilter(urlPatterns = "/api/user/*") // 특정 url에만 필터를 걸고 싶을 때는 main에 @ServletComponentScan 을 추가하고 해당 어노테이션을 추가
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
        // ContentCachingRequestWrapper
        // 기존의 HttpServletRequest의 getReader를 호출할 경우 한 번 내용을 읽으면 커서로 인해 다시 읽을 수 없음.
        // 그러나 ContentCachingRequestWrapper의 경우 여러번 호출해도 안의 내용을 오류 없이 읽을 수 있음.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);




        chain.doFilter(httpServletRequest, httpServletResponse);

        // 후처리

        // 왜 후처리에 Content를 다루는가?
        // ContentCachingRequestWrapper의 경우 생성자에서 길이만 지정하지 내용을 담지 않음.
        // 실제로 Body를 쓰는 부분은 WriteToCache함수 부분임
        // 스프링이 안에서 매핑이 끝난 다음에 읽어야 함.
        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url: {}, request body: {}", url, reqContent);

        // 컨트롤러를 다 타고 리스폰스에 담겨서 옴
        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        // getContentAsByteArray를 호출할 경우 다시 커서가 끝까지 가기 때문에 실제 response에 담겨가지 않음.
        // 따라서 해당 부분을 복사해주어야 함. 이때 실행하는 함수가 copyBodyToResponse
        httpServletResponse.copyBodyToResponse();
        log.info("response status: {}, responseBody: {}", httpStatus,resContent);
    }
}