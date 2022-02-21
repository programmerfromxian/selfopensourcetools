package com.opensource.tools.common.advice;

import com.opensource.tools.common.model.CommonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author yangzai
 * @date 2022-02-16
 */
@ControllerAdvice
public class AdviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonReturn handle(HttpServletRequest request, Exception ex) {
        LOGGER.error("Execute request {} error: ", request.getRequestURI(), ex);
        return CommonReturn.builder().success(false).errMsg(ex.getMessage()).build();
    }

}
