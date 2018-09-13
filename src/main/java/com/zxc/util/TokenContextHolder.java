package com.zxc.util;

public class TokenContextHolder {

    private static ThreadLocal<String> t = new ThreadLocal<String>();

    public static void put(String s) {
        t.set(s);
    }

    public static String get() {
        return t.get();
    }

    public static void clear() {
        t.remove();
    }


}
