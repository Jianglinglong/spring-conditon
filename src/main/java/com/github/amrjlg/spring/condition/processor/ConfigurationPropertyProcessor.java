package com.github.amrjlg.spring.condition.processor;

import com.github.amrjlg.spring.exception.ResourceNotExistException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 13:43
 */
public class ConfigurationPropertyProcessor implements Condition {
    private String key(String prefix, String name) {
        if (StringUtils.hasText(prefix)) {
            return prefix + "." + name;
        } else {
            return name;
        }
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> configurationProperty = metadata.getAnnotationAttributes("com.github.jianglinglong.spring.condition.annotation.ConfigurationProperty");
        if (configurationProperty == null) {
            return false;
        }
        boolean match = false;
        StandardEnvironment environment = (StandardEnvironment) context.getEnvironment();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        ResourceLoader resourceLoader = context.getResourceLoader();
        Map<String, Object> attributes = metadata.getAnnotationAttributes("org.springframework.context.annotation.PropertySource");
        String prefix = (String) configurationProperty.get("prefix");
        String[] propertyNames = (String[]) attributes.get("value");
        MutablePropertySources propertySources = environment.getPropertySources();
        ClassMetadata classMetadata = (ClassMetadata) metadata;
        for (String name : propertyNames) {
            Resource resource = resourceLoader.getResource(name);
            if (!resource.exists()) {
                throw new ResourceNotExistException(resource.getDescription() + "not exists");
            }
            Properties properties = new Properties();

            String className = classMetadata.getClassName();
            try {
                properties.load(resource.getInputStream());
                Class<?> beanClass = ClassUtils.forName(className, null);
                Field[] fields = beanClass.getDeclaredFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if (properties.containsKey(key(prefix, fieldName))) {
                        match = true;
                    }
                }
                if (match) {
                    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(beanClass).getRawBeanDefinition();


                    MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
                    if (!propertySources.contains(name)) {
                        propertySources.addFirst(new PropertiesPropertySource(name, properties));
                    }
                    for (Field field : fields) {
                        propertyValues.addPropertyValue(field.getName(), environment.getProperty(key(prefix, field.getName()), field.getType(), null));
                    }
                    context.getRegistry().registerBeanDefinition(className, beanDefinition);
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

}
