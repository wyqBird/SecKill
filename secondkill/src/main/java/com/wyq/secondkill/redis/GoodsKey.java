package com.wyq.secondkill.redis;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GoodsKey
 * @description: TODO
 * @date 2019/2/19 11:05
 */
public class GoodsKey extends BasePrefix {

    //这里使用private,防止外部实例化
    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    //同一个模块内部通过以下变量来区分
    public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
}