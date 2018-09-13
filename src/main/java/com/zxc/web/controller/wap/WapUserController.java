package com.zxc.web.controller.wap;

import com.google.code.kaptcha.Producer;
import com.zxc.cache.VCache;
import com.zxc.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Administrator on 2018/4/17.
 */


@Api(value = "网页用户", description = "网页用户接口")
@RestController
@RequestMapping("/wy/wap/user")
@Slf4j
public class WapUserController {

    @Autowired
    private VCache zxcCache;

    @Autowired
    private Producer captchaProducer;


    @ApiOperation(value = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),})
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultDto logout(HttpServletRequest httpRequest, HttpServletResponse response) {
        return ResultDto.newInstance();
    }


    @ApiOperation(value = "获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query"),
    })
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public ResultDto token(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "phone") String phone) throws IOException {

        response.setDateHeader("Expires", 0);// 禁止server端缓存
        // 设置标准的 HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 设置IE扩展 HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");// 设置标准 HTTP/1.0 不缓存图片
        response.setContentType("image/jpeg");// 返回一个 jpeg 图片，默认是text/html(输出文档的MIMI类型)
        String capText = captchaProducer.createText();// 为图片创建文本

        zxcCache.putCode(phone, capText);
        BufferedImage bi = captchaProducer.createImage(capText); // 创建带有文本的图片
        ServletOutputStream out = response.getOutputStream();
        // 图片数据输出
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        log.info("Session 验证码 {}", capText);
        return null;
    }

}
