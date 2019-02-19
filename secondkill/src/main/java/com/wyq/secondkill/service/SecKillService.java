package com.wyq.secondkill.service;

import com.wyq.secondkill.domain.OrderInfo;
import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: SecKillService
 * @description: SecKillService
 * @date 2019/2/17 17:15
 */
@Service
public class SecKillService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    @Transactional
    public OrderInfo seckill(SecKillUser user, GoodsVo goods) {
        //减库存、下订单、写入秒杀订单
        goodsService.reduceStock(goods);
        //创建订单信息，写入秒杀订单
        return orderService.createOrder(user, goods);
    }
}
