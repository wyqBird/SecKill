package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.OrderInfo;
import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.result.CodeMsg;
import com.wyq.secondkill.result.Result;
import com.wyq.secondkill.service.GoodsService;
import com.wyq.secondkill.service.OrderService;
import com.wyq.secondkill.service.SecKillUserService;
import com.wyq.secondkill.vo.GoodsVo;
import com.wyq.secondkill.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: OrderController
 * @description: TODO
 * @date 2019/2/19 15:47
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private SecKillUserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, SecKillUser user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}
