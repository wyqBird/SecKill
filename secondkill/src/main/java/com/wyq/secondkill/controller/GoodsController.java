package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.service.SecKillUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GoodsController
 * @description: TODO
 * @date 2019/2/16 16:32
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private SecKillUserService userService;

    @GetMapping("/to_list")
    public String list(Model model, SecKillUser user) {
        model.addAttribute("user", user);
        return "goods_list";
    }



}
