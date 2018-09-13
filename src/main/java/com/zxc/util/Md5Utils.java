package com.zxc.util;

import org.springframework.util.DigestUtils;

public class Md5Utils {
    public static String encry(String pass) {
        return DigestUtils.md5DigestAsHex(pass.getBytes());
    }

    public static void main(String[] args) {
        String encry = encry("123321");
        System.out.println(encry);
    }
}
