package com.zxc.web.Interceptor;

import com.alibaba.fastjson.JSON;
import com.zxc.cache.VCache;
import com.zxc.enums.ErrorCode;
import com.zxc.util.Constants;
import com.zxc.util.TokenContextHolder;
import com.zxc.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class BackInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private VCache vCache;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {
        if (authValid(request, response)) {
            return true;
        }
        outPut(response, ErrorCode.AUTH_ERROR);
        return false;
    }

    private boolean authValid(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token) || StringUtils.isBlank(vCache.getPhone(token))) {
            log.error("后台验证失败");
            return false;
        }
        TokenContextHolder.put(token);
        return true;
    }


    private void outPut(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        IOUtils.write(JSON.toJSONString(new ResultDto(errorCode)), out, Constants.DEFAULT_CHARSET);
        response.setContentType(ContentType.APPLICATION_JSON.withCharset(Constants.DEFAULT_CHARSET).toString());
        try {
            out.flush();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}