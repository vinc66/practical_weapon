package com.zxc.config;

import com.zxc.cache.VMasterSlaveStrategy;
import com.zxc.cache.VRedis;
import com.zxc.cache.VRedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
class RedisConfig {

    private static final String MASTER = "defaultMaster";
    private static final String SLAVE = "defaultSlave";

    @Autowired
    private RedisTemplate<String, String> masterRedisTemplate;

    @Bean("vRedis")
    @Primary
    public VRedis generalProductRedis() {
        VRedisTemplate mallRedis = generateMallRedis(
                masterRedisTemplate, masterRedisTemplate);
        return mallRedis;
    }

    private VRedisTemplate generateMallRedis(RedisTemplate<String, String> masterRedisTemplate,
                                             RedisTemplate<String, String> slaveRedisTemplate) {
        Map<String, RedisTemplate<String, String>> redisTemplateMap =
                new HashMap<String, RedisTemplate<String, String>>();

        redisTemplateMap.put(MASTER, masterRedisTemplate);
        redisTemplateMap.put(SLAVE, slaveRedisTemplate);

        VMasterSlaveStrategy shardingStrategy = new VMasterSlaveStrategy();
        shardingStrategy.setRedisTemplateMap(redisTemplateMap);
        VRedisTemplate mallRedis = new VRedisTemplate();
        mallRedis.setShardingStrategy(shardingStrategy);
        return mallRedis;
    }
}
