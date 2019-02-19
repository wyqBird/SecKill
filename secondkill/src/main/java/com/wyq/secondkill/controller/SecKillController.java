package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.OrderInfo;
import com.wyq.secondkill.domain.SecKillOrder;
import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.result.CodeMsg;
import com.wyq.secondkill.result.Result;
import com.wyq.secondkill.service.GoodsService;
import com.wyq.secondkill.service.OrderService;
import com.wyq.secondkill.service.SecKillService;
import com.wyq.secondkill.service.SecKillUserService;
import com.wyq.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public Result<OrderInfo> seckill(Model model,SecKillUser user,
                                     @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);//10个商品，req1 req2
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return Result.error(CodeMsg.SECKILL_OVER);
        }
        //判断是否已经秒杀到了
        SecKillOrder order = orderService.getSecKillOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            return Result.error(CodeMsg.REPEATE_SECKILL);
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = secKillService.seckill(user, goods);
        return Result.success(orderInfo);
    }
}

