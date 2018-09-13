package com.zxc.web.advice;

import com.zxc.enums.ErrorCode;
import com.zxc.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: controller异常统一处理类保证 responseCode = 200
 * _User: zxc
 * Date: 2018-08-22
 * Time: 16:59
 */
@Component
@Aspect
@Slf4j
public class ControllerExceptionHandler {

    @Around("execution(* com.zxc.web.controller.*.*.*(..))")
    public Object pointCut(ProceedingJoinPoint jp) {
        Object[] args = jp.getArgs();
        Object rvt;
        try {
            rvt = jp.proceed(args);
        } catch (Throwable throwable) {
            log.error("服务运行异常 ", throwable);
            return new ResultDto(ErrorCode.ERROR, throwable.getMessage());
        }
        return rvt;
    }
}
