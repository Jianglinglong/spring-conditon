package io.github.amrjlg.spring.condition.annotation;

import io.github.amrjlg.spring.condition.processor.ConfigurationPropertyProcessor;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * default propertySource location classpath:application.properties
 * <p>
 * matching mode like : prefix.fieldName or fieldName
 *
 * @author amrjlg
 * 2019/5/30
 * 13:36
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Conditional(ConfigurationPropertyProcessor.class)
@PropertySource("classpath:application.properties")
public @interface ConfigurationProperty {
    String prefix();
}
