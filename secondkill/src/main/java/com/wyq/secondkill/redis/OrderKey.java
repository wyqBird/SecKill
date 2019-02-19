package com.wyq.secondkill.redis;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: OrderKey
 * @description: TODO
 * @date 2019/2/19 16:57
 */
public class OrderKey extends BasePrefix{

    public OrderKey(String prefix) {
        super(prefix);
    }
    //这里设置永久不过期
    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");

}
