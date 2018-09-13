package com.zxc.web.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description: 后台获取随机验证码
 * _User: vinc
 * Date: 2018-08-27
 * Time: 14:39
 */
@Configuration
public class KaptchaConfig {

    @Bean(name = "captchaProducer")
    public DefaultKaptcha initKaptcha(){
        Config config = new Config(getProperties());
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }


    @ConfigurationProperties(prefix = "kaptcha")
    public Properties getProperties() {
        Properties pro = new Properties();
        return pro;
    }
}
