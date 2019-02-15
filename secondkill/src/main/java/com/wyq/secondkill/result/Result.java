package com.wyq.secondkill.result;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: Result
 * @description: 返回结果的封装
 * @date 2019/2/14 17:17
 */
public class Result <T> {
    private int code;
    private String msg;
    /**
     * 因为不知道data具体是哪种类型，所以写成泛型
     */
    private T data;

    /**
     * 改成私有构造函数，因为我们不希望用户通过构造函数来生成Result对象
     * 而是通过我们封装好的方法来生成对象
     * @param data
     */
    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if(cm == null) {
            return;
        }

        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
     * 成功时调用
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 失败时调用
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
