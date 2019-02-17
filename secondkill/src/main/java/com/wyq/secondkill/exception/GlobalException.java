package com.wyq.secondkill.exception;

import com.wyq.secondkill.result.CodeMsg;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: GlobalException
 * @description: TODO
 * @date 2019/2/16 14:16
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 2376744703700063097L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
