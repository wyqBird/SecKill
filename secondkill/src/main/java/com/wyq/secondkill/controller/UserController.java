package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.result.Result;
import com.wyq.secondkill.service.SecKillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: UserController
 * @description: TODO
 * @date 2019/2/18 12:22
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    SecKillUserService userService;
    @Autowired
    RedisService redisService;
    @RequestMapping("/info")
    @ResponseBody
    public Result<SecKillUser> info(Model model, SecKillUser user) {
        return Result.success(user);
    }
}
