package com.danger.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.Ma on 2018/9/12.
 */
@Aspect
@Component
public class WebLogAop {

    //@Pointcut("execution(public * com.danger.controller.*.*(..))")

    @Pointcut("execution(public * com.danger.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore() {



    }
}
