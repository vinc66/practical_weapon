package com.zxc.util;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * _User: zxc
 * Date: 2018-07-30
 * Time: 11:50
 */
public class UUIDUtils {

    public static String random() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
}
