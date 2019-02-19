package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.OrderInfo;
import com.wyq.secondkill.domain.SecKillOrder;
import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.result.CodeMsg;
import com.wyq.secondkill.service.GoodsService;
import com.wyq.secondkill.service.OrderService;
import com.wyq.secondkill.service.SecKillService;
import com.wyq.secondkill.service.SecKillUserService;
import com.wyq.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: SecKillController
 * @description: TODO
 * @date 2019/2/17 17:17
 */
@Controller
@RequestMapping("/miaosha")
public class SecKillController {
    @Autowired
    SecKillUserService userService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SecKillService secKillService;

    /**
     * QPS:555   并发量5000，循环10次
     */
    @PostMapping("/do_miaosha")
    public String list(Model model, SecKillUser user,
                       @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
            return "seckill_fail";
        }
        //判断是否已经秒杀到了
        SecKillOrder order = orderService.getSecKillOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_SECKILL.getMsg());
            return "seckill_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = secKillService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}

