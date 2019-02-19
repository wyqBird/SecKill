package com.wyq.secondkill.controller;

import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.redis.GoodsKey;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.result.Result;
import com.wyq.secondkill.service.GoodsService;
import com.wyq.secondkill.service.SecKillUserService;
import com.wyq.secondkill.vo.GoodsDetailVo;
import com.wyq.secondkill.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GoodsController
 * @description: GoodsController
 * @date 2019/2/16 16:32
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private SecKillUserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * QPS:966, 5000并发量，循环10次
     */
    @GetMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request,
            HttpServletResponse response,
            Model model, SecKillUser user) {
        model.addAttribute("user", user);

        //1.从缓存中取值，看能不能取到
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }

        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        //return "goods_list";

        SpringWebContext ctx = new SpringWebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap(), applicationContext);

        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;

    }

    @GetMapping(value = "/to_detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Model model, SecKillUser user,
                                        @PathVariable("goodsId")long goodsId) {
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        return Result.success(vo);
    }

}
