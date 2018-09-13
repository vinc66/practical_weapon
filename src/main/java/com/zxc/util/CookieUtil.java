package com.zxc.util;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieUtil {

    public static void add(String key, String value, HttpServletResponse response) {
        Cookie c = new Cookie(key, value);
        c.setPath("/");
        response.addCookie(c);
    }

    public static void clear(String key, HttpServletResponse response) {
        Cookie c3 = new Cookie(key, null);
        c3.setPath("/");
        c3.setMaxAge(0);
        response.addCookie(c3);
    }


    public static String get(String key, HttpServletRequest httpRequest) {
        Cookie cookie = WebUtils.getCookie(httpRequest, key);
        String name = "";
        if (cookie != null) {
            try {
                name = URLDecoder.decode(cookie.getValue(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("cookie value 转码失败");
            }
        }
        return name;
    }
}