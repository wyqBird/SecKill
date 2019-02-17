package com.wyq.secondkill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: MD5Util
 * @description: MD5工具类
 * @date 2019/2/15 17:02
 */
public class MD5Util {
    /**
     * md5 操作
     * @param src
     * @return
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 这里为什么写死？如果不写死，服务端根本不知道这个串是什么东西
     */
    private static final String salt = "1a2b3c4d";

    /**
     * 第一次 md5，提高传输过程中的数据安全性
     * @param inputPass
     * @return
     */
    public static String inputPassFromPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 第二次 md5，提高数据库中数据的安全性
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input, String saltDB) {
        String formPass = inputPassFromPass(input);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassFromPass("123456"));
        System.out.println(formPassToDBPass(inputPassFromPass("123456"), "1a2b3c4d"));
        System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));
    }
}
