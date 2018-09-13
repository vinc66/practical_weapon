package com.zxc.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zxc.web.Interceptor.AppInterceptor;
import com.zxc.web.Interceptor.BackInterceptor;
import com.zxc.web.Interceptor.CommonInterceptor;
import com.zxc.web.endpoint.WebSocketHandler;
import com.zxc.web.endpoint.WebSocketHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Administrator on 2018/8/25.
 */
@Configuration
@EnableWebSocket
public class MvcConfigurerAdapter extends WebMvcConfigurationSupport implements WebSocketConfigurer {

    //    配置资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    @Autowired
    private AppInterceptor appInterceptor;
    @Autowired
    private BackInterceptor backInterceptor;
    @Autowired
    private CommonInterceptor commonInterceptor;

    //    拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");
//app端
        registry.addInterceptor(appInterceptor)
                .addPathPatterns(
                        "/qhz/user/**")
                .excludePathPatterns(
                        "/qhz/user/reg");
//后台端
        registry.addInterceptor(backInterceptor)
                .addPathPatterns(
                        "/qhz/wap/**")
                .excludePathPatterns(
                        "/qhz/wap/user/code",
                        "/qhz/wap/user/login");
    }

    //        跨域
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        super.addCorsMappings(registry);
//        registry.addMapping("/**")
//                .allowedHeaders("*")
//                .allowedMethods("POST", "GET", "OPTIONS")
//                .allowedOrigins("*");
//    }

    //        格式转化
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(stringHttpMessageConverter);

    }

    //websocket支持
    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler, "/websocket").addInterceptors(new WebSocketHandlerInterceptor());
        webSocketHandlerRegistry.addHandler(webSocketHandler, "/sockjs").addInterceptors(new WebSocketHandlerInterceptor()).withSockJS();
    }

    //返回中文乱码问题
    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        //注入过滤器
        registration.setFilter(characterEncodingFilter);
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("CharacterEncodingFilter");
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }


//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(524288000);
//        factory.setMaxRequestSize(4096);
//        return factory.createMultipartConfig();
//    }
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(524288000);
        multipartResolver.setMaxInMemorySize(1048576);
        return multipartResolver;
    }

}
