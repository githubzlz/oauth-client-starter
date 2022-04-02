package com.zlz.route.exception;

import com.zlz.basic.exception.BizException;
import com.zlz.basic.response.ResultSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2020-06-06 09:17
 * @description 全局统一异常处理
 */
@ControllerAdvice
@RestController
@Slf4j
public class BlogExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultSet<Integer> exceptionHandler(Exception e) {
        log.error("发生异常:", e);
        if (e instanceof BizException) {
            return new ResultSet<>(e.getMessage(), ((BizException) e).getCode());
        }
        return ResultSet.error("未知错误，请联系管理员");
    }
}
