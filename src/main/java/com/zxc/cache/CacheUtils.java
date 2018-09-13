package com.zxc.cache;

public class CacheUtils {

    public static String testKey(String jack) {
        return "name:" + jack;
    }

    public static String phoneCode(String phone) {
        return join("phone:token:", phone);
    }

    private static String join(String prefix, String cont) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(prefix).append(cont).toString();
    }

    public static String phone(String uuid) {
        return join("phone:", uuid);
    }

    public static String userId(String phone) {
        return join("user:", phone);
    }

    public static String baiduToken() {
        return "baidutoken";
    }

    public static String token(String phone) {
        return join("token:", phone);
    }

    public static String idleTime() {
        return "idle_time";
    }

    public static String role(String roleId) {
        return join("role:", roleId);
    }

    public static String init(String ios) {
        return join("init:",ios);
    }

    public static String checkCount(String userId) {
        return join("count:check:",userId);
    }

    public static String flowCount(String userId) {
        return join("count:flow:",userId);
    }

    public static String applyCount(String userId) {
        return join("count:apply:",userId);
    }

    public static String delay() {
        return "delay";
    }

    public static String platform(String orderId) {
        return join("platform:",orderId);
    }
}
