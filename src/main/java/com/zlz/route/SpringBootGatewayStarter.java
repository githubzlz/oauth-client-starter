package com.zlz.route;

import com.zlz.basic.utils.SnowWorker;
import com.zlz.route.properties.ServerPortProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhulinzhong
 * @date 2022-02-25 16:47:06
 */
@Configuration
@ConditionalOnClass(ServerPortProperties.class)
@EnableConfigurationProperties(ServerPortProperties.class)
@ComponentScan(value = "com.zlz.route")
public class SpringBootGatewayStarter {

    @Bean
    public SnowWorker getSnowWorker(){
        return new SnowWorker();
    }

}
