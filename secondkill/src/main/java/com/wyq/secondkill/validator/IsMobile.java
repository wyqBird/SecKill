package com.wyq.secondkill.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: IsMobile
 * @description: @IsMobile
 * @date 2019/2/16 13:18
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class })
public @interface IsMobile {

    //参数默认值，不能为空
    boolean required() default true;
    //若校验不通过，提示什么信息
    String message() default "手机号码格式有误！！！";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
