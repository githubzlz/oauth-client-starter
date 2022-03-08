package com.zlz.route;

import com.zlz.route.apis.ApisUp;
import com.zlz.route.interceptor.SecurityInterceptor;
import com.zlz.route.interceptor.WebSecurityConfig;
import com.zlz.route.properties.ServerPortProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @author zhulinzhong
 * @date 2022-02-25 16:47:06
 */
@Configuration
@ConditionalOnClass(ServerPortProperties.class)
@EnableConfigurationProperties(ServerPortProperties.class)
public class SpringBootGatewayStarter {
    @Resource
    private WebApplicationContext webApplicationContext;

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public WebSecurityConfig getWebSecurityConfig() {
        return new WebSecurityConfig();
    }

    @Bean
    public ApisUp getApisUp() {
        return new ApisUp(webApplicationContext);
    }


}
