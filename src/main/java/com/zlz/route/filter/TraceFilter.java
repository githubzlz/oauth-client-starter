package com.zlz.route.filter;

import com.zlz.basic.utils.SnowWorker;
import com.zlz.route.common.trace.Trace;
import com.zlz.route.common.trace.TraceContext;
import com.zlz.route.common.user.User;
import com.zlz.route.properties.ServerPortProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

/**
 * @author zhulinzhong
 * @date 2022-03-08 19:21:23
 */
@Component
@Order(Integer.MIN_VALUE)
@Slf4j
public class TraceFilter implements Filter {

    @Resource
    private ServerPortProperties serverPortProperties;
    @Resource
    private SnowWorker snowWorker;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Instant start = Instant.now();

        initTrace();

        chain.doFilter(request, response);

        destroyTrace();

        Instant end = Instant.now();

        log.info("{}:耗时{}ms", request.getServletContext().getContextPath(), Duration.between(start, end).toMillis());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 初始化trace信息
     */
    private void initTrace() {
        // TODO 获取真正用户信息
        Trace trace = new Trace();
        trace.setTraceId(snowWorker.nextId());
        User user = new User();
        user.setId(-100L);
        trace.setUser(user);
        TraceContext.init(trace);

        MDC.put("traceId", TraceContext.getTraceId().toString());
        MDC.put("test", "123123123");
    }

    /**
     * 请求完成销毁信息
     */
    private void destroyTrace() {
        TraceContext.destroy();
    }
}
