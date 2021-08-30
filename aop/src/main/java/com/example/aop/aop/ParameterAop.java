package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect // aop를 적용해주기 위해서 사용함.
@Component
public class ParameterAop {

    // com.example.aop.controller 패키지의 하위에 있는 모든 메소드를 실행시킬 때 같이 해당 함수가 실행됨.
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());
        // JoinPoint: 들어가는 지점에 대한 정보를 가지고 있는 객체
        Object[] args = joinPoint.getArgs(); // 메소드에 들어가는 매개변수의 배열
        for(Object obj:args){
            System.out.println("type: "+obj.getClass().getSimpleName());
            System.out.println("value: "+ obj);
        }
    }

    // AfterReturning: 설정해 둔 메소드 실행 이후 해당 메소드가 정상 실행되고 반환하고 난 후 실행되는 것
    // value: 메소드 이름
    // returnObj: 리턴하는 오브젝트 이름.
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj){
        System.out.println("returnObj");
        System.out.println(returnObj);
    }
}
