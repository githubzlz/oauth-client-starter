package com.zlz.gateway.interceptor;

import com.zlz.gateway.properties.ServerPortProperties;
import com.zlz.route.common.trace.Trace;
import com.zlz.route.common.trace.TraceContext;
import com.zlz.route.common.user.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulinzhong
 * @date 2022-02-25 17:05:07
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private ServerPortProperties serverPortProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        initTrace();
        System.out.println(serverPortProperties.getHost() + ":" + serverPortProperties.getPort());
        request.setAttribute("startTime", System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        super.postHandle(request, response, handler, modelAndView);
        destroyTrace();
    }

    private void initTrace(){
        Trace trace = new Trace();
        trace.setTraceId(Thread.currentThread().getId());
        User user = new User();
        user.setId(Thread.currentThread().getId());
        trace.setUser(user);
        TraceContext.init(trace);
    }

    private void destroyTrace(){
        TraceContext.destroy();
    }
}
