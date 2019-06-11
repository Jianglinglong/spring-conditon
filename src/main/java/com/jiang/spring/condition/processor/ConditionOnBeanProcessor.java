package com.jiang.spring.condition.processor;

import com.jiang.spring.condition.annotation.ConditionOnBean;
import com.jiang.spring.exception.ConditionOnBeanException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.beans.Introspector;
import java.util.Map;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 10:16
 */
public class ConditionOnBeanProcessor implements Condition {
    private final String annotationName = ConditionOnBean.class.getName();
    private final String beanName = Bean.class.getName();
    private static int match;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(annotationName);
        Class[] classes = (Class[]) attributes.get("value");
        if (classes == null || classes.length == 0) {
            throw new ConditionOnBeanException("必须要指定需要依赖的bean");
        }
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        for (Class aClass : classes) {
            String decapitalize = Introspector.decapitalize(aClass.getSimpleName());
            boolean containsBean = factory.containsBean(decapitalize) || factory.containsBean(aClass.getName());
            if (!containsBean) {
                return false;
            }
        }
        return true;
    }
}
