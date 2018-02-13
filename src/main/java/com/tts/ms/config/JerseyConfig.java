//package com.tts.ms.config;
//
//import com.tts.ms.rest.integration.BizExceptionMapper;
//import org.glassfish.jersey.jackson.JacksonFeature;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Jersey配置
// * @Author gaoyan
// * @Date: 2017/12/24
// */
//@Component
//public class JerseyConfig extends ResourceConfig {
//
//    public JerseyConfig() {
//        //构造函数，在这里注册需要使用的内容，（过滤器，拦截器，API等）
//        // 向jersey框架注册资源类，凡完全限定名是以指定字符串开头的类，都将包含  ,"com.xcrm.rest.integration"
//        packages("com.tts.ms.resource");
//        register(BizExceptionMapper.class);
//        register(JacksonFeature.class);
//        Set<Class<?>> resources = new HashSet<>();
//        registerClasses(resources);
//    }
//}