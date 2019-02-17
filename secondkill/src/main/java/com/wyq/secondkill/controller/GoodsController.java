package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.service.GoodsService;
import com.wyq.secondkill.service.SecKillUserService;
import com.wyq.secondkill.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GoodsController
 * @description: TODO
 * @date 2019/2/16 16:32
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private SecKillUserService userService;
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/to_list")
    public String list(Model model, SecKillUser user) {
        model.addAttribute("user", user);

        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @GetMapping("/to_detail/{goodsId}")
    public String detail(Model model,SecKillUser user,
                         @PathVariable("goodsId")long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            seckillStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", seckillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }


}
