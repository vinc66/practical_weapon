package com.zxc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/8/25.
 */
@Configuration
@Slf4j
public class SpringRestTemplateConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    public HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(50);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(0, false);
        httpClientBuilder.setRetryHandler(retryHandler);
        httpClientBuilder.setDefaultHeaders(Arrays.asList(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")));

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClientBuilder.build());
        httpComponentsClientHttpRequestFactory.setConnectTimeout(2000);
        httpComponentsClientHttpRequestFactory.setReadTimeout(5000);
        log.info(" http配置完成...");
        return httpComponentsClientHttpRequestFactory;
    }
}
