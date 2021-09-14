package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.ClassArrayEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        // Handler
        // > Type, Formatting, Validation, service 등에 대한 정보를 가지고 있음.
        log.info("request url: {}", url);
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString()) // uri에 쿼리까지 같이 붙여주려면 사용함.
                .build()
                .toUri();
        // 어노테이션이 있는지 체크하는 것.
        boolean hasAnnotation = checkAnnotion(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 나의 서버는 모두 public으로 동작을 하나, 단 Auth 권한을 가진 요청에 대해서는 세션, 쿠키 등을 비교하고 검증한다.
        if(hasAnnotation){
            // 권한 체크
            String query = uri.getQuery();
            log.info("query: {}", query);
            if (query.equals("name=steve")) {
                return true;
            }
            throw new AuthException();
        }
        return true; // 이 이후로 컨트롤러까지 가지 못함.
    }

    public boolean checkAnnotion(Object handler, Class clazz) {
        // resource, javascript, html 과 같은 정적 파일에 대한 요청일 경우는 그냥 통과시킴
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // annotation이 달려 있는지에 대한 체크
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            // Auth annotation이 있을 때는 true
            return true;
        }
        return false;
    }
}
