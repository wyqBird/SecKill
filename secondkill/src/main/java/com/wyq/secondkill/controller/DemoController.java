package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.User;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.redis.UserKey;
import com.wyq.secondkill.result.CodeMsg;
import com.wyq.secondkill.result.Result;
import com.wyq.secondkill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: DemoController
 * @description: 测试Controller
 * @date 2019/2/14 16:34
 */
@Controller
public class DemoController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name", "wyq");
        return "hello";
    }

    @GetMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        //成功时只需要传递数据
        return Result.success("hello wyq!");
        //return new Result(0, "success", "hello wyq!");
    }

    @GetMapping("/helloError")
    @ResponseBody
    public Result<String> helloError(){
        //失败时只需要传递CodeMsg
        return Result.error(CodeMsg.SERVER_ERROR);
        //return new Result(500102, "XXX");
    }

    @GetMapping("/db/get")
    @ResponseBody
    public Result<User> doGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @GetMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        userService.tx();
        return Result.success(true);
    }

    @GetMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }

    @GetMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("哈哈");
        // UserKey:id1
        redisService.set(UserKey.getById, ""+ 1, user);
        return Result.success(true);
    }
}
