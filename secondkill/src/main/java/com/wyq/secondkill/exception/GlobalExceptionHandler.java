package com.wyq.secondkill.exception;

import com.wyq.secondkill.result.CodeMsg;
import com.wyq.secondkill.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GlobalExceptionHandler
 * @description: TODO
 * @date 2019/2/16 13:39
 */
@ControllerAdvice  //切面
@ResponseBody    //为了方便输出，使用@ResponseBody
public class GlobalExceptionHandler {
    //这个类就相当于一个controller
    @ExceptionHandler(value=Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        if(e instanceof GlobalException) {
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCm());
        }
        else if(e instanceof BindException) {
            BindException ex = (BindException)e;
            //为什么是一个数组，因为参数校验可能有很多错误，而不是一项
            List<ObjectError> errors = ex.getAllErrors();
            //为简单起见，我们只取第一个错误
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else {
            //如果不是一个绑定异常，则返回一个通用的服务端异常
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}

