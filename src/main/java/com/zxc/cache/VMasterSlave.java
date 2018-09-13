package com.zxc.cache;


import org.springframework.data.redis.core.RedisTemplate;

public interface VMasterSlave {
    RedisTemplate<String, String> getTemplate(RedisHandleType redisHandleType);
}
