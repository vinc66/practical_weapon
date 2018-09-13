package com.zxc.enums;

/**
 * Created with IntelliJ IDEA.
 * Description: 错误码
 * _User: vinc
 * Date: 2018-06-29
 * Time: 11:01
 */
public enum ErrorCode {

    ERROR(-1, "失败"),
    SUCCESS(0, "成功"),
    AUTH_ERROR(999, "用户身份验证失败"),
    PARAM_ERROR(101, "参数不合法"),
    INVALIDATE_ROLE(102, "用户无此权限");

    public final int code;
    public final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

