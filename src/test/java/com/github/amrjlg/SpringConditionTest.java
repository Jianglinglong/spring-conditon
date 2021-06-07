package com.github.amrjlg;

import com.github.amrjlg.beans.Cat;
import com.github.amrjlg.property.DefaultProperties;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author amrjlg
 * 2019/5/30
 * 10:05
 */

public class SpringConditionTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.github.amrjlg");

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("-----------------");
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        Cat cat = applicationContext.getBean(Cat.class);
        System.out.println(cat);
        DefaultProperties properties = applicationContext.getBean(DefaultProperties.class);
        System.out.println(properties);
    }
}
