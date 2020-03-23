package com.skgxsn.blog_idea2017.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect     // 创建切面
@Component      // 开启组件扫描
public class LogAspect {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Pointcut声明切面    execution指定拦截哪些类 --> web 下的所有方法
    @Pointcut("execution(* com.skgxsn.blog_idea2017.web.*.*(..))")
    public void log() {
    }

    // 在切面之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
    }

    // 在切面之后执行
    @After("log()")
    public void doAfter() {
//        logger.info("-------- doAfter -------------");

    }

    // 拦截方法返回的内容
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Result : {}", result);
    }

    // 定义返回方法的内部类
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethed, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethed;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
