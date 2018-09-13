package com.zxc.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface VRedis {

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long expire);

    void set(String key, String value, long expire, TimeUnit timeUnit);

    void del(String key);

    void decrement(String key, Long num);

    void increment(String key, Long num);

    void hashSet(String key, String keyIndex, String data);

    Object hashGet(String key, String keyIndex);

    Map<String, String> hashGetAll(String key);

    void hashDel(String key, String keyIndex);
}
