package com.jiang.spring.condition.processor;

import com.jiang.spring.condition.annotation.ConfigurationProperty;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 13:43
 */
public class ConfigurationPropertyProcessor  implements ApplicationContextAware,EnvironmentAware, BeanFactoryAware, BeanPostProcessor, Condition {

    private Environment environment;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        boolean present = beanClass.isAnnotationPresent(ConfigurationProperty.class);
        if (present){
            ConfigurationProperty annotation = beanClass.getAnnotation(ConfigurationProperty.class);
            String prefix = annotation.prefix();
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                Object property = environment.getProperty(key(prefix, field.getName()), field.getType());
                if(property != null){
                    if (!Modifier.isPublic(field.getModifiers())){
                        field.setAccessible(true);
                    }
                    try {
                        field.set(bean,property);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    private String key (String prefix,String name){
        if (StringUtils.hasText(prefix)){
            return prefix+"."+name;
        }else {
            return name;
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        return true;
    }
}
