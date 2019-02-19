package com.wyq.secondkill.vo;

import com.wyq.secondkill.domain.SecKillUser;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GoodsDetailVo
 * @description: TODO
 * @date 2019/2/19 14:25
 */
public class GoodsDetailVo {
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private SecKillUser user;

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public SecKillUser getUser() {
        return user;
    }

    public void setUser(SecKillUser user) {
        this.user = user;
    }
}
