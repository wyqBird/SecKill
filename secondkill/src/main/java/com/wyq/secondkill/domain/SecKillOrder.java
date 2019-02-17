package com.wyq.secondkill.domain;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: SecKillOrder
 * @description: SecKillOrder
 * @date 2019/2/17 16:15
 */
public class SecKillOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
