package com.zlz.route.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhulinzhong
 * @date 2022-02-25 16:49:47
 */
@Component
@ConfigurationProperties(prefix = "route")
public class ServerPortProperties {

    private String host;

    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
