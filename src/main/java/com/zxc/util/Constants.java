package com.zxc.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: vincent  .
 * Date  : 2018/4/18  .
 * Desc  :
 */
public interface Constants {
    String DEFAULT_CHARSET = "UTF-8";
    //    手机号验证码过期时间
    int PHONE_CODE_EXPIRE = 180;
    //    token失效时间
    int TOKEN_EXPIRE = 7;
    String COOKIE_ACCOUNT_NAME = "_u";
    //    文件最大字节500M
    long MAX_FILE_SIZE = 524288000;
//    图片大小10M
    long MAX_PIC_SIZE = 10485760;
//    订单平台标志过期时间
    int ORDER_EXPIRE = 1800;

//    默认公海时间
    String IDLE_DEFAULT = "7";

    static String getMessageClazz() {
        // FIXME: 2018/9/13
        return null;
    }

    String FILE_PATH = "/app/upload/";


}
