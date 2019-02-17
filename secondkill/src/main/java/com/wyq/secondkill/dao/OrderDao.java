package com.wyq.secondkill.dao;

import com.wyq.secondkill.domain.OrderInfo;
import com.wyq.secondkill.domain.SecKillOrder;
import org.apache.ibatis.annotations.*;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: OrderDao
 * @description: OrderDao
 * @date 2019/2/17 17:04
 */
@Mapper
public interface OrderDao {
    /**
     * 根据用户id和商品id，查询是否秒杀成功
     * @param userId
     * @param goodsId
     * @return
     */
    @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    public SecKillOrder getSecKillOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

    /**
     * 插入订单信息
     * @param orderInfo
     * @return
     */
    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    /**
     * 插入秒杀订单信息
     * @param seckillOrder
     */
    @Insert("insert into miaosha_order (user_id, goods_id, order_id)"
            + "values(#{userId}, #{goodsId}, #{orderId})")
    public void insertSecKillOrder(SecKillOrder seckillOrder);

}
