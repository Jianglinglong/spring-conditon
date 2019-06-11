package com.jiang.spring.condition.annotation;

import com.jiang.spring.condition.processor.ConfigurationPropertyProcessor;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 13:36
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Import(ConfigurationPropertyProcessor.class)
@PropertySource("classpath:application.properties")
public @interface ConfigurationProperty {
    String prefix();
}
