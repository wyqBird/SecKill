package com.wyq.secondkill.config;

import com.wyq.secondkill.domain.SecKillUser;
import com.wyq.secondkill.service.SecKillUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: UserArgumentResolver
 * @description: UserArgumentResolver
 * @date 2019/2/16 17:55
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private SecKillUserService userService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //获取参数类型
        Class<?> clazz = parameter.getParameterType();
        //SecKillUser，则返回true，如果不是返回false
        return clazz == SecKillUser.class;

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //获取http的request/response
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        //获取token
        String paramToken = request.getParameter(SecKillUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, SecKillUserService.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return userService.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        //获得所有cookie
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0) {
            return null;
        }
        for(Cookie cookie : cookies) {
            //遍历，如果和我们需要的cookie同名，则取值
            if(cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
