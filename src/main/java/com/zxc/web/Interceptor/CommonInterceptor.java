package com.zxc.web.Interceptor;

import com.zxc.util.TokenContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.NDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Slf4j
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {
        NDC.push(String.valueOf(System.currentTimeMillis()));
        printReqMessage(request, response);
        return true;
    }

    private boolean printReqMessage(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuffer sb = new StringBuffer();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            sb.append(name).append("=").append(request.getParameter(name)).append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        String tokenHeader = request.getHeader("token");
        String tokenParam = request.getParameter("token");
        if(request.getRequestURI().contains("swagger") || request.getRequestURI().contains("api-docs")){
            return true;
        }
        log.info("====requestUrl :[{}] ,data :[{}] ,remoteIp :[{}] ", request.getRequestURI(), sb.toString(), request.getRemoteHost());
        log.info("====tokenHeader :[{}] ,tokenParam [{}]", tokenHeader, tokenParam);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenContextHolder.clear();
        NDC.clear();
    }

}