package com.wyq.secondkill.service;

import com.wyq.secondkill.dao.GoodsDao;
import com.wyq.secondkill.domain.SecKillGoods;
import com.wyq.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GoodsService
 * @description: TODO
 * @date 2019/2/17 16:28
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        SecKillGoods g = new SecKillGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }

}
