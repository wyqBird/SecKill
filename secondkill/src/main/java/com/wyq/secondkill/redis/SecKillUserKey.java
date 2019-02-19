package com.wyq.secondkill.redis;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: SecKillUserKey
 * @description: TODO
 * @date 2019/2/16 16:16
 */
public class SecKillUserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 *2;

    //这里使用private,防止外部实例化
    private SecKillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static SecKillUserKey token = new SecKillUserKey(TOKEN_EXPIRE, "tk");
    public static SecKillUserKey getById = new SecKillUserKey(0, "id");
}
