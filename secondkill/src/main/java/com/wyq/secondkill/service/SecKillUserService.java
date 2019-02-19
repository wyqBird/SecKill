package com.wyq.secondkill.service;

import com.wyq.secondkill.dao.SecKillUserDao;
import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.exception.GlobalException;
import com.wyq.secondkill.redis.RedisService;
import com.wyq.secondkill.redis.SecKillUserKey;
import com.wyq.secondkill.result.CodeMsg;
import com.wyq.secondkill.util.MD5Util;
import com.wyq.secondkill.util.UUIDUtil;
import com.wyq.secondkill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: SecKillUserService
 * @description: TODO
 * @date 2019/2/15 17:37
 */
@Service
public class SecKillUserService {
    //为了后期修改方便，我们定义一个常量
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private SecKillUserDao secKillUserDao;

    @Autowired
    private RedisService redisService;

    public SecKillUser getById(long id) {
        //return secKillUserDao.getById(id);
        //取缓存
        SecKillUser user = redisService.get(SecKillUserKey.getById, ""+id, SecKillUser.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = secKillUserDao.getById(id);
        if(user != null) {
            redisService.set(SecKillUserKey.getById, ""+id, user);
        }
        return user;
    }

    public boolean updatePassword(String token, long id, String formPass) {
        //取user
        SecKillUser user = getById(id);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        SecKillUser toBeUpdate = new SecKillUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        secKillUserDao.update(toBeUpdate);
        //处理缓存
        redisService.delete(SecKillUserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(SecKillUserKey.token, token, user);
        return true;
    }

    public SecKillUser getByToken(HttpServletResponse response, String token){
        //public 的方法，一定要进行参数校验
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        SecKillUser user = redisService.get(SecKillUserKey.token, token, SecKillUser.class);
        if(user != null) {
            //延长有效期
            addCookie(response, token, user);
        }
        return user;
    }

    //public String login(HttpServletResponse response, LoginVo loginVo) {
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        SecKillUser user = getById(Long.parseLong(mobile));
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String slatDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, slatDB);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        String token = UUIDUtil.uuid();
        addCookie(response, token, user);

        //return token;
        return true;
    }

    private void addCookie(HttpServletResponse response, String token, SecKillUser user) {
        redisService.set(SecKillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        //设置Cookie的有效期，为了和session保持一致，就设置成MiaoshaUserKey的有效期
        cookie.setMaxAge(SecKillUserKey.token.expireSeconds());
        //设置保存路径，为网站的根目录
        cookie.setPath("/");
        //这样生成完之后，我们只需要写道response中即可
        response.addCookie(cookie);
    }


}
