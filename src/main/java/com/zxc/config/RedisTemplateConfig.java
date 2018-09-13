package com.zxc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
class RedisTemplateConfig {

    @Autowired
    private JedisPoolConfig poolConfig;

    @Bean
    @ConfigurationProperties("redis.pool")
    public JedisPoolConfig poolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean("masterFactory")
    @ConfigurationProperties("redis.master")
    public JedisConnectionFactory masterFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(poolConfig);
        return jedisConnectionFactory;
    }

    @Autowired
    @Qualifier("masterFactory")
    private JedisConnectionFactory generalMasterFactory;

    @Bean("masterRedisTemplate")
    public RedisTemplate<String, String> generalMasterRedisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(generalMasterFactory);
        return redisTemplate;
    }


}
