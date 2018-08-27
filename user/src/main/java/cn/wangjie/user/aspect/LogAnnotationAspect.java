package cn.wangjie.user.aspect;

import cn.wangjie.user.aspect.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloudDemo
 * @description: 注解式aop
 * @author: WangJie
 * @create: 2018-08-27 17:24
 **/
@Aspect
@Component
@Slf4j


public class LogAnnotationAspect {
    @Before("@annotation(logAnnotation)")
    public void doBefore(LogAnnotation logAnnotation){
        log.info("注解形式aop @Before 获取注解信息:{}",logAnnotation.note());
    }
}
