package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.result.CodeMsg;
import com.wyq.secondkill.result.Result;
import com.wyq.secondkill.service.SecKillUserService;
import com.wyq.secondkill.util.ValidatorUtil;
import com.wyq.secondkill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * @author coldsmoke
 * @version 1.0
 * @className: LoginController
 * @description: TODO
 * @date 2019/2/15 17:21
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 使用slf4j，因为它是一个接口，具体实现可以选择其他，非常方便
     */
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SecKillUserService userService;

    @GetMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //登陆
        userService.login(response, loginVo);
        //如果出现异常，则直接抛出了，所有直接返回true就行
        return Result.success(true);

    }

//    //生成用户信息时用
//    @PostMapping("/do_login")
//    @ResponseBody
//    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
//        log.info(loginVo.toString());
//        //登陆
//        String token = userService.login(response, loginVo);
//        //如果出现异常，则直接抛出了，所有直接返回true就行
//        return Result.success(token);
//
//    }

}

