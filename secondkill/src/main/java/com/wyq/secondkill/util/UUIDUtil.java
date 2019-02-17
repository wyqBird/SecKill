package com.wyq.secondkill.util;

import java.util.UUID;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: UUIDUtil
 * @description: TODO
 * @date 2019/2/16 15:01
 */
public class UUIDUtil {
    public static String uuid() {
        //原生的uuid带‘-’，我们希望去掉它
        return UUID.randomUUID().toString().replace("-", "");
    }
}
