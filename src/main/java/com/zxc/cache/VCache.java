package com.zxc.cache;

import com.zxc.util.Constants;
import com.zxc.util.TokenContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class VCache {


    @Autowired
    private VRedis vRedis;

    public void putCode(String phone, String code) {
        vRedis.set(CacheUtils.phoneCode(phone), code, Constants.PHONE_CODE_EXPIRE, TimeUnit.SECONDS);
    }

    public String getCode(String phone) {
        return vRedis.get(CacheUtils.phoneCode(phone));
    }


    public void putToken(String phone, String userId, String token) {
        vRedis.set(CacheUtils.phone(token), phone, Constants.TOKEN_EXPIRE, TimeUnit.DAYS);
        vRedis.set(CacheUtils.userId(token), userId, Constants.TOKEN_EXPIRE, TimeUnit.DAYS);
    }

    public String getPhone() {
        return vRedis.get(CacheUtils.phone(TokenContextHolder.get()));
    }


    public String getPhone(String token) {
        return vRedis.get(CacheUtils.phone(token));
    }


    public String getUserId() {
        String ifPresent = vRedis.get(CacheUtils.userId(TokenContextHolder.get()));
        if (StringUtils.isBlank(ifPresent))
            throw new RuntimeException("token失效，无法获取用户id");
        return ifPresent;
    }

    public Integer getUserIdInt() {
        return Integer.valueOf(getUserId());
    }

    public void delToken() {
        vRedis.del(CacheUtils.phone(TokenContextHolder.get()));
        vRedis.del(CacheUtils.userId(TokenContextHolder.get()));
    }

    public String getPhoneToken(String phone) {
        return vRedis.get(CacheUtils.token(phone));
    }

    public void delToken(String phone, String userId) {
        vRedis.del(CacheUtils.phone(phone));
        vRedis.del(CacheUtils.userId(userId));
    }

}
