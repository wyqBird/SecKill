package com.wyq.secondkill.redis;

/**
 *@author: coldsmoke
 */
public interface KeyPrefix {
    //考虑到实际缓存的情况，这里设置两个方法
    //有效期
    public int expireSeconds();

    //前缀
    public String getPrefix();
}
