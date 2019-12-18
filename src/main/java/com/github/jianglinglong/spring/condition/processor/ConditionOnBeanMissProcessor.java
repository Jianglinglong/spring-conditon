package com.github.jianglinglong.spring.condition.processor;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;

/**
 * @author jiang
 * @date 2019/5/30
 * @time 10:16
 */
public class ConditionOnBeanMissProcessor implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (metadata instanceof MethodMetadata){
            MethodMetadata methodMetadata = (MethodMetadata) metadata;
            String methodName = methodMetadata.getMethodName();
            String returnTypeName = methodMetadata.getReturnTypeName();
            ConfigurableListableBeanFactory factory = context.getBeanFactory();
            boolean method = !factory.containsBeanDefinition(methodName);
            boolean returnType = !factory.containsBeanDefinition(returnTypeName);
            return method || returnType;
        }else if (metadata instanceof ClassMetadata){
            ClassMetadata classMetadata = (ClassMetadata) metadata;
            String className = classMetadata.getClassName();
            return !context.getBeanFactory().containsBeanDefinition(className);
        }else {
            return false;
        }

    }
}
