package com.wyq.secondkill.service;

import com.wyq.secondkill.dao.OrderDao;
import com.wyq.secondkill.domain.OrderInfo;
import com.wyq.secondkill.domain.SecKillOrder;
import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.redis.OrderKey;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: OrderService
 * @description: OrderService
 * @date 2019/2/17 17:09
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RedisService redisService;


    public SecKillOrder getSecKillOrderByUserIdGoodsId(Long userId, Long goodsId) {
        //return orderDao.getSecKillOrderByUserIdGoodsId(userId, goodsId);
        return redisService.get(OrderKey.getMiaoshaOrderByUidGid, "" + userId + "_" + goodsId, SecKillOrder.class);
    }

    public OrderInfo getOrderById(long orderId) {
        return orderDao.getOrderById(orderId);
    }


    @Transactional
    public OrderInfo createOrder(SecKillUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        //1pc, 2android, 3ios
        orderInfo.setOrderChannel(1);
        //订单状态，0新建未支付，1已支付，2已发货，3已收货，4，已退款，5已完成
        orderInfo.setStatus(0);

        long orderId = orderDao.insert(orderInfo);
        SecKillOrder seckillOrder = new SecKillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setGoodsId(goods.getId());
        orderDao.insertSecKillOrder(seckillOrder);

        redisService.set(OrderKey.getMiaoshaOrderByUidGid, "" + user.getId() + "_" + goods.getId(), seckillOrder);

        return orderInfo;
    }
}
