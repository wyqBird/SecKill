package com.wyq.secondkill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: WebConfig
 * @description: TODO
 * @date 2019/2/16 17:48
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserArgumentResolver userArgumentResolver;

    /*
     * springmvc的controller中可以带很多参数，比如request，response，model。。。
     * 那这些都参数是怎么来的呢？值是谁给他赋的呢，就是addArgumentResolvers来赋值的，
     * 框架回调这个方法，往我们的controller方法中的参数进行赋值。
     * (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addArgumentResolvers(java.util.List)
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // TODO Auto-generated method stub
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(userArgumentResolver);
    }
}
