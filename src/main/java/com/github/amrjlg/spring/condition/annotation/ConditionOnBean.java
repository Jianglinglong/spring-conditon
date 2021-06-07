package com.github.amrjlg.spring.condition.annotation;

import com.github.amrjlg.spring.condition.processor.ConditionOnBeanProcessor;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author amrjlg
 * 2019/5/30 10:06
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionOnBeanProcessor.class)
public @interface ConditionOnBean {
    Class<?>[] value();
}
