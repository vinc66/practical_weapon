package com.zxc.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 解决跨域
 * _User: vinc
 * Date: 2018-07-02
 * Time: 16:15
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/*", filterName = "crosFilter")
public class CorsFilter implements Filter {

    private String allowedOrigins = "*";
    private String allowedMethods = "GET, POST, OPTIONS";
    private String allowedHeaders = "*,token";
    private String allowCredentials = "false";
    private String maxAge = "1800";

    public CorsFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("配置跨域过滤器开始");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", this.allowedOrigins);
        response.setHeader("Access-Control-Allow-Methods", this.allowedMethods);
        response.setHeader("Access-Control-Allow-Headers", this.allowedHeaders);
        response.setHeader("Access-Control-Allow-Credentials", this.allowCredentials);
        response.setHeader("Access-Control-Max-Age", this.maxAge);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (!httpServletRequest.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(servletRequest, response);
        }
    }

    public void destroy() {
        log.info("配置跨域过滤器关闭");
    }
}