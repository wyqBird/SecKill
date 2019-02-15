package com.wyq.secondkill.redis;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: UserKey
 * @description: UserKey
 * @date 2019/2/15 14:45
 */
public class UserKey extends BasePrefix {

    //这里使用private,防止外部实例化
    private UserKey(String prefix) {
        super(prefix);
        // TODO Auto-generated constructor stub
    }

    //同一个模块内部通过以下变量来区分    UserKey:id1
    //有可能UserKey是用id来做一个缓存
    public static UserKey getById = new UserKey("id");
    //有可能使用name来做一个缓存
    public static UserKey getByName = new UserKey("name");

}
