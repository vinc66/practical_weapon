package com.zxc.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class VMasterSlaveStrategy implements VMasterSlave, InitializingBean {

    private Map<String, RedisTemplate<String, String>> redisTemplateMap = new HashMap<>();
    private static final String DEFAULT_MASTER = "defaultMaster";
    private static final String DEFAULT_SLAVE = "defaultSlave";


    @Override
    public void afterPropertiesSet() throws Exception {
        if (redisTemplateMap.get(DEFAULT_MASTER) == null) {
            throw new RuntimeException("redis请配置master");
        } else if (redisTemplateMap.get(DEFAULT_SLAVE) == null) {
            throw new RuntimeException("redis请配置slave");
        }
        log.info("redis配置成功");
    }

    public Map<String, RedisTemplate<String, String>> getRedisTemplateMap() {
        return redisTemplateMap;
    }

    public void setRedisTemplateMap(Map<String, RedisTemplate<String, String>> redisTemplateMap) {
        this.redisTemplateMap = redisTemplateMap;
    }

    @Override
    public RedisTemplate<String, String> getTemplate(RedisHandleType redisHandleType) {
        return redisHandleType == RedisHandleType.M_SELECT || redisHandleType == RedisHandleType.SELECT ?
                redisTemplateMap.get(DEFAULT_SLAVE) : redisTemplateMap.get(DEFAULT_MASTER);
    }
}
