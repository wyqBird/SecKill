package com.wyq.secondkill.redis;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: BasePrefix
 * @description: BasePrefix
 * @date 2019/2/14 21:56
 */
public abstract class BasePrefix implements KeyPrefix{
    //如何保证每个模块的prefix是不一样的呢?
    private int expireSeconds;
    private String prefix;

    //我们希望这个id存储的User对象永远不过期，所以再加一个构造函数，直接放入抽象类中BasePrefix
    //BasePrefix中不需要用private,因为BasePrefix是抽象类，别人无法 new
    public BasePrefix(String prefix) {
        //用 0 来代表永不过期
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        super();
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        //默认 0 为永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }

}
