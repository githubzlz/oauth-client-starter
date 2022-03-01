package com.zlz.gateway.apis;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @author zhulinzhong
 * @date 2022-02-25 17:43:27
 */
public class ApisUp implements ApplicationRunner {

    private WebApplicationContext webApplicationContext;

    public ApisUp(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        handlerMethods.forEach((k, v) -> {
            for (String pattern : k.getPatternsCondition().getPatterns()) {
                System.out.println(pattern);
            }
        });
    }

}
