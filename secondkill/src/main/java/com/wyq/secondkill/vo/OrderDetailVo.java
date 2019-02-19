package com.wyq.secondkill.vo;

import com.wyq.secondkill.domain.OrderInfo;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: OrderDetailVo
 * @description: TODO
 * @date 2019/2/19 15:51
 */
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
    public GoodsVo getGoods() {
        return goods;
    }
    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }
    public OrderInfo getOrder() {
        return order;
    }
    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
