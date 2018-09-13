package com.zxc.cache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class VRedisTemplate implements VRedis {

    private VMasterSlaveStrategy shardingStrategy;

    public VMasterSlaveStrategy getShardingStrategy() {
        return shardingStrategy;
    }

    public void setShardingStrategy(VMasterSlaveStrategy shardingStrategy) {
        this.shardingStrategy = shardingStrategy;
    }

    @Override
    public String get(String key) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.SELECT);
        try {
            return template.opsForValue().get(key);
        } catch (Exception e) {
            log.error("redis 操作失败", e);
        }
        return null;
    }

    @Override
    public void set(String key, String value) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            template.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error("redis 操作失败", e);
        }
    }

    @Override
    public void set(String key, String value, long expire) {
        set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value, long expire, TimeUnit timeUnit) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            template.opsForValue().set(key, value, expire, timeUnit);
        } catch (Exception e) {
            log.error("redis 操作失败", e);
        }
    }

    @Override
    public void del(String key) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            template.delete(key);
        } catch (Exception e) {
            log.error("redis 操作失败", e);
        }
    }

    @Override
    public void decrement(String key, Long num) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            template.opsForValue().increment(key, -num);
        } catch (Exception e) {
            log.error("redis 减操作失败", e);
        }
    }

    @Override
    public void increment(String key, Long num) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            template.opsForValue().increment(key, num);
        } catch (Exception e) {
            log.error("redis 加操作失败", e);
        }
    }

    @Override
    public void hashSet(String key, String keyIndex, String data) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            template.opsForHash().put(key, keyIndex, data);
        } catch (Exception e) {
            log.error("redis 加操作失败", e);
        }
    }

    @Override
    public String hashGet(String key, String keyIndex) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            Object o = template.opsForHash().get(key, keyIndex);
            if (o instanceof String)
                return o.toString();
        } catch (Exception e) {
            log.error("redis 加操作失败", e);
        }
        return null;
    }

    @Override
    public Map<String, String> hashGetAll(String key) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            Map<Object, Object> entries = template.opsForHash().entries(key);
            if (MapUtils.isNotEmpty(entries)) {
                Map<String, String> res = Maps.newHashMap();
                entries.entrySet().stream().forEach(en -> {
                    res.put(en.getKey().toString(), en.getValue().toString());
                });
                return res;
            }
        } catch (Exception e) {
            log.error("redis 加操作失败", e);
        }
        return null;
    }

    @Override
    public void hashDel(String key, String keyIndex) {
        RedisTemplate<String, String> template = shardingStrategy.getTemplate(RedisHandleType.UPDATE);
        try {
            template.opsForHash().delete(key,keyIndex);
        } catch (Exception e) {
            log.error("redis 加操作失败", e);
        }
    }

}
