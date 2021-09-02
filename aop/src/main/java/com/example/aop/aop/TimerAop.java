package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

// Bean과 Component의 차이
// Bean의 경우 클래스에 붙일 수 없음. 메소드에 붙일 수 있음.
// Component: 클래스에 붙일 수 있으며 하나만 등록해도 클래스에 속한 메소드들이 Bean으로 동작함.
@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {
    }

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer() {
    }

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();
        // ProceedingJointPoint: 실행하는 시점을 정할 수 있는 듯.
        // proceed를 호출하면 정해둔 메소드가 실행되고, 메소드가 반환하면 result 에 저장됨.
        stopWatch.stop();
        System.out.println("total time: " + stopWatch.getTotalTimeSeconds());
    }
}
