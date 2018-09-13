package com.zxc.web.advice;

import com.alibaba.fastjson.JSON;
import com.zxc.enums.ErrorCode;
import com.zxc.util.Constants;
import com.zxc.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * _User: zxc
 * Date: 2018-08-22
 * Time: 16:48
 */
//@Component
@Slf4j
@Deprecated
public class ExceptionHandlerResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception e) {
        log.error("服务运行异常", e);
        try {
            IOUtils.write(JSON.toJSONString(new ResultDto(ErrorCode.ERROR,e.getMessage())), response.getOutputStream(), Constants.DEFAULT_CHARSET);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}